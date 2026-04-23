/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.reservations;

import hotel.people.Guest;
import hotel.rooms.Room;
import hotel.model.ReservationStatus;
import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private Guest guest;
    private Room room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
public Reservation(int reservationId, Guest guest, Room room,
                   LocalDate checkInDate, LocalDate checkOutDate) {

    if (guest == null || room == null || checkInDate == null || checkOutDate == null) {
        throw new InvalidDataException("Invalid data");
    }

    if (!checkInDate.isBefore(checkOutDate)) {
        throw new IllegalArgumentException("Check-in must be before check-out");
    }

    this.reservationId = reservationId;
    this.guest = guest;
    this.room = room;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.status = ReservationStatus.CONFIRMED;
}
    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
        room.free();
        System.out.println("Reservation " + reservationId + " cancelled.");
    }

    public void complete() {
        this.status = ReservationStatus.COMPLETED;
        room.free();
        System.out.println("Reservation " + reservationId + " completed.");
    }

    public int getReservationId() {
        return reservationId;
    }
    public Guest getGuest() {
        return guest;
    }
    public Room getRoom() {
        return room;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public ReservationStatus getStatus() {
        return status;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    public void validateRoomExists() {
    if (room == null) {
        throw new ROOMNOTAVAILABLEEXCEPTION("Room does not exist for this reservation.");
    }
}
}



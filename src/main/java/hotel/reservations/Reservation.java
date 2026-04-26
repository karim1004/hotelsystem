package hotel.reservations;
 
import hotel.people.Guest;
import hotel.rooms.Room;
import hotel.model.ReservationStatus;
import hotel.exceptions.InvalidDataException;
import hotel.exceptions.ROOMNOTAVAILABLEEXCEPTION;
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
        if (guest == null)
            throw new InvalidDataException("Guest cannot be null.");
        if (room == null)
            throw new ROOMNOTAVAILABLEEXCEPTION("Room cannot be null.");
        if (checkInDate == null || checkOutDate == null)
            throw new InvalidDataException("Check-in and check-out dates cannot be null.");
        if (!checkInDate.isBefore(checkOutDate))
            throw new InvalidDataException("Check-in date must be before check-out date.");
 
        this.reservationId = reservationId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.CONFIRMED; 
    }
 
    public void cancel() {
        if (status == ReservationStatus.CANCELLED)
            return;
        if (status == ReservationStatus.COMPLETED)
            throw new IllegalStateException("Cannot cancel a completed reservation.");
        status = ReservationStatus.CANCELLED;
        room.free();
    }
 
    public void complete() {
        if (status == ReservationStatus.COMPLETED)
            return;
        if (status == ReservationStatus.CANCELLED)
            throw new IllegalStateException("Cannot complete a cancelled reservation.");
        status = ReservationStatus.COMPLETED;
        room.free();
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
        if (checkInDate == null || !checkInDate.isBefore(this.checkOutDate))
            throw new InvalidDataException("Invalid check-in date.");
        this.checkInDate = checkInDate;
    }
 
    public void setCheckOutDate(LocalDate checkOutDate) {
        if (checkOutDate == null || !this.checkInDate.isBefore(checkOutDate))
            throw new InvalidDataException("Invalid check-out date.");
        this.checkOutDate = checkOutDate;
    }
 
    public void validateRoomExists() {
        if (room == null)
            throw new ROOMNOTAVAILABLEEXCEPTION("Room does not exist for this reservation.");
    }
 
    
    public String toString() {
        return "Reservation #" + reservationId +
               " | Guest: " + guest.getUsername() +
               " | Room: " + room.getRoomNumber() +
               " | " + checkInDate + " -> " + checkOutDate +
               " | Status: " + status;
    }
}}

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
    if (checkInDate == null || !checkInDate.isBefore(this.checkOutDate)) {
        throw new InvalidDataException("Invalid check-in date");
    }
    this.checkInDate = checkInDate;
}
public void setCheckOutDate(LocalDate checkOutDate) {
    if (checkOutDate == null || !this.checkInDate.isBefore(checkOutDate)) {
        throw new InvalidDataException("Invalid check-out date");
    }
    this.checkOutDate = checkOutDate;
}
    public void validateRoomExists() {
    if (room == null) {
        throw new ROOMNOTAVAILABLEEXCEPTION("Room does not exist for this reservation.");
    }
}
}



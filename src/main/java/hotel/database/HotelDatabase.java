/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.database;

import hotel.people.Guest;
import hotel.rooms.Room;
import hotel.reservations.Reservation;
import hotel.reservations.Invoice;
import java.util.ArrayList;
import java.util.List;

public class HotelDatabase {

    private static List<Guest> guests = new ArrayList<>();
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Invoice> invoices = new ArrayList<>();


    public static void addGuest(Guest guest) {
        guests.add(guest);
    }
    public static void addRoom(Room room) {
        rooms.add(room);
    }
    public static void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
    public static void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public static List<Guest> getGuests() {
        return guests;
    }
    public static List<Room> getRooms() {
        return rooms;
    }
    public static List<Reservation> getReservations() {
        return reservations;
    }
    public static List<Invoice> getInvoices() {
        return invoices;
    }

    public static List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getAvailability().toString().equals("AVAILABLE")) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}



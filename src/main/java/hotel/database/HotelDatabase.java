/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.database;
import hotel.rooms.Room;
import hotel.rooms.RoomType;
import hotel.rooms.Amenity;
import hotel.people.Guest;
import hotel.people.Staff;
import hotel.people.Admin;
import hotel.people.Receptionist;
import hotel.model.Gender;
import hotel.model.Role;
import hotel.model.AmenityType;  
import hotel.model.ReservationStatus;
import hotel.reservations.Reservation;
import hotel.reservations.Invoice;
import hotel.exceptions.InvalidDataException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Arrays;


public class HotelDatabase {

    private static ArrayList<Guest> guests = new ArrayList<>();
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<Invoice> invoices = new ArrayList<>();
    private static ArrayList<RoomType> roomTypes = new ArrayList<>();
    private static ArrayList<Amenity> amenities = new ArrayList<>();
    private static ArrayList<Staff> staffMembers = new ArrayList<>();

    static{

        // Amenities
        Amenity wifi= new Amenity(AmenityType.WIFI,"High-speed Wifi");
        Amenity tv= new Amenity(AmenityType.TV,"Flat screen TV");
        Amenity minibar = new Amenity(AmenityType.MINIBAR,"Mini bar");
        Amenity ac= new Amenity(AmenityType.AIR_CONDITIONING, "Air Conditioner");
        amenities.addAll(Arrays.asList(wifi, tv, minibar, ac));

        // Room Types
        RoomType single = new RoomType("Single", 500);
        RoomType doubleRoom = new RoomType("Double", 800);
        RoomType suite = new RoomType("Suite", 1500);

        roomTypes.addAll(Arrays.asList(single, doubleRoom, suite));

        //  Rooms
        rooms.add(new Room(101,single,new ArrayList<>(Arrays.asList(wifi, tv))));
        rooms.add(new Room(102,doubleRoom,new ArrayList<>(Arrays.asList(wifi, tv, ac))));
        rooms.add(new Room(201,suite,new ArrayList<>(Arrays.asList(wifi, tv, minibar, ac))));

        Guest guest1 = new Guest(
                "Reem", "Password123", LocalDate.of(2005, 11, 5),
                3000.0, "Cairo", Gender.FEMALE, "High floor, quiet room"
        );
        Guest guest2 = new Guest(
                "Zeyad", "Secure456", LocalDate.of(2004, 8, 15),
                2000.0, "Alexandria", Gender.MALE, "Near elevator"
        );

        guests.addAll(Arrays.asList(guest1, guest2));

        staffMembers.add(new Admin("admin_main", "Admin12345", LocalDate.of(1985, 12, 1), "HQ", Gender.MALE, 40));
        staffMembers.add(new Receptionist("jana_recep", "janaPass1", LocalDate.of(1995, 6, 15), "HQ", Gender.FEMALE, 35));
        reservations.add(new Reservation(1, guest1, rooms.get(0), LocalDate.now(), LocalDate.now().plusDays(2)));
    }


    public static ArrayList<Guest> getGuests() { return new ArrayList<>(guests); }
    public static ArrayList<Room> getRooms() { return new ArrayList<>(rooms); }
    public static ArrayList<Reservation> getReservations() { return new ArrayList<>(reservations); }
    public static ArrayList<Invoice> getInvoices() { return new ArrayList<>(invoices); }
    public static ArrayList<RoomType> getRoomTypes() { return new ArrayList<>(roomTypes); }
    public static ArrayList<Amenity> getAmenities() { return new ArrayList<>(amenities); }
    public static ArrayList<Staff> getStaffMembers() { return new ArrayList<>(staffMembers); }


    public static void addGuest(Guest guest) {
    
        if (guest.getUsername() == null || guest.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
       
        if (guest.getBalance() < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        guests.add(guest);
    }
    
public static void addReservation(Reservation res) {
    if (res == null || res.getRoom() == null) {
        throw new IllegalArgumentException("Reservation or Room cannot be null");
    }

    if (!isRoomAvailable(res.getRoom(), res.getCheckIn(), res.getCheckOut())) {
        throw new RuntimeException("Room already booked for these dates!");
    }

    reservations.add(res);
}


public static void addRoom(Room room) {
    if (room == null) {
        throw new IllegalArgumentException("Room cannot be null");
    }

    if (room.getRoomNumber() <= 0) {
        throw new IllegalArgumentException("Invalid room number");
    }

    for (Room r : rooms) {
        if (r.getRoomNumber() == room.getRoomNumber()) {
            throw new IllegalArgumentException("Room number " + room.getRoomNumber() + " already exists!");
        }
    }

    rooms.add(room);

}
    public static boolean isRoomAvailable(Room room, LocalDate checkIn, LocalDate checkOut) {

    for (Reservation r : reservations) {
        if (r.getRoom().equals(room)) {
            if (checkIn.isBefore(r.getCheckOut()) &&
                checkOut.isAfter(r.getCheckIn())) {
                return false;
            }
        }
    }

    return true;
  }
}

  


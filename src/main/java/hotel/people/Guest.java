/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.people;
import hotel.model.Gender;
import hotel.model.PaymentMethod;
import hotel.reservations.Reservation;
import hotel.reservations.Invoice;
import hotel.rooms.Room;
import hotel.database.HotelDatabase;
import hotel.exceptions.InvalidDataException;
import hotel.exceptions.INVALIDPAYMENTEXCEPTION;
import hotel.exceptions.ROOMNOTAVAILABLEEXCEPTION;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class Guest extends Person {
  private double balance;
    private String roomPreferences;
    private Gender gender;
   

    public Guest(String username, String password, LocalDate dob, double balance, String address, Gender gender, String roomPreferences) {
        super(username, password, dob);
        super(username, password, dob, address);
         if (balance < 0)
            throw new InvalidDataException("Balance cannot be negative.");
         if (gender == null)
            throw new InvalidDataException("Gender is required.");
         this.gender = gender;
        this.balance = balance;
        this.roomPreferences = roomPreferences;
    }

    public void login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Guest login successful!");
        } else {
            System.out.println("Invalid guest credentials.");
        }
    }

 public static Guest register(String username, String password, LocalDate dob,
                                 double balance, String address, Gender gender, String roomPreferences) {
        for (Guest g : HotelDatabase.getGuests()) {
            if (g.getUsername().equals(username))
                throw new InvalidDataException("Username already exists.");
        }
     Guest newGuest = new Guest(username, password, dob, balance, address, gender, roomPreferences);
        HotelDatabase.addGuest(newGuest);
        System.out.println("Guest registered successfully: " + username);
        return newGuest;
    }
     public ArrayList<Room> viewAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        ArrayList<Room> available = new ArrayList<>();
        for (Room r : HotelDatabase.getRooms()) {
            if (HotelDatabase.isRoomAvailable(r, checkIn, checkOut))
                available.add(r);
        }
        return available;
    }
      public Reservation makeReservation(Room room, LocalDate checkIn, LocalDate checkOut) {
        if (!HotelDatabase.isRoomAvailable(room, checkIn, checkOut))
            throw new ROOMNOTAVAILABLEEXCEPTION("Room " + room.getRoomNumber() + " is not available for the selected dates.");

        int id = HotelDatabase.getReservations().size() + 1; // FIX: generate id properly
        Reservation reservation = new Reservation(id, this, room, checkIn, checkOut);
        HotelDatabase.addReservation(reservation);
        room.occupy();
        System.out.println("Reservation made: " + reservation);
        return reservation;
    }
    public void cancelReservation(Reservation r) {
        if (!r.getGuest().getUsername().equals(this.username))
            throw new InvalidDataException("You can only cancel your own reservations.");
        r.cancel();
        System.out.println("Reservation #" + r.getReservationId() + " cancelled.");
    }
     public void viewReservations() { // FIX: was missing entirely
        System.out.println("Reservations for " + username + ":");
        boolean found = false;
        for (Reservation r : HotelDatabase.getReservations()) {
            if (r.getGuest().getUsername().equals(this.username)) {
                System.out.println("  " + r);
                found = true;
            }
        }
        if (!found) System.out.println("  No reservations found.");
    }
    public Invoice checkout(Reservation r, PaymentMethod method) {
        if (!r.getGuest().getUsername().equals(this.username))
            throw new InvalidDataException("You can only check out your own reservations.");

        long days = ChronoUnit.DAYS.between(r.getCheckInDate(), r.getCheckOutDate()); // FIX: correct getter names
        double total = days * r.getRoom().getRoomType().getPrice(); // FIX: use getter chain, not field access

        if (total > balance)
            throw new INVALIDPAYMENTEXCEPTION("Insufficient balance. Required: $" + total + ", Available: $" + balance);

        int invoiceId = HotelDatabase.getInvoices().size() + 1;
        Invoice invoice = new Invoice(invoiceId, r, total, method);
        invoice.processPayment();
        deductBalance(total);
        r.complete();
        HotelDatabase.getInvoices().add(invoice);
        return invoice;
    }
    public void deductBalance(double amount) {
        if (amount > balance)
            throw new INVALIDPAYMENTEXCEPTION("Insufficient balance."); // FIX: was missing 'new'
        balance -= amount;
    }

    public void topUpBalance(double amount) {
        if (amount <= 0)
            throw new InvalidDataException("Top-up amount must be positive.");
        balance += amount;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0)
            throw new InvalidDataException("Balance cannot be negative.");
        this.balance = balance;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null)
            throw new InvalidDataException("Gender is required.");
        this.gender = gender;
    }
    public String getRoomPreferences() {
        return roomPreferences;
    }

    public void setRoomPreferences(String roomPreferences) {
        this.roomPreferences = (roomPreferences != null) ? roomPreferences : "";
    }
    public String toString() {
        return "Guest{username='" + username + "', gender=" + gender +
               ", balance=" + balance + ", address='" + address + "'}";
    }
}

   

    

   
    
    
   

      

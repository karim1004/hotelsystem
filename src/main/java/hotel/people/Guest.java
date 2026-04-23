/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.people;
import hotel.model.Gender;
import java.time.LocalDate;
import hotel.exceptions.InvalidDataException;
import hotel.exceptions.INVALIDPAYMENTEXCEPTION;
import hotel.exceptions.ROOMNOTAVAILABLEEXCEPTION;

public class Guest extends Person {
    private double balance;
    private String roomPreferences;
    private Gender gender;

    public Guest(String username, String password, LocalDate dob, double balance, String address, Gender gender, String roomPreferences) {
        super(username, password, dob);
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

    public void register() {
        System.out.println("Guest registered.");
    }
    public void viewRooms() {
        System.out.println("Viewing available rooms...");
    }
      public Reservation makeReservation(Room room, LocalDate in, LocalDate out)
            throws ROOMNOTAVAILABLEEXCEPTION {
        return new Reservation(this, room, in, out);
    }

    public void cancelReservation(Reservation r) {
        r.cancel();
    }

    public void checkout(Reservation r, PaymentMethod method)
            throws INVALIDPAYMENTEXCEPTION {

        double days = java.time.temporal.ChronoUnit.DAYS.between(
                r.getCheckIn(), r.getCheckOut());

        double total = days * r.getRoom().type.price;

        Invoice invoice = new Invoice(total);
        invoice.processPayment(this, method);

        r.complete();
    }

     public ArrayList<Room> viewAvailableRooms(LocalDate in, LocalDate out) {
        ArrayList<Room> available = new ArrayList<>();
        for (Room r : HotelDatabase.rooms) {
            if (r.isAvailable(in, out))
                available.add(r);
        }
        return available;
    }
    
   private static boolean usernameExists(String username) {
        for (Guest g : HotelDatabase.guests) {
            if (g.getUsername().equals(username))
                return true;
        }
        return false;
    }  

   
     public void setAddress(String address) {
        if (address == null || address.isEmpty())
            throw new InvalidDataException("Invalid address");
        this.address = address;
    }
   
    public void setBalance(double balance) {
        if (balance < 0)
            throw new InvalidDataException("Negative balance");
        this.balance = balance;
    }
    public void setGender(Gender gender) {
        if (gender == null)
            throw new InvalidDataException("Gender required");
        this.gender = gender;
    }

   
    public void setRoomPreferences(String pref) {
        if (pref == null)
            pref = "";
        this.roomPreferences = pref;
    }
    
     public static Guest register(Guest guest) {
        if (usernameExists(guest.getUsername()))
            throw new InvalidDataException("Username already exists");

        HotelDatabase.guests.add(guest);
        return guest;
    }
    
     public void deductBalance(double amount) {
        if (amount > balance)
            throw InvalidDataException("Insufficient balance");
        balance -= amount;
    }
    
     public String getRoomPreferences() {
        return roomPreferences;
    }
public double getBalance() {
    return balance;
}
    public String getAddress() {
        return address; 
    }
    public Gender getGender() {
        return gender;
    }
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.people;

import hotel.model.Gender;
import hotel.model.Role;
import hotel.database.HotelDatabase;
import hotel.exceptions.InvalidDataException;
import java.time.LocalDate;

public abstract class Staff extends Person {
    protected Role role;
    protected int workingHours;
     protected Gender gender;

    public Staff(String username, String password, LocalDate dob, String address, Gender gender, Role role, int workingHours) {
        super(username, password, dob);
        super(username, password, dob, address);
        if (gender == null)
            throw new InvalidDataException("Gender is required.");
        if (role == null)
            throw new InvalidDataException("Role is required.");
         if (workingHours < 0)
            throw new InvalidDataException("Working hours cannot be negative.");
        this.gender = gender;
         this.role = role;
        this.workingHours = workingHours;
    }

    public void login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println(role + " login successful!");
        }
        else {
            System.out.println("Invalid staff credentials.");
        }
    }

     public void viewGuests() {
        System.out.println("=== Guest List ===");
        for (var g : HotelDatabase.getGuests())
            System.out.println("  " + g);
    }
     public void viewRooms() {
        System.out.println("=== Room List ===");
        for (var r : HotelDatabase.getRooms())
            System.out.println("  " + r);
    }
     public void viewReservations() {
        System.out.println("=== All Reservations ===");
        for (var r : HotelDatabase.getReservations())
            System.out.println("  " + r);
     }
   
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        if (role == null)
            throw new InvalidDataException("Role cannot be null.");
        this.role = role;
    }

    public int getWorkingHours() {
        return workingHours;
    }
     public void setWorkingHours(int workingHours) {
        if (workingHours < 0)
            throw new InvalidDataException("Working hours cannot be negative.");
        this.workingHours = workingHours;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        if (gender == null)
            throw new InvalidDataException("Gender is required.");
        this.gender = gender;
    }
      public String toString() {
        return role + "{username='" + username + "', workingHours=" + workingHours + "}";
    }
}


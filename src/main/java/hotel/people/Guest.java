/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.people;



import hotel.model.Gender;
import java.time.LocalDate;

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
    public void makeReservation() {
        System.out.println("Reservation made.");
    }
    public void cancelReservation() {
        System.out.println("Reservation cancelled.");
    }
    public void checkout() {
        System.out.println("Checked out successfully.");
    }

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRoomPreferences() {
        return roomPreferences;
    }
    public void setRoomPreferences(String roomPreferences) {
        this.roomPreferences = roomPreferences;
    }
}

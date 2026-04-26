/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.people;

import hotel.model.Gender;
import hotel.model.Role;
import java.time.LocalDate;
import hotel.reservations.Reservation;
import hotel.exceptions.InvalidDataException;
public class Receptionist extends Staff {

    public Receptionist(String username, String password, LocalDate dob, String address, Gender gender, int workingHours) {
        super(username, password, dob, address, gender, Role.RECEPTIONIST, workingHours);
    }
public void checkIn(Reservation r) {
        if (r == null)
            throw new InvalidDataException("Reservation cannot be null.");
        r.getRoom().occupy();
        System.out.println("Guest checked in: " + r.getGuest().getUsername() +
                           " -> Room " + r.getRoom().getRoomNumber());
    }

    public void checkOut(Reservation r) {
        if (r == null)
            throw new InvalidDataException("Reservation cannot be null.");
        r.complete();
        System.out.println("Guest checked out: " + r.getGuest().getUsername() +
                           " from Room " + r.getRoom().getRoomNumber());
    }
   
}

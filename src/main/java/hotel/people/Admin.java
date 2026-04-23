/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotel.people;

import hotel.model.Gender;
import hotel.model.Role;
import java.time.LocalDate;
import database.HotelDatabase;
import enums.Role;

public class Admin extends Staff {

    public Admin(String username, String password, LocalDate dob, String address, Gender gender, int workingHours) {
        super(username, password, dob, address, gender, Role.ADMIN, workingHours);
    }

  public void addRoom(Room room) {
        HotelDatabase.rooms.add(room);
    }

    public void removeRoom(Room room) {
        HotelDatabase.rooms.remove(room);
    }

    public void updateRoom(Room oldRoom, Room newRoom) {
        int index = HotelDatabase.rooms.indexOf(oldRoom);
        if (index != -1)
            HotelDatabase.rooms.set(index, newRoom);
    }

   
    public void addRoomType(RoomType type) {
        HotelDatabase.roomTypes.add(type);
    }

    public void removeRoomType(RoomType type) {
        HotelDatabase.roomTypes.remove(type);
    }

  
    public void addAmenity(Amenity a) {
        HotelDatabase.amenities.add(a);
    }

    public void removeAmenity(Amenity a) {
        HotelDatabase.amenities.remove(a);
    }



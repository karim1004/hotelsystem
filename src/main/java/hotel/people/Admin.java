/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotel.people;

import hotel.model.Gender;
import hotel.model.Role;
import hotel.database.HotelDatabase;
import hotel.interfaces.Manageable;
import hotel.rooms.Room;
import hotel.rooms.RoomType;
import hotel.rooms.Amenity;
import hotel.exceptions.InvalidDataException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Admin extends Staff implements Manageable<Room> {

    public Admin(String username, String password, LocalDate dob,
                 String address, Gender gender, int workingHours) {
        super(username, password, dob, address, gender, Role.ADMIN, workingHours);
    }

   public void add(Room room) {
        if (room == null)
            throw new InvalidDataException("Room cannot be null.");
        HotelDatabase.addRoom(room);
        System.out.println("Room " + room.getRoomNumber() + " added.");
    }

    public void update(Room oldRoom, Room newRoom) {
        List<Room> rooms = HotelDatabase.getRooms();
        int index = rooms.indexOf(oldRoom);
        if (index != -1) {
            rooms.set(index, newRoom);
            System.out.println("Room updated.");
        } else {
            throw new InvalidDataException("Room not found.");
        }
    }

  public void delete(Room room) {
        boolean removed = HotelDatabase.getRooms().remove(room);
        if (!removed)
            throw new InvalidDataException("Room not found.");
        System.out.println("Room " + room.getRoomNumber() + " removed.");
    }
    public List<Room> viewAll() {
        return new ArrayList<>(HotelDatabase.getRooms());
    }

   
     public void addRoomType(RoomType type) {
        if (type == null)
            throw new InvalidDataException("RoomType cannot be null.");
        HotelDatabase.getRoomTypes().add(type);
        System.out.println("RoomType '" + type.getRoomType() + "' added.");
    }
     public void updateRoomType(RoomType oldType, RoomType newType) { 
        List<RoomType> types = HotelDatabase.getRoomTypes();
        int index = types.indexOf(oldType);
        if (index != -1) {
            types.set(index, newType);
            System.out.println("RoomType updated.");
        } else {
            throw new InvalidDataException("RoomType not found.");
        }
    }
      public void removeRoomType(RoomType type) {
        boolean removed = HotelDatabase.getRoomTypes().remove(type);
        if (!removed)
            throw new InvalidDataException("RoomType not found.");
        System.out.println("RoomType removed.");
    }
     public void addAmenity(Amenity amenity) {
        if (amenity == null)
            throw new InvalidDataException("Amenity cannot be null.");
        HotelDatabase.getAmenities().add(amenity);
        System.out.println("Amenity '" + amenity.getType() + "' added.");
    }
       public void updateAmenity(Amenity oldAmenity, Amenity newAmenity) { 
        List<Amenity> amenities = HotelDatabase.getAmenities();
        int index = amenities.indexOf(oldAmenity);
        if (index != -1) {
            amenities.set(index, newAmenity);
            System.out.println("Amenity updated.");
        } else {
            throw new InvalidDataException("Amenity not found.");
        }
           public void removeAmenity(Amenity amenity) {
        boolean removed = HotelDatabase.getAmenities().remove(amenity);
        if (!removed)
            throw new InvalidDataException("Amenity not found.");
        System.out.println("Amenity removed.");
       }
    }



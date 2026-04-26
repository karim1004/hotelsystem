/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.rooms;

import hotel.model.RoomAvailability;
import java.util.List;
import hotel.exceptions.InvalidDataException;


public class Room {
    private int roomNumber;
    private RoomType roomType ;
    private List<Amenity> amenities ;
    private RoomAvailability availability;

    public Room(int roomNumber, RoomType roomType, List<Amenity> amenities) {
       if (roomNumber <= 0)
            throw new InvalidDataException("Room number must be positive.");
       if (roomType == null)
            throw new InvalidDataException("Room type cannot be null.");
        this.roomNumber = roomNumber; InvalidDataException {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amenities =  (amenities != null) ? amenities : new ArrayList<>();
        this.availability = RoomAvailability.AVAILABLE; 
    }

   
    public void occupy() {
        this.availability = RoomAvailability.OCCUPIED;
    }
    public void free() {
        this.availability = RoomAvailability.AVAILABLE;
    }
    public void maintenance() {
        this.availability = RoomAvailability.UNDER_MAINTENANCE;
    }

    public boolean isAvailable() {
        return this.availability == RoomAvailability.AVAILABLE;
    }    

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        if (roomNumber <= 0) {
            throw new InvalidDataException("Room number must be positive.");
        }
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
         if (roomType == null)
            throw new InvalidDataException("Room type cannot be null.");
        this.roomType = roomType;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<Amenity> amenities) {
          this.amenities = (amenities != null) ? amenities : new ArrayList<>();
       
    }

    public RoomAvailability getAvailability() {
        return availability;
    }
    public void setAvailability(RoomAvailability availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + roomType + ") - " + availability;
    }
}


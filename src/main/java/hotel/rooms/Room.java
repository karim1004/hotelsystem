/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.rooms;

import hotel.model.RoomAvailability;
import java.util.List;

public class Room {
    private int roomNumber;
    private RoomType roomType ;
    private List<Amenity> amenities ;
    private RoomAvailability availability;

    public Room(int roomNumber, RoomType roomType, List<Amenity> amenities) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amenities = amenities;
        this.availability = RoomAvailability.AVAILABLE; // default
    }

    // Methods to change availability
    public void occupy() {
        this.availability = RoomAvailability.OCCUPIED;
    }
    public void free() {
        this.availability = RoomAvailability.AVAILABLE;
    }
    public void maintenance() {
        this.availability = RoomAvailability.UNDER_MAINTENANCE;
    }

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
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


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.rooms;


import hotel.model.AmenityType;
import hotel.exceptions.InvalidDataException;

public class Amenity {
    private AmenityType type;
    private String description;

    public Amenity(AmenityType type, String description) {
        if (type == null)
            throw new InvalidDataException("Amenity type cannot be null.");
        if (description == null || description.trim().isEmpty())
            throw new InvalidDataException("Amenity description cannot be empty.");
        this.type = type;
        this.description = description;
    }

    public AmenityType getType() {
        return type;
    }

    public void setType(AmenityType type) {
        if (type == null)
            throw new InvalidDataException("Amenity type cannot be null.");
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty())
            throw new InvalidDataException("Amenity description cannot be empty.");
        this.description = description;
    }

    @Override
    public String toString() {
        return type + " - " + description;
    }
}




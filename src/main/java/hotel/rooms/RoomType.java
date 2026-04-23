/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.rooms;


public class RoomType {
    private String roomType;
    private double price;

    public RoomType(String roomType,double price) throws InvalidDataException{
        this.roomType = roomType;
        this.price=price;
    }

    public String getroomType() {
        return roomType;
    }
    public void setroomType(String type) throws InvalidDataException {
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidDataException("Room type name cannot be empty."); 
        }
        this.roomType = type;
    }
    public double getprice(){
        return price;
            }
    public  void setprice(double price) throws InvalidDataException {
        if (price < 0) {
            throw new InvalidDataException("Room price cannot be negative."); // 
        }
        this.price = price;
    }

    public String toString() {
        return roomType + " ($" + price + ")";
    }
}



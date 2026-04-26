/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel.people;
import hotel.model.Gender;
import java.time.LocalDate;
import hotel.exceptions.InvalidDataException;

public abstract class Person {
    protected String username;
    protected String password;
    protected LocalDate dateOfBirth;
     protected String address;


   
   public Person(String username, String password, LocalDate dateOfBirth, String address){
       setUsername(username);
        setPassword(password);
        setDateOfBirth(dateOfBirth);
        setAddress(address);
    }

    public abstract void login (String username, String password);

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getAddress() {
        return address;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty())
            throw new InvalidDataException("Username cannot be empty");
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 4)
            throw new InvalidDataException("Password must be at least 4 characters");
        this.password = password;
    }


   public void setDateOfBirth(LocalDate dob) {
        if (dob == null || dob.isAfter(LocalDate.now()))
            throw new InvalidDataException("Invalid date of birth");
        this.dateOfBirth = dob;
    }
      public void setAddress(String address) {
        if (address == null || address.trim().isEmpty())
            throw new InvalidDataException("Address cannot be empty.");
        this.address = address;
    }
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
 public String toString() {
        return "Username: " + username;
    }
}

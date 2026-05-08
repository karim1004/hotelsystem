
package application.controller.java;
import javafx.beans.property.SimpleStringProperty;
 import hotel.rooms.RoomType;
import hotel.people.Guest;
import hotel.reservations.Reservation;
import hotel.model.ReservationStatus;
import hotel.rooms.RoomType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;

public class DashboardController {

    @FXML private Label lblWelcome;
    @FXML private Label lblBalance;
    @FXML private Label lblEmail;

    @FXML private TableView<Reservation> tblReservations;
    @FXML private TableColumn<Reservation, String> colRoom; // From Room object
    @FXML private TableColumn<Reservation, LocalDate> colCheckIn;
    @FXML private TableColumn<Reservation, LocalDate> colCheckOut;
    @FXML private TableColumn<Reservation, ReservationStatus> colStatus;

    // This would be set when the user logs in
    private Guest currentGuest; 

  // This receives the data from the LoginController
    public void setGuest(Guest guest) {
        this.currentGuest = guest;
        
        // Update the labels on the screen with Karim's data
        lblWelcome.setText("Welcome, " + currentGuest.getUsername());
        lblBalance.setText("$" + String.format("%.2f", currentGuest.getBalance()));
    }
    @FXML
    public void initialize() {
        // 1. Link columns to Reservation class getters
        // For simple fields:
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 2. Special case: The Room is an object, so we extract the name/type
colRoom.setCellValueFactory(cellData -> {
    // Add .toString() or .name() at the end to turn it into text
    String roomTypeName = cellData.getValue().getRoom().getRoomType().toString(); 
    return new SimpleStringProperty(roomTypeName);
});}
        // Note: Make sure your Room class has a getRoomType() method!
  // ... (Keep everything above this exactly the same) ...

    private void displayGuestInfo() {
        if (currentGuest != null) {
            lblWelcome.setText("Welcome, " + currentGuest.getUsername());
            lblBalance.setText("$" + String.format("%.2f", currentGuest.getBalance()));
            // lblEmail.setText(currentGuest.getEmail()); 
            
            // Call the method to populate the table
            loadReservations();
        }
    }

    private void loadReservations() {
        // 1. Create a JavaFX ObservableList to hold the data for the table
        ObservableList<Reservation> guestReservations = FXCollections.observableArrayList();

        // 2. Loop through all reservations in the main HotelDatabase
        for (Reservation res : hotel.database.HotelDatabase.getReservations()) {
            
            // 3. If the reservation belongs to the current guest, add it to our list
            if (res.getGuest().getUsername().equals(currentGuest.getUsername())) {
                guestReservations.add(res);
            }
        }

        // 4. Inject the data into the TableView
        tblReservations.setItems(guestReservations);
    }
}
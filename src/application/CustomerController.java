package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Database.DBManagement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class CustomerController implements Initializable{

	@FXML
	Label namelbl;
	@FXML
	Label phonelbl;
	@FXML
	Label usernamelbl;
	@FXML
	Pane ProfilePane;
	@FXML
	Pane CatalogPane;
	@FXML
	Pane ManageReservationPane;
	@FXML
	Pane ReviewPane;
	
	 @FXML
	TableView<Reservation> reservationHistory;
	@FXML
	TableColumn<Reservation, Integer> bookingId;
	@FXML
	TableColumn<Reservation, String> startDate;
	@FXML
	TableColumn<Reservation, String> status;
	
	@FXML
	TableView<Reservation> pendingPeservationHistory;
	@FXML
	TableColumn<Reservation, Integer> bookingId1;
	@FXML
	TableColumn<Reservation, String> startDate1;
	@FXML
	TableColumn<Reservation, String> status1;
	
	@FXML
	TableView<Reservation> reservedCars;
	@FXML
	TableColumn<Reservation, String> model1;
	@FXML
	TableColumn<Reservation, String> color1;
	@FXML
	TableColumn<Reservation, String> plateNum1;
	
	@FXML
	TableView<Car> carsCatalog;
	@FXML
	TableColumn<Car, String> model;
	@FXML
	TableColumn<Car, String> color;
	@FXML
	TableColumn<Car, String> plateNum;
	
	@FXML
	ComboBox searchType;
	@FXML
	TextField searchField;
	@FXML
	TextField newDate;
	@FXML
	TextArea reviewsArea;
	
	int selectedReservation=0;
	
	int selectedReservedCar=0;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
		if(RentalAgencyController.currentUser!=null) {
			namelbl.setText("Name:       "+RentalAgencyController.currentUser.name);
			phonelbl.setText("Phone:       "+RentalAgencyController.currentUser.phone);
			usernamelbl.setText("Username:       "+RentalAgencyController.currentUser.username);
		}
			
		
		ProfilePane.setVisible(true);
		CatalogPane.setVisible(false);
		ManageReservationPane.setVisible(false);
		ReviewPane.setVisible(false);
		
		bookingId.setCellValueFactory(new PropertyValueFactory<>("Id"));
		startDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
	    status.setCellValueFactory(new PropertyValueFactory<>("Status"));	    
	    reservationHistory.getItems().addAll(Reservation.getValidatedReservations());
	    
	    model.setCellValueFactory(new PropertyValueFactory<>("Model"));
		color.setCellValueFactory(new PropertyValueFactory<>("Color"));
	    plateNum.setCellValueFactory(new PropertyValueFactory<>("Plate"));	    
	    carsCatalog.getItems().addAll(RentalAgencyController.cars);
	    
	    bookingId1.setCellValueFactory(new PropertyValueFactory<>("Id"));
		startDate1.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
	    status1.setCellValueFactory(new PropertyValueFactory<>("Status"));	    
	    pendingPeservationHistory.getItems().addAll(Reservation.getPendingReservations());
	    
	    searchType.getItems().add("Model");
		searchType.getItems().add("Color");
		
		model1.setCellValueFactory(new PropertyValueFactory<>("CarModel"));
		color1.setCellValueFactory(new PropertyValueFactory<>("CarColor"));
		plateNum1.setCellValueFactory(new PropertyValueFactory<>("CarPlate"));
		reservedCars.getItems().addAll(Reservation.getValidatedReservations());
		
		carsCatalog.setRowFactory(tv -> {
		    TableRow<Car> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
		            Car selectedCar = row.getItem();
		            TextInputDialog dialog = new TextInputDialog(""); // Create a new text input dialog
				    dialog.setTitle("Text Input Dialog");
				    dialog.setHeaderText("Confirm reservation");
				    dialog.setContentText("Please enter date");
				    Optional<String> result = dialog.showAndWait(); // Display the dialog and wait for it to be closed
				    result.ifPresent(name -> {
				    	String reservationDate = name;
		    			
		    			if (reservationDate != null && !reservationDate.isEmpty()) {
			               
		    				Reservation nr = new Reservation();
		    				nr.status="Pending";
		    				nr.startDate=reservationDate;
		    				nr.user=RentalAgencyController.currentUser;
		    				nr.id=RentalAgencyController.reservations.size()+1;
		    				nr.carColor=selectedCar.color;
		    				nr.carModel=selectedCar.model;
		    				nr.carPlate=selectedCar.plate;
		    				RentalAgencyController.reservations.add(nr);
		    				Alert alert = new Alert(AlertType.INFORMATION);
		    				alert.setTitle("Alert");
		    				alert.setContentText("Reservation done!");
		    				alert.showAndWait();
		    				
			            }else {
			            	Alert alert = new Alert(AlertType.INFORMATION);
			    			alert.setTitle("Alert");
			    			alert.setContentText("Error!");
			    			alert.showAndWait();
			            }
				    });
		        }
		    });
		    return row;
		});
		
		pendingPeservationHistory.setRowFactory(tv -> {
		    TableRow<Reservation> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
		            Reservation data = row.getItem();
		            newDate.setText(data.startDate);
		            selectedReservation=data.id;
		        }
		    });
		    return row;
		});
		
		reservedCars.setRowFactory(tv -> {
		    TableRow<Reservation> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
		            Reservation data = row.getItem();
		            displayReviews(data.carPlate);
		            selectedReservedCar=data.id;
		        }
		    });
		    return row;
		});
		
		
	}
	
	public void displayCars() {
		carsCatalog.getItems().clear();
		carsCatalog.getItems().addAll(RentalAgencyController.cars);
	}
	
	public void displayPendingReservation() {
		
		pendingPeservationHistory.getItems().clear();
		pendingPeservationHistory.getItems().addAll(Reservation.getPendingReservations());
	}
	
	public void displayValidatedReservation() {
		reservationHistory.getItems().clear();
		reservationHistory.getItems().addAll(Reservation.getValidatedReservations());
	}
	
	public void displayValidatedReservationCars() {
		reservedCars.getItems().clear();
		reservedCars.getItems().addAll(Reservation.getValidatedReservations());
	}
	
	
	
	public void displayProfilePane() {
		
		displayValidatedReservation();
		
		ProfilePane.setVisible(true);
		CatalogPane.setVisible(false);
		ManageReservationPane.setVisible(false);
		ReviewPane.setVisible(false);
	}
	
	public void displayReservationPane() {
		
		displayPendingReservation();
		
		ProfilePane.setVisible(false);
		CatalogPane.setVisible(false);
		ManageReservationPane.setVisible(true);
		ReviewPane.setVisible(false);
	}
	
	public void displayCatalogPane() {
		
		displayCars();
		
		ProfilePane.setVisible(false);
		CatalogPane.setVisible(true);
		ManageReservationPane.setVisible(false);
		ReviewPane.setVisible(false);
	}
	
	public void displayReviewPane() {
		
		displayValidatedReservationCars();
		
		ProfilePane.setVisible(false);
		CatalogPane.setVisible(false);
		ManageReservationPane.setVisible(false);
		ReviewPane.setVisible(true);
	}
	
	public void displayReviews(String plate) {
		ArrayList<Reviews> revs = Reviews.getReviews(plate);
		reviewsArea.setText("");
		for(int i=0;i<revs.size();i++) {
			reviewsArea.appendText("\n Car Information [ Model:"+revs.get(i).car.model+" - Color: "+revs.get(i).car.color+" - PlateNum: "+revs.get(i).car.plate+"]");
			reviewsArea.appendText("\n Message : [ "+revs.get(i).message+" ] \n Posted By: ["+revs.get(i).user.username+"]\n-----\n");
		}
		
	}
	
	public void postReview() {
		TextInputDialog dialog = new TextInputDialog(""); // Create a new text input dialog
	    dialog.setTitle("Text Input Dialog");
	    dialog.setHeaderText("Reviews");
	    dialog.setContentText("Please enter your review");
	    Optional<String> result = dialog.showAndWait(); // Display the dialog and wait for it to be closed
	    result.ifPresent(name -> {
	    	String message = name;
			
			if (message != null && !message.isEmpty()) {
               
				Reviews.postReview(selectedReservedCar, message);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert");
				alert.setContentText("Review posted!");
				alert.showAndWait();
				
            }
	    });
	}
	
	public void search() {
		
		String query = searchField.getText();
		
		if(query.equals("")) {
			displayCars();
			return;
		}
		
		if(searchType.getSelectionModel().getSelectedItem().equals("Model") ) {
			Car c=new Car();
			carsCatalog.getItems().clear();
			carsCatalog.getItems().addAll(c.searchByModel(query));
			
		}else {
			Car c=new Car();
			carsCatalog.getItems().clear();
			carsCatalog.getItems().addAll(c.searchByColor(query));
			
		}
		
	}
	
	public void updateReservation() {
		Reservation.updateReservation(selectedReservation, newDate.getText());
		displayPendingReservation();
	}
	
	public void deleteReservation() {
		Reservation.deleteReservation(selectedReservation);
		displayPendingReservation();
	}
	
	@FXML
	public void logout() throws IOException {
		RentalAgencyController.currentUser=null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        AnchorPane root = loader.load();
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
	}
	
	public void sync() {
		DBManagement.sync();
	}
	
}

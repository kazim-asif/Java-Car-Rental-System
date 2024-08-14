package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Database.DBManagement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminController implements Initializable{

	@FXML
	Pane CatalogPane;
	@FXML
	Pane ApprovalPane;
	@FXML
	TableView<Car> carsCatalog;
	@FXML
	TableColumn<Car, String> model;
	@FXML
	TableColumn<Car, String> color;
	@FXML
	TableColumn<Car, String> plateNum;
	
	@FXML
	TableView<Reservation> pendingPeservationHistory;
	@FXML
	TableColumn<Reservation, Integer> bookingId1;
	@FXML
	TableColumn<Reservation, String> startDate1;
	@FXML
	TableColumn<Reservation, String> status1;
	
	@FXML
	TextField addCarModel;
	@FXML
	TextField addCarColor;
	@FXML
	TextField addCarPlate;
	@FXML
	TextField updateCarModel;
	@FXML
	TextField updateCarColor;
	@FXML
	TextField updateCarPlate;
	@FXML
	TextField deleteCarPlate;
	@FXML
	TextArea detailsArea;
	
	int selectedId=0;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		
			CatalogPane.setVisible(true);
			ApprovalPane.setVisible(false);
		
		    model.setCellValueFactory(new PropertyValueFactory<>("Model"));
			color.setCellValueFactory(new PropertyValueFactory<>("Color"));
		    plateNum.setCellValueFactory(new PropertyValueFactory<>("Plate"));	    
		    carsCatalog.getItems().addAll(RentalAgencyController.cars);
		    
		    carsCatalog.setRowFactory(tv -> {
			    TableRow<Car> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
			            Car data = row.getItem();
			            updateCarModel.setText(data.model);
			            updateCarPlate.setText(data.plate);
			            updateCarColor.setText(data.color);
			            deleteCarPlate.setText(data.plate);
			            
			        }
			    });
			    return row;
			});
		    
		    bookingId1.setCellValueFactory(new PropertyValueFactory<>("Id"));
			startDate1.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		    status1.setCellValueFactory(new PropertyValueFactory<>("Status"));	    
		    pendingPeservationHistory.getItems().addAll(Reservation.getAllPendingReservations());
		    
		    pendingPeservationHistory.setRowFactory(tv -> {
			    TableRow<Reservation> row = new TableRow<>();
			    row.setOnMouseClicked(event -> {
			        if (event.getButton() == MouseButton.PRIMARY && !row.isEmpty()) {
			        	Reservation data = row.getItem();
			        	displayPendingReservationDetails(data.id);
			        	selectedId=data.id;
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
		pendingPeservationHistory.getItems().addAll(Reservation.getAllPendingReservations());
	}
	
	
	public void displayCatalogPane() {
		CatalogPane.setVisible(true);
		ApprovalPane.setVisible(false);
	}
	
	public void displayApprovalPane() {
		CatalogPane.setVisible(false);
		ApprovalPane.setVisible(true);
	}
	
	public void addCar() {
		String model=addCarModel.getText();
		String color=addCarColor.getText();
		String plate=addCarPlate.getText();
		
		if(!model.equals("") && !color.equals("") && !plate.equals("")){
		    Car c=new Car(model,color,plate);
		    c.addCar(c);
		    Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setContentText("Added");
			alert.showAndWait();
		    displayCars();
		    
		}
	}
	
	public void updateCar() {
		String model=updateCarModel.getText();
		String color=updateCarColor.getText();
		String plate=updateCarPlate.getText();
		
		if(!model.equals("") && !color.equals("") && !plate.equals("")){
		    Car c=new Car(model,color,plate);
		    c.updateCar(c);
		    Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setContentText("Updated");
			alert.showAndWait();
		    displayCars();
		    
		}
	}
	
	public void deleteCar() {
		String plate=deleteCarPlate.getText();
		
		if(!plate.equals("")){
		    Car c=new Car();
		    c.deleteCar(plate);
		    Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setContentText("Deleted");
			alert.showAndWait();
		    displayCars();
		    
		}
	}
	
	public void displayPendingReservationDetails(int id) {
		detailsArea.setText("");
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).id == id){
				detailsArea.appendText("\n -- Details --\n");
				detailsArea.appendText(" Customer Details [ Name: "+RentalAgencyController.reservations.get(i).user.name+" - Phone: "+RentalAgencyController.reservations.get(i).user.phone+"]\n");
				detailsArea.appendText(" Car Details [ Model: "+RentalAgencyController.reservations.get(i).carModel+" - Color: "+RentalAgencyController.reservations.get(i).carColor+"- Plate: "+RentalAgencyController.reservations.get(i).carPlate+"]\n");
				break;
			}
		}
		
	}
	
	public void approve() {
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).id == selectedId){
				RentalAgencyController.reservations.get(i).status="Validated";
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert");
				alert.setContentText("Approved!");
				alert.showAndWait();
			}
		}
		
		detailsArea.setText("");
		displayPendingReservation();
	}
	
	@FXML
	public void logout() throws IOException {
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

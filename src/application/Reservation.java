package application;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Reservation {

	public int id;
	public User user;
	public String carModel,carPlate,carColor;
	public String status, startDate;
	public Car car;
		
	public Reservation(int id, User user, Car car, String status, String startDate) {
		super();
		this.id = id;
		this.user = user;
		this.car=car;
		this.carColor = car.color;
		this.carModel=car.model;
		this.carPlate=car.plate;
		this.status = status;
		this.startDate = startDate;
	}




	public String getCarModel() {
		return carModel;
	}




	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}




	public String getCarPlate() {
		return carPlate;
	}




	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}




	public String getCarColor() {
		return carColor;
	}




	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	Reservation(){}
	
	public static ArrayList<Reservation> getPendingReservations(){
		ArrayList<Reservation> filter=new ArrayList<>();
		
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).user.username.equalsIgnoreCase(RentalAgencyController.currentUser.username) && RentalAgencyController.reservations.get(i).status.equalsIgnoreCase("pending")){
				
				filter.add(RentalAgencyController.reservations.get(i));
			}
		}
		
		return filter;
	}
	
	public static ArrayList<Reservation> getAllPendingReservations(){
		ArrayList<Reservation> filter=new ArrayList<>();
		
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).status.equalsIgnoreCase("pending")){
				
				filter.add(RentalAgencyController.reservations.get(i));
			}
		}
		
		return filter;
	}

	public static ArrayList<Reservation> getValidatedReservations(){
		ArrayList<Reservation> filter=new ArrayList<>();
		
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).user.username.equalsIgnoreCase(RentalAgencyController.currentUser.username) &&  ("Validated").equalsIgnoreCase(RentalAgencyController.reservations.get(i).status)){
				filter.add(RentalAgencyController.reservations.get(i));
			}
		}
		
		return filter;
	}
	
	public static ArrayList<Reservation> getAllValidatedReservations(){
		ArrayList<Reservation> filter=new ArrayList<>();
		
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(("Validated").equalsIgnoreCase(RentalAgencyController.reservations.get(i).status)){
				filter.add(RentalAgencyController.reservations.get(i));
			}
		}
		
		return filter;
	}
	
	public static void updateReservation(int id, String newDate) {
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).id == id){
				RentalAgencyController.reservations.get(i).startDate = newDate;
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert");
				alert.setContentText("Done");
				alert.showAndWait();
				return;
			}
		}
	}
	
	public static void deleteReservation(int id) {
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).id == id){
				RentalAgencyController.reservations.remove(i);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert");
				alert.setContentText("Done");
				alert.showAndWait();
				return;
			}
		}
	}
}

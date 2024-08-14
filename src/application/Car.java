package application;

import java.util.ArrayList;

public class Car {

	public String model, color, plate;

	public Car(String model, String color, String plate) {
		super();
		this.model = model;
		this.color = color;
		this.plate = plate;
	}
	
	public Car(){}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	
	public ArrayList<Car> searchByModel(String value) {
		ArrayList<Car> filter=new ArrayList<>();
		
		for(int i=0;i<RentalAgencyController.cars.size();i++) {
			if(value.equalsIgnoreCase(RentalAgencyController.cars.get(i).model)){
				filter.add(RentalAgencyController.cars.get(i));
			}
		}
		
		return filter;
	}
	
	
	public ArrayList<Car> searchByColor(String value) {
		ArrayList<Car> filter=new ArrayList<>();
		
		for(int i=0;i<RentalAgencyController.cars.size();i++) {
			if(value.equalsIgnoreCase(RentalAgencyController.cars.get(i).color)){
				filter.add(RentalAgencyController.cars.get(i));
			}
		}
		
		return filter;
	}
	
	public void addCar(Car car) {
		for(int i=0;i<RentalAgencyController.cars.size();i++) {
			if(car.plate.equalsIgnoreCase(RentalAgencyController.cars.get(i).plate)){
				//failure
				return;
			}
		}
		
		RentalAgencyController.cars.add(car);
	}
	
	public void updateCar(Car car) {
		for(int i=0;i<RentalAgencyController.cars.size();i++) {
			if(car.plate.equalsIgnoreCase(RentalAgencyController.cars.get(i).plate)){
				RentalAgencyController.cars.get(i).model=car.model;
				RentalAgencyController.cars.get(i).color=car.color;
			}
		}
		
		
	}
	
	public void deleteCar(String plate) {
		for(int i=0;i<RentalAgencyController.cars.size();i++) {
			if(plate.equalsIgnoreCase(RentalAgencyController.cars.get(i).plate)){
				RentalAgencyController.cars.remove(i);
			}
		}
		
		
	}
	
	
}

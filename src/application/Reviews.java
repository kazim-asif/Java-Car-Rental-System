package application;

import java.util.ArrayList;

public class Reviews {

	public String message;
	public Car car;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User user;
	
	public Reviews(String message, Car car, User user) {
		super();
		this.message = message;
		this.car = car;
		this.user = user;
	}
	
	Reviews(){}
	
	public static ArrayList<Reviews> getReviews(String plate){
		ArrayList<Reviews> filter=new ArrayList<>();
		for(int i=0;i<RentalAgencyController.reviews.size();i++) {
			if( RentalAgencyController.reviews.size()>0 && RentalAgencyController.reviews.get(i).car!=null && RentalAgencyController.reviews.get(i).car.plate!=null && RentalAgencyController.reviews.get(i).car.plate.equalsIgnoreCase(plate)) {
				filter.add(RentalAgencyController.reviews.get(i));
			}
		}
		
		return filter;
	}
	
	public static void postReview(int id, String message) {
		for(int i=0;i<RentalAgencyController.reservations.size();i++) {
			if(RentalAgencyController.reservations.get(i).id==id) {
				Reviews r=new Reviews(message,RentalAgencyController.reservations.get(i).car,RentalAgencyController.reservations.get(i).user);
				RentalAgencyController.reviews.add(r);
				break;
			}
		}
	}
	
}

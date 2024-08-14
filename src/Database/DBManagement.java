package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.Car;
import application.RentalAgencyController;
import application.Reservation;
import application.Reviews;
import application.User;

public class DBManagement {

	public static void getUsersFromDatabase() throws SQLException {
		
		java.sql.Connection connection = Connection.connection;

        // Create a SQL statement to select all records from the User table
        String query = "SELECT * FROM User";

        // Create a PreparedStatement
        PreparedStatement statement = connection.prepareStatement(query);

        // Execute the query and obtain the result set
        ResultSet resultSet = statement.executeQuery();

        // Clear the existing users list
        RentalAgencyController.users.clear();

        // Iterate over the result set and add User objects to the users list
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");

            User user = new User(name, phone, username, password);
            RentalAgencyController.users.add(user);
        }

        // Close the resources
        resultSet.close();
        statement.close();
      //  connection.close();
		
	}
	
	public static void getCarsFromDatabase() {
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Create a SQL statement to select all records from the Car table
	        String query = "SELECT * FROM Car";

	        // Create a PreparedStatement
	        PreparedStatement statement = connection.prepareStatement(query);

	        // Execute the query and obtain the result set
	        ResultSet resultSet = statement.executeQuery();

	        // Clear the existing cars ArrayList
	        RentalAgencyController.cars.clear();

	        // Iterate over the result set and add Car objects to the cars ArrayList
	        while (resultSet.next()) {
	            String model = resultSet.getString("model");
	            String color = resultSet.getString("color");
	            String plate = resultSet.getString("plate");

	            Car car = new Car(model, color, plate);
	            RentalAgencyController.cars.add(car);
	        }

	        // Close the resources
	        resultSet.close();
	        statement.close();
	    //    connection.close();

	        System.out.println("Successfully retrieved cars from the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to retrieve cars from the database.");
	        e.printStackTrace();
	    }
	}
	
	public static void getReservationsFromDatabase() {
	    
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Create a SQL statement to select all records from the Reservation table
	        String query = "SELECT * FROM Reservation";

	        // Create a PreparedStatement
	        PreparedStatement statement = connection.prepareStatement(query);

	        // Execute the query and obtain the result set
	        ResultSet resultSet = statement.executeQuery();

	        // Iterate over the result set and create Reservation objects
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String carModel = resultSet.getString("carModel");
	            String carPlate = resultSet.getString("carPlate");
	            String carColor = resultSet.getString("carColor");
	            String username = resultSet.getString("username");
	            String status = resultSet.getString("status");
	            String startDate = resultSet.getString("startDate");

	            Car car=new Car(carModel, carColor,carPlate);
	            User user=new User();
	            for(int i=0;i<RentalAgencyController.users.size();i++) {
	            	if(RentalAgencyController.users.get(i).username.equalsIgnoreCase(username))
	            		user=RentalAgencyController.users.get(i);
	            }
	            
	            Reservation reservation = new Reservation(id,user, car , status, startDate);
	            RentalAgencyController.reservations.add(reservation);
	        }

	        // Close the resources
	        resultSet.close();
	        statement.close();
	      //  connection.close();

	        System.out.println("Successfully retrieved reservations from the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to retrieve reservations from the database.");
	        e.printStackTrace();
	    }

	   
	}
	
	public static void getReviewsFromDatabase() {
	    
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Create a SQL statement to select all records from the Reviews table
	        String query = "SELECT * FROM Reviews";

	        // Create a PreparedStatement
	        PreparedStatement statement = connection.prepareStatement(query);

	        // Execute the query and obtain the result set
	        ResultSet resultSet = statement.executeQuery();

	        // Iterate over the result set and create Review objects
	        while (resultSet.next()) {
	            String username = resultSet.getString("username");
	            String plateNum = resultSet.getString("plateNum");
	            String message = resultSet.getString("message");

	            User user=new User();
	            for(int i=0;i<RentalAgencyController.users.size();i++) {
	            	if(RentalAgencyController.users.get(i).username.equalsIgnoreCase(username))
	            		user=RentalAgencyController.users.get(i);
	            }
	            
	            Car car=new Car();
	            for(int i=0;i<RentalAgencyController.cars.size();i++) {
	            	if(RentalAgencyController.cars.get(i).plate.equalsIgnoreCase(plateNum))
	            		car=RentalAgencyController.cars.get(i);
	            }
	            
	            Reviews review = new Reviews(message, car, user);
	            RentalAgencyController.reviews.add(review);
	        }

	        // Close the resources
	        resultSet.close();
	        statement.close();
	    //    connection.close();

	        System.out.println("Successfully retrieved reviews from the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to retrieve reviews from the database.");
	        e.printStackTrace();
	    }

	    
	}
	
	public static void readData() throws SQLException{
		getUsersFromDatabase();
		getCarsFromDatabase();
		getReservationsFromDatabase();
		getReviewsFromDatabase();
				
	}
	
	
	public static void saveUsersToDatabase() {
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Clear the existing records from the Users table
	        String deleteQuery = "DELETE FROM User";
	        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	        deleteStatement.executeUpdate();

	        // Create a SQL statement to insert records into the Users table
	        String insertQuery = "INSERT INTO User (name, phone, username, password) VALUES (?, ?, ?, ?)";
	        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

	        // Iterate over the users list and insert records into the Users table
	        for (User user : RentalAgencyController.users) {
	            insertStatement.setString(1, user.getName());
	            insertStatement.setString(2, user.getPhone());
	            insertStatement.setString(3, user.getUsername());
	            insertStatement.setString(4, user.getPassword());
	            insertStatement.executeUpdate();
	        }

	        // Close the resources
	        insertStatement.close();
	        deleteStatement.close();
	 //       connection.close();

	        System.out.println("Successfully saved users to the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to save users to the database.");
	        e.printStackTrace();
	    }
	}


	public static void saveCarsToDatabase() {
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Clear the existing records from the Cars table
	        String deleteQuery = "DELETE FROM Car";
	        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	        deleteStatement.executeUpdate();

	        // Create a SQL statement to insert records into the Cars table
	        String insertQuery = "INSERT INTO Car (model, color, plate) VALUES (?, ?, ?)";
	        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

	        // Iterate over the cars list and insert records into the Cars table
	        for (Car car : RentalAgencyController.cars) {
	            insertStatement.setString(1, car.getModel());
	            insertStatement.setString(2, car.getColor());
	            insertStatement.setString(3, car.getPlate());
	            insertStatement.executeUpdate();
	        }

	        // Close the resources
	        insertStatement.close();
	        deleteStatement.close();
	//        connection.close();

	        System.out.println("Successfully saved cars to the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to save cars to the database.");
	        e.printStackTrace();
	    }
	}

	
	public static void saveReservationsToDatabase() {
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Clear the existing records from the Reservations table
	        String deleteQuery = "DELETE FROM Reservation";
	        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	        deleteStatement.executeUpdate();

	        // Create a SQL statement to insert records into the Reservations table
	        String insertQuery = "INSERT INTO Reservation (id, carModel, carPlate, carColor, username, status, startDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

	        // Iterate over the reservations list and insert records into the Reservations table
	        for (Reservation reservation : RentalAgencyController.reservations) {
	            insertStatement.setInt(1, reservation.getId());
	            insertStatement.setString(2, reservation.getCarModel());
	            insertStatement.setString(3, reservation.getCarPlate());
	            insertStatement.setString(4, reservation.getCarColor());
	            insertStatement.setString(5, reservation.getUser().getUsername());
	            insertStatement.setString(6, reservation.getStatus());
	            insertStatement.setString(7, reservation.getStartDate());
	            insertStatement.executeUpdate();
	        }

	        // Close the resources
	        insertStatement.close();
	        deleteStatement.close();
	//        connection.close();

	        System.out.println("Successfully saved reservations to the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to save reservations to the database.");
	        e.printStackTrace();
	    }
	}


	public static void saveReviewsToDatabase() {
	    try {
	        // Connect to the database
	    	java.sql.Connection connection = Connection.connection;

	        // Clear the existing records from the Reviews table
	        String deleteQuery = "DELETE FROM Reviews";
	        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
	        deleteStatement.executeUpdate();

	        // Create a SQL statement to insert records into the Reviews table
	        String insertQuery = "INSERT INTO Reviews (username, plateNum, message) VALUES (?, ?, ?)";
	        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

	        // Iterate over the reviews list and insert records into the Reviews table
	        for (Reviews review : RentalAgencyController.reviews) {
	            insertStatement.setString(1, review.getUser().getUsername());
	            insertStatement.setString(2, review.getCar().getPlate());
	            insertStatement.setString(3, review.getMessage());
	            insertStatement.executeUpdate();
	        }

	        // Close the resources
	        insertStatement.close();
	        deleteStatement.close();
//	        connection.close();

	        System.out.println("Successfully saved reviews to the database.");
	    } catch (SQLException e) {
	        System.out.println("Failed to save reviews to the database.");
	        e.printStackTrace();
	    }
	}

	public static void sync() {
		saveUsersToDatabase();
		saveCarsToDatabase();
		saveReservationsToDatabase();
		saveReviewsToDatabase();
			
	}
	
}

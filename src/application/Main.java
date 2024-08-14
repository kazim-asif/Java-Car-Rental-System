package application;
	
import java.sql.SQLException;

import Database.Connection;
import Database.DBManagement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        AnchorPane root = loader.load();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
    }
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		RentalAgencyController rs=new RentalAgencyController();
		
		Connection.connect();
		DBManagement.readData();
		
		launch(args);
	}
}

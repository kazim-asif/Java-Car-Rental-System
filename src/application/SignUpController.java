package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignUpController {

	@FXML
	TextField name;
	@FXML
	TextField phone;
	@FXML
	TextField username;
	@FXML
	TextField password;
	
	
	public void register() throws Exception {
		String Name = name.getText();
		String Phone = phone.getText();
		String Username = username.getText();
		String Password = password.getText();

		if( !Name.equals(" ") && !Phone.equals(" ") && !Username.equals(" ") && !Password.equals(" ")) {
			
			for(int i=0;i<RentalAgencyController.users.size();i++) {
				if(Username.equalsIgnoreCase(RentalAgencyController.users.get(i).username)) {
					//break and alerts
					return;
				}
			}
			
			User newUser = new User(Name, Phone, Username, Password);
			RentalAgencyController.users.add(newUser);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setContentText("Registeration successfull! Please login now.");
			alert.showAndWait();
			
			redirectToLogin();
			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setContentText("Fill all fields");
			alert.showAndWait();
		}
		
	}
	
	@FXML
	public void redirectToLogin() throws Exception{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        AnchorPane root = loader.load();
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
    }
	
}

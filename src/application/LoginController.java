package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController implements Initializable{

	@FXML
	TextField username;
	@FXML
	TextField password;
	@FXML
	ComboBox logintype;
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		logintype.getItems().add("Customer");
		logintype.getItems().add("Admin");
	}
	
	
	
	public void standardLogin() throws Exception {
		String UserName = username.getText();
		String Password = password.getText();
		
		if( !UserName.equals(" ") && !Password.equals(" ")) {
			
			if(logintype.getSelectionModel().getSelectedItem().equals("Customer") ) {
				for(int i=0;i<RentalAgencyController.users.size();i++) {
					if(UserName.equalsIgnoreCase(RentalAgencyController.users.get(i).username) && 
							Password.equalsIgnoreCase(RentalAgencyController.users.get(i).password)) {
						
						RentalAgencyController.currentUser=RentalAgencyController.users.get(i);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Alert");
						alert.setContentText("Done!");
						alert.showAndWait();
						redirectToCustomerModule();
						
					}
				}
			}else {
				if(UserName.equalsIgnoreCase("admin") && 
						Password.equalsIgnoreCase("admin")) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Alert");
					alert.setContentText("Done!");
					alert.showAndWait();
					redirectToAdminModule();
					
				}
			}
			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setContentText("Fill all fields");
			alert.showAndWait();
		}
		
	}
	
	public void guestLogin() throws Exception {
		redirectToGuestModule();
	}
	
	@FXML
	public void redirectToSignup() throws Exception{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Signup.fxml"));
        AnchorPane root = loader.load();
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
    }
	
	@FXML
	public void redirectToCustomerModule() throws Exception{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/CustomerModule.fxml"));
        AnchorPane root = loader.load();
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
    }
	
	@FXML
	public void redirectToAdminModule() throws Exception{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/AdminModule.fxml"));
        AnchorPane root = loader.load();
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
    }
	
	@FXML
	public void redirectToGuestModule() throws Exception{
		
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/GuestModule.fxml"));
        AnchorPane root = loader.load();
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Rental System");
        primaryStage.setScene(new Scene(root, 600, 675));
        primaryStage.show();
    }
	
	
	
}

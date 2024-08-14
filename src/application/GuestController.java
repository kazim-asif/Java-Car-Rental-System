package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuestController implements Initializable{

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
	
	
	@Override
    public void initialize(URL location, ResourceBundle resources) {
		model.setCellValueFactory(new PropertyValueFactory<>("Model"));
		color.setCellValueFactory(new PropertyValueFactory<>("Color"));
	    plateNum.setCellValueFactory(new PropertyValueFactory<>("Plate"));	    
	    carsCatalog.getItems().addAll(RentalAgencyController.cars);
	    
	    searchType.getItems().add("Model");
		searchType.getItems().add("Color");
	}
	
	public void displayCars() {
		carsCatalog.getItems().clear();
		carsCatalog.getItems().addAll(RentalAgencyController.cars);
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
   
   @FXML
	public void logout() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
       AnchorPane root = loader.load();
       Stage primaryStage=new Stage();
       primaryStage.setTitle("Rental System");
       primaryStage.setScene(new Scene(root, 600, 675));
       primaryStage.show();
	}
}

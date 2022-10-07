package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class HomeController implements Initializable{

	@FXML
    private HBox Employees;

    @FXML
    private HBox Products;

    @FXML
    private HBox Services;
    @FXML
    private AnchorPane Container;

    public Parent fxml;
    
    @FXML
    void toEmployees() throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("Employees.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    }

    @FXML
    void toProducts() throws IOException {
    	fxml = FXMLLoader.load(getClass().getResource("Products.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    }

    @FXML
    void toServices() throws IOException {
    	
    	fxml = FXMLLoader.load(getClass().getResource("Shopping.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

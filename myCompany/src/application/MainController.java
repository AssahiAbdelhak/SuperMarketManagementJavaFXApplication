package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable{

	@FXML
    private AnchorPane fluidContainer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			fluidContainer.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//fluidContainer
	}

}

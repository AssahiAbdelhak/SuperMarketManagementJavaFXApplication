package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
public class SignUpController implements Initializable{

	@FXML
    private ComboBox<String> combobox;

	@FXML
    private Button SignUpBtn;

    @FXML
    private TextField admin;

    @FXML
    private TextField password;

    @FXML
    private TextField userName;
    @FXML
    private AnchorPane Container;

    @FXML
    void insertInfos() throws ClassNotFoundException, SQLException, IOException {

    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	
    	String sql = "INSERT INTO users (name,password,type,admin) VALUES ('"+userName.getText()+"','"+password.getText()+"','"+combobox.getValue()+"','"+admin.getText()+"');";
//    	String sql = "INSERT INTO users (name,password,type,admin) VALUES ('hello','hello','hello','hello')";

    	Statement statement = connection.createStatement();
    	statement.executeUpdate(sql);
    	
    	Parent fxml = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combobox.getItems().addAll("Option 1","Option 2","Option 3");
	}

}

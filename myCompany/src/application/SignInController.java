package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SignInController implements Initializable {

	@FXML
    private Button SignUpBtn;

    @FXML
    private TextField password;

    @FXML
    private Button signInBtn;

    @FXML
    private TextField userName;
    
    @FXML
    private Label forgot;

    @FXML
    private Label msg;
    
    @FXML
    private AnchorPane Container;

    @FXML
    void typeEmail() throws AddressException, MessagingException {
   
//    	System.out.println("Preparing...");
//    	Properties properties = new Properties();
//    	properties.put("mail.smtp.auth", "true");
////    	properties.put("mail.smtp.starttls.enable", "true");
//    	properties.put("mail.smtp.starttls.enable", "true");
//    	properties.put("mail.smtp.host", "smtp.gmail.com");
//    	properties.put("mail.smtp.port", "587");
//    	
//    	String myAccount = "bdalhqa89@gmail.com";
//    	String pass = "abdelhak2016";
//    	System.out.println("Connection...");
//    	Session session = Session.getInstance(properties, new Authenticator() {
//    	    @Override
//    	    protected PasswordAuthentication getPasswordAuthentication() {
//    	        return new PasswordAuthentication(myAccount, pass);
//    	    }
//    	});
//    	System.out.println("Connected succefully");
//    	//session.setDebug(true);
//    	
//    	MimeMessage msg = new MimeMessage(session);
//    	System.out.println("Message sent");
//    	msg.setFrom(new InternetAddress(myAccount));
//    	msg.setRecipient(Message.RecipientType.TO,new InternetAddress("non.replymycompany@gmail.com"));
//    	msg.setSubject("recovery");
//    	msg.setText("Hello World");
//    	Transport.send(msg);
//    	System.out.println("Message sent");
    }
    
    @FXML
    void changeToSignUp() throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    	
    }
    
    void changeToHome() throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    	
    }

    @FXML
    Boolean checkInfos() throws ClassNotFoundException, SQLException, IOException {

    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	String userNameProv = "select name from users where name='"+userName.getText()+"'";
    	
    	Statement statement = connection.createStatement();
    	ResultSet userNameBrought = statement.executeQuery(userNameProv);
    	if(userNameBrought.next())
    		System.out.println("Yes existes!!!!");
    	else {
    		msg.setText("User Name or password are incorrect !!!");
    		return false;
    	}
    	String passwordProv = "select password from users where name='"+userName.getText()+"'";
    	ResultSet passwordBrought = statement.executeQuery(passwordProv);
    	if(passwordBrought.next()) {
    		if(passwordBrought.getString("password").equals(password.getText())) {
    			msg.setText("Identification Succes");
    			changeToHome();
    		}
    		else {
    			msg.setText("User Name or password are incorrect !!!");
    			return false;
    		}
    	}
    	return false;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}

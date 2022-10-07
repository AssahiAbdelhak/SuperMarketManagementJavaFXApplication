package application;

import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ShoppingController implements Initializable{

	@FXML
    private ImageView back;

    @FXML
    private Button buy;

    @FXML
    private TextField discount;

    @FXML
    private TextArea facture;

    @FXML
    private TextField name;

    @FXML
    private TextField number;

    @FXML
    private TextField price;

    @FXML
    private Button print;

    @FXML
    private Label total;
    
    @FXML
    private AnchorPane Container;

    @FXML
    void back() throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    }

    @FXML
    void buy() {

    	if(price.getText()==""||discount.getText()==""||number.getText()=="") {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("one or many fields are empty");
			alert.showAndWait();
    	}else {
    		int ttl = (Integer.parseInt(price.getText())-Integer.parseInt(discount.getText()))*Integer.parseInt(number.getText());
    		System.out.println(ttl);
    		System.out.printf("%-15s|",name.getText());
    		facture.setText(facture.getText()+String.format("%-20s", name.getText())+" X "+String.format("%-8s", number.getText())+" = "+String.format("%s", String.valueOf(ttl)+"\n"));
    		total.setText(String.valueOf(Integer.parseInt(total.getText())+ttl));
    	}
    	
    }

    @FXML
    void filter() throws ParseException, ClassNotFoundException, SQLException {
    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	String sql = "Select * from products;";
    	Statement statement = connection.createStatement();
    	ResultSet res = statement.executeQuery(sql);
    	
    	while(res.next()) {
    		System.out.print(res.getString("name").equals(name.getText()));
    		if(res.getString("name").equals(name.getText())) {
    			price.setText(String.valueOf(res.getInt("price")));
    			discount.setText(String.valueOf(res.getInt("discount")));
    			number.setFocusTraversable(true);
    			break;
    		}else {
    			price.setText("");
    			discount.setText("");
    		}
    	}
    }

    @FXML
    void print() throws DocumentException, IOException {
    	Document doc = new Document();
    	PdfWriter.getInstance(doc, new FileOutputStream("facture.pdf"));
    	doc.open();
    	
    	Image logo = Image.getInstance("C:\\Users\\hp\\eclipse-workspace\\myCompany\\src\\images\\logo.png");
    	logo.setWidthPercentage(50);
    	logo.setAlignment(Element.ALIGN_RIGHT);
    	doc.add(logo);
    	
    	doc.add(new Paragraph());
    	doc.add(new Paragraph("Your ticket is here : "));
    	Paragraph p = new Paragraph(facture.getText());
    	doc.add(p);
    	doc.add(new Paragraph());
    	doc.add(new Paragraph("you have to pay"+total.getText()));
    	doc.close();
    	
    	Desktop.getDesktop().open(new File("facture.pdf"));
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}

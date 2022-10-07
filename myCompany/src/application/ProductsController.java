package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductsController implements Initializable{

	@FXML
    private Button addPro;

    @FXML
    private ImageView back;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private Button delPro;

    @FXML
    private TextField discount;

    @FXML
    private TableColumn<Products,Integer> discountField;

    @FXML
    private TableColumn<Products,Integer> idField;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Products,String> nameField;

    @FXML
    private TextField number;

    @FXML
    private TableColumn<Products,Integer> numberField;

    @FXML
    private TextField price;

    @FXML
    private TableColumn<Products,Integer> priceField;

    @FXML
    private ImageView search;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Products> table;

    @FXML
    private TableColumn<Products,String> typeField;

    @FXML
    private Button updPro;
    
    @FXML
    private AnchorPane Container;


    @FXML
    void addPro() throws SQLException, ClassNotFoundException {
//    	new Products(result.getInt("id"),result.getString("name"),result.getInt("number"),result.getFloat("price"),result.getString("type"),result.getInt("discount")))
    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	String sql = "INSERT INTO products values (default,'"+name.getText()+"',"+Integer.parseInt(number.getText())+",'"+Float.parseFloat(price.getText())+"','"+combobox.getValue()+"','"+Integer.parseInt(discount.getText())+"');";
    	Statement statement = connection.createStatement();
    	statement.executeUpdate(sql);
    	refreshTable();
    }

    @FXML
    void back() throws IOException {
    	Parent fxml = FXMLLoader.load(getClass().getResource("Home.fxml"));
    	Container.getChildren().clear();
    	Container.getChildren().add(fxml);
    }

    @FXML
    void delPro() throws SQLException, ClassNotFoundException {
    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	Products product = table.getSelectionModel().getSelectedItem();
    	if(product!=null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setHeaderText("You are about to delete this employee.");
    		alert.setContentText("Are you sure deleting this employee ?");
    		Optional<ButtonType> res = alert.showAndWait();
    		if(res.get()==ButtonType.OK) {
    		String sql = "delete from products where id="+product.getId()+";";
    	
    		Statement statement = connection.createStatement();
    		statement.executeUpdate(sql);
    	
    		refreshTable();
    		}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("You should select one product before deleting");
    		
    		alert.showAndWait();
    	}
    }

    @FXML
    void filter() {
    	refreshTable();
		int i = 0;
		ObservableList<Products> prods = table.getItems();
		for (;i<prods.size();i++) {
			if(!(prods.get(i).getName().toLowerCase().contains(searchField.getText().toLowerCase()))) {
				prods.remove(i);
				i--;
			}
			
		}
    }

    @FXML
    void updPro() throws SQLException, ClassNotFoundException {
    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	
    	Products prod = table.getSelectionModel().getSelectedItem();
    	if(prod!=null) {
    		if(idField.getText()==""||name.getText()==""||price.getText()==""||discount.getText()==""||combobox.getValue()==null) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setContentText("one or many fields are empty");
    			alert.showAndWait();
    		}else {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setContentText("Are you sure about that ?");
    			Optional<ButtonType> res = alert.showAndWait();
    			if(res.get()==ButtonType.OK) {
    				Statement statement = connection.createStatement();
    				int id = prod.getId();
    								  //    			   '"+name.getText()+"',"+Integer.parseInt(salary.getText())+",'"+date.getValue().toString()+"','"+combobox.getValue()+"','"+city.getText()+"','"+street.getText()+"');"
    				System.out.println();
//    				int id,String name,int number,float price,String type,int discount
    				String sql = "update products set name='"+name.getText()+"', price="+Float.parseFloat(price.getText())+", type = '"+combobox.getValue()+"', number="+Integer.parseInt(number.getText())+", discount="+Integer.parseInt(discount.getText()) +" where id="+id+";";
    				statement.executeUpdate(sql);
    				refreshTable();
    			}
    		}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("You should select an employee !!");
    		alert.showAndWait();
    	}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		idField.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameField.setCellValueFactory(new PropertyValueFactory<>("name"));
		numberField.setCellValueFactory(new PropertyValueFactory<>("number"));
		priceField.setCellValueFactory(new PropertyValueFactory<>("price"));
		typeField.setCellValueFactory(new PropertyValueFactory<>("type"));
		discountField.setCellValueFactory(new PropertyValueFactory<>("discount"));
		
		
		combobox.getItems().addAll("foods","drinks","fruits","vegetables","other");
		refreshTable();
	}
	public ObservableList<Products> observablelist;
	void refreshTable() {
    	observablelist = FXCollections.observableArrayList();
		
		try {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();
			
			String sql = "Select * From products;";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
//													
				observablelist.add(new Products(result.getInt("id"),result.getString("name"),result.getInt("number"),result.getFloat("price"),result.getString("type"),result.getInt("discount")));
			}
			table.setItems(observablelist);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@FXML
    void fillFields() throws ParseException {
    	if(table.getSelectionModel().getSelectedItem()!=null) {
    		Products prod = table.getSelectionModel().getSelectedItem();
    		name.setText(prod.getName());
    		number.setText(String.valueOf(prod.getNumber()));
    		
    		combobox.setValue(prod.getType());
    		
    		price.setText(String.valueOf(prod.getPrice()));
    		discount.setText(String.valueOf(prod.getDiscount()));
    	}
    }
}

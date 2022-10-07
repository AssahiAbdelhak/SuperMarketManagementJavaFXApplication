package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class EmployeesController implements Initializable{

	public ObservableList<Employee> observablelist;
	
	@FXML
    private AnchorPane Container;

    @FXML
    private Button addEmployee;

    @FXML
    private ImageView back;

    @FXML
    private TextField city;

    @FXML
    private TableColumn<Employee, String> cityField;

    @FXML
    private TableColumn<Employee,Integer> salaryField;
    
    @FXML
    private ComboBox<String> combobox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button delEmployee;

    @FXML
    private TableColumn<Employee, Integer> idField;

    @FXML
    private TableColumn<Employee, String> joinDateField;

    @FXML
    private TextField name;

    @FXML
    private TableColumn<Employee, String> nameField;

    @FXML
    private TableColumn<Employee, String> rankField;

    @FXML
    private TextField salary;

    @FXML
    private ImageView search;

    @FXML
    private TextField street;

    @FXML
    private TableColumn<Employee, String> streetField;

    @FXML
    private TableView<Employee> table;

    @FXML
    private Button updEmployee;

    @FXML
    private TextField searchField;
    
    
    
    @FXML
    void addEmployee() throws SQLException, ClassNotFoundException {

    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	String sql = "INSERT INTO emplyees values (default,'"+name.getText()+"',"+Float.parseFloat(salary.getText())+",'"+datePicker.getValue().toString()+"','"+combobox.getValue()+"','"+city.getText()+"','"+street.getText()+"');";
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
    void delEmployee() throws ClassNotFoundException, SQLException {

    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	Employee emp = table.getSelectionModel().getSelectedItem();
    	if(emp!=null) {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setHeaderText("You are about to delete this employee.");
    		alert.setContentText("Are you sure deleting this employee ?");
    		Optional<ButtonType> res = alert.showAndWait();
    		if(res.get()==ButtonType.OK) {
    		String sql = "delete from emplyees where id="+emp.getId()+";";
    	
    		Statement statement = connection.createStatement();
    		statement.executeUpdate(sql);
    	
    		refreshTable();
    		}
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("You should select one Employee bedore deleting");
    		
    		alert.showAndWait();
    	}
    	
    }

    @FXML
    void filter() {
    		refreshTable();
    		int i = 0;
    		ObservableList<Employee> emps = table.getItems();
    		for (;i<emps.size();i++) {
    			if(!(emps.get(i).getName().toLowerCase().contains(searchField.getText().toLowerCase()))) {
    				emps.remove(i);
    				i--;
    			}
    			
    		}
    	
    }

    @FXML
    void updEmployee() throws SQLException, ClassNotFoundException {
    	
    	ConnectionClass connectionClass = new ConnectionClass();
    	Connection connection = connectionClass.getConnection();
    	
    	Employee emp = table.getSelectionModel().getSelectedItem();
    	if(emp!=null) {
    		if(idField.getText()==""||nameField.getText()==""||cityField.getText()==""||streetField.getText()==""||nameField.getText()==""||salaryField.getText()==""||datePicker.getValue()==null||combobox.getValue()==null) {
    			Alert alert = new Alert(AlertType.ERROR);
    			alert.setContentText("one or many fields are empty");
    			alert.showAndWait();
    		}else {
    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setContentText("Are you sure about that ?");
    			Optional<ButtonType> res = alert.showAndWait();
    			if(res.get()==ButtonType.OK) {
    				Statement statement = connection.createStatement();
    				int id = emp.getId();
    								  //    			   '"+name.getText()+"',"+Integer.parseInt(salary.getText())+",'"+date.getValue().toString()+"','"+combobox.getValue()+"','"+city.getText()+"','"+street.getText()+"');"
    				System.out.println();
    				String sql = "update emplyees set name='"+name.getText()+"', salary="+Float.parseFloat(salary.getText())+", rank = '"+combobox.getValue()+"', joindate='"+datePicker.getValue().toString()+"', city='"+city.getText()+"', street='"+street.getText() +"' where id="+id+";";
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
	
    void refreshTable() {
    	observablelist = FXCollections.observableArrayList();
		
		try {
			ConnectionClass connectionClass = new ConnectionClass();
			Connection connection = connectionClass.getConnection();
			
			String sql = "Select * From emplyees;";
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
//													
				observablelist.add(new Employee(result.getInt("id"),result.getString("name"),result.getFloat("salary"),result.getString("joindate"),result.getString("rank"),result.getString("city"),result.getString("street")));
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
    		Employee emp = table.getSelectionModel().getSelectedItem();
    		name.setText(emp.getName());
    		salary.setText(String.valueOf(emp.getSalary()));
    		
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		
    		LocalDate date = LocalDate.parse(emp.getJoin_date(), formatter);
    		datePicker.setValue(date);
    		combobox.setValue(emp.getRank());
    		
    		city.setText(emp.getCity());
    		street.setText(emp.getStreet());
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// the argument should match with Class's properties
		idField.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameField.setCellValueFactory(new PropertyValueFactory<>("name"));
		salaryField.setCellValueFactory(new PropertyValueFactory<>("salary"));
		joinDateField.setCellValueFactory(new PropertyValueFactory<>("join_date"));
		rankField.setCellValueFactory(new PropertyValueFactory<>("rank"));
		cityField.setCellValueFactory(new PropertyValueFactory<>("city"));
		streetField.setCellValueFactory(new PropertyValueFactory<>("street"));
		
		
		combobox.getItems().addAll("Admin","Client","cashier","Storekepper","Manager","President","Seller");
		refreshTable();
		
	}
	
	

}

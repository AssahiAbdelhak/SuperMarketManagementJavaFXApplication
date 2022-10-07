package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
	
	private SimpleIntegerProperty id;
	private SimpleFloatProperty salary;
	
	private SimpleStringProperty name;
	private SimpleStringProperty join_date;
	private SimpleStringProperty rank;
	private SimpleStringProperty city;
	private SimpleStringProperty street;
	
	Employee(int id,String name,float salary,String join_date,String rank,String city,String street){
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.salary = new SimpleFloatProperty(salary);
		this.join_date = new SimpleStringProperty( join_date);
		this.rank = new SimpleStringProperty( rank);
		this.city = new SimpleStringProperty( city);
		this.street = new SimpleStringProperty( street);
	}

	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id =  new SimpleIntegerProperty(id);
	}

	public float getSalary() {
		return salary.get();
	}

	public void setSalary(float salary) {
		this.salary = new SimpleFloatProperty(salary);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getJoin_date() {
		return join_date.get();
	}

	public void setJoin_date(String join_date) {
		this.join_date = new SimpleStringProperty(join_date);
	}

	public String getCity() {
		return city.get();
	}

	public void setCity(String city) {
		this.city = new SimpleStringProperty(city);
	}

	public String getRank() {
		return rank.get();
	}

	public void setRank(String rank) {
		this.rank = new SimpleStringProperty( rank);
	}

	public String getStreet() {
		return street.get();
	}

	public void setStreet(String street) {
		this.street = new SimpleStringProperty(street);
	}

}

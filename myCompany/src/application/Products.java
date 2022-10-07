package application;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
	
	private SimpleIntegerProperty id;
	private SimpleIntegerProperty discount;
	private SimpleIntegerProperty number;
	private SimpleFloatProperty price;
	
	private SimpleStringProperty name;
	private SimpleStringProperty type;
	
	Products(int id,String name,int number,float price,String type,int discount){
		this.id = new SimpleIntegerProperty(id);
		this.price = new SimpleFloatProperty(price);
		this.discount = new SimpleIntegerProperty(discount);
		this.number = new SimpleIntegerProperty( number);
		this.name  = new SimpleStringProperty(name);
		this.type  = new SimpleStringProperty(type);
	}

	public int getId() {
		return id.get();
	}
	
	public void setId(int id) {
		this.id =  new SimpleIntegerProperty(id);
	}

	public int getDiscount() {
		return discount.get();
	}
	
	public void setDiscount(int discount) {
		this.discount =  new SimpleIntegerProperty(discount);
	}
	
	public float getPrice() {
		return price.get();
	}

	public void setPrice(float price) {
		this.price = new SimpleFloatProperty(price);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public String getType() {
		return type.get();
	}

	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}
	
	public int getNumber() {
		return number.get();
	}

	public void setNumber(int number) {
		this.number = new SimpleIntegerProperty(number);
	}

	

}

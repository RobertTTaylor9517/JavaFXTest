package org.openjfx.hellofx.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Memo {
	
	private final IntegerProperty id;
	private final StringProperty name;
	private final StringProperty message;
	
	//constructor
	public Memo(int id, String name, String message) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.message = new SimpleStringProperty(message);
	}
	
	//Getters
	
	public int getId() {
		return id.get();
	}
	
	public String getName() {
		return name.get();
	}
	
	public String getMessage() {
		return message.get();
	}
	
	//Setters
	
	public void setId(int val) {
		id.set(val);
	}
	
	public void setName(String val) {
		name.set(val);
	}
	
	public void setMessage(String val) {
		message.set(val);
	}
	
	//Prop values
	
	public IntegerProperty idProperty() {
		return id;
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty messageProperty() {
		return message;
	}
}

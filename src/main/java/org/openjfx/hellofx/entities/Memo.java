package org.openjfx.hellofx.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Memo {
	
	private final StringProperty name;
	private final StringProperty message;
	
	//constructor
	public Memo(String name, String message) {
		this.name = new SimpleStringProperty(name);
		this.message = new SimpleStringProperty(message);
	}
	
	//Getters
	public String getName() {
		return name.get();
	}
	
	public String getMessage() {
		return message.get();
	}
	
	//Setters
	public void setName(String val) {
		name.set(val);
	}
	
	public void setMessage(String val) {
		message.set(val);
	}
	
	//Prop values
	public StringProperty nameProperty() {
		return name;
	}
	
	public StringProperty messageProperty() {
		return message;
	}
}

package org.openjfx.hellofx;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloWorld extends Application {
	
	private static Scene scene;
	
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) throws Exception {
		scene = new Scene(loadFXML("Main"), 640, 480);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}
	
	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloWorld.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

}

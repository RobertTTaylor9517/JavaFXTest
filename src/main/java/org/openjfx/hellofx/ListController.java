package org.openjfx.hellofx;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import org.openjfx.hellofx.entities.Memo;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ListController implements Initializable {

	@FXML
	private ListView<String> memoList = new ListView<String>();
	@FXML
	private AnchorPane labelAnchor = new AnchorPane();
	@FXML
	private TextField nameField;
	@FXML
	private TextArea messageField;

	private ObservableList<Memo> data = FXCollections.observableArrayList();
	private DbConnection dc;
	private Stage stage = new Stage();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dc = new DbConnection();
		getMemos();

	}

	@FXML
	public void getMemos() {
		
		labelAnchor.getChildren().clear();
		memoList.getItems().clear();
		data.clear();

		Connection conn = dc.Connect();

		// Execute query and store info

		try {
			ResultSet res = conn.createStatement().executeQuery("SELECT * FROM memos");
			while (res.next()) {
				data.add(new Memo(res.getInt(1), res.getString(2), res.getString(3)));
			}
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		for (int i = 0; i < data.size(); i++) {
			if (!memoList.getItems().contains(data.get(i).getName())) {
				memoList.getItems().add(data.get(i).getName());
			}
		}

		Label label = new Label();

		memoList.getSelectionModel().selectedItemProperty()
				.addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
					if (new_val == null) {
						memoList.getSelectionModel().clearSelection();
					} else {
						int index = memoList.getSelectionModel().getSelectedIndex();
						label.setText(data.get(index).getMessage());
					}
				});

		labelAnchor.getChildren().add(label);
	}

	@FXML
	private void openAddMemo() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("AddMemo.fxml"));
			

			Scene scene = new Scene(fxmlLoader.load());
//			Stage stage = new Stage();

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void addMemo(ActionEvent ev) {
		String name = "'" + nameField.getText() + "'";
		String message = "'" + messageField.getText() + "'";
		int id = ThreadLocalRandom.current().nextInt();

		final Node source = (Node) ev.getSource();
		final Stage addStage = (Stage) source.getScene().getWindow();

		System.out.println(name + message);

		Connection conn = dc.Connect();

		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO memos (id, name, message) VALUES (" + id + ", " + name + ", " + message + ");";

			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			conn.close();

//			this.getMemos();

//			data.add(new Memo(id, name, message));
			
			addStage.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void closeAddMemo(ActionEvent ev) {
		final Node source = (Node) ev.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();

		stage.close();
	}

	@FXML
	private void deleteMemo(ActionEvent ev) {
		int index;
		int id;

		index = memoList.getSelectionModel().getSelectedIndex();
		id = data.get(index).getId();

		Connection conn = dc.Connect();

		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			String sql = "DELETE from memos where id = " + id + ";";

			stmt.executeUpdate(sql);
			stmt.close();
			conn.commit();
			conn.close();

			getMemos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

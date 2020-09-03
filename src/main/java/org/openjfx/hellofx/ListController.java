package org.openjfx.hellofx;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.hellofx.entities.Memo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class ListController implements Initializable {
	
	@FXML private ListView<String> memoList;
	
	private ObservableList<Memo> data;
	private DbConnection dc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dc = new DbConnection();
		
	}
	
	@FXML
	private void getMemos() {
		Connection conn = dc.Connect();
		data = FXCollections.observableArrayList();
		
		// Execute query and store info
		
		try {
			ResultSet res = conn.createStatement().executeQuery("SELECT * FROM memos");
			while(res.next()) {
				data.add(new Memo(res.getString(2), res.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int i = 0; i < data.size(); i++) {
//			memoList.getItems().add(data.get(i).getName());
//		}
		
		for(int i = 0; i < data.size(); i++) {
			if(! memoList.getItems().contains(data.get(i).getName())) {
				memoList.getItems().add(data.get(i).getName());
			}
		}
	}

}

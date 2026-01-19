package application;
	
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
//			 Parent root = FXMLLoader.load(getClass().getResource("dd.fxml"));
//			 Parent root = FXMLLoader.load(getClass().getResource("trangchuDemo.fxml"));
//			 Parent root = FXMLLoader.load(getClass().getResource("GUImusic.fxml"));
//			 Parent root = FXMLLoader.load(getClass().getResource("searchGUI.fxml"));
//			 Parent root = FXMLLoader.load(getClass().getResource("libraryGUI.fxml"));
//			 Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
//			 Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
			 Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
			   Scene scene = new Scene(root);
			   stage.setScene(scene);
			   stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

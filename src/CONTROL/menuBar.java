package CONTROL;

import javafx.scene.Node;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class menuBar {
	@FXML
	private Button searchBtn;
	@FXML
	private AnchorPane menuBar;
	@FXML
	private AnchorPane Content;
	public void homeTab(ActionEvent event)
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Homepage.fxml"));	
		Parent root = loader.load();
	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		catch(Exception e)
		{
			
		}
	}
	public void searchTab(ActionEvent event)
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/searchGUI.fxml"));	
		Parent root = loader.load();
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		catch(Exception e)
		{
			
		}
	}
	public void libraryTab(ActionEvent event)
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/libraryGUI.fxml"));	
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		catch(Exception e)
		{
			
		}
	}
	public void ProfileTab(ActionEvent event)
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Profile.fxml"));	
		Parent root = loader.load();
		
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		}
		catch(Exception e)
		{
			
		}
	}
}

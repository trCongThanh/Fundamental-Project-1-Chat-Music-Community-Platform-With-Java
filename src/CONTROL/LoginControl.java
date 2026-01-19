package CONTROL;

import java.io.IOException;

import DAO.UserDao;
import MODEL.nguoiDung;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginControl {
	@FXML
	private TextField UserNameTextField;
	@FXML
	private PasswordField PasswordTextField;
	private Parent root;
	private Stage stage;
	private Scene scene;
	
	public void Login(ActionEvent event)  {
		try {
			String userName = UserNameTextField.getText().trim();
			String passWord = PasswordTextField.getText().trim();
			nguoiDung nguoiDung = UserDao.login(userName, passWord);
			if(nguoiDung != null) {
				UserDao.refreshDataUser();
				UserDao.AddDataUser(nguoiDung.getUsername(),nguoiDung.getAvatar());
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Login successful");
				alert.setHeaderText(null);
				alert.setContentText("Login successful");
				alert.showAndWait();
				switchIntoHomepage(event);
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Username or Password is wrong");
				alert.setHeaderText(null);
				alert.setContentText("Username or Password is wrong");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void switchIntoHomepage(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/Homepage.fxml"));
			scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void switchIntoSignUp(MouseEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/Sign_Up.fxml"));
			scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}

package CONTROL;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpControl implements Initializable {
	@FXML
	private TextField UserNameTextField;
	@FXML
	private PasswordField PasswordTextField;
	@FXML
	private PasswordField ConfirmTextField;
	@FXML
	private ComboBox<String> DayComboBox;
	@FXML
	private ComboBox<String> MonthComboBox;
	@FXML
	private TextField YearTextField;
	@FXML
	private ComboBox<String> GenderComboBox;
	private Parent root;
	private Scene scene;
	private Stage stage;
	private String Days [] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
								"19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String Months [] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private String Genders [] = {"Male","Female"};
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		DayComboBox.getItems().addAll(Days);
		MonthComboBox.getItems().addAll(Months);
		GenderComboBox.getItems().addAll(Genders);
	}
	
	public void SignUp(ActionEvent event) {
		String UserName = UserNameTextField.getText().trim();
		String passWord = PasswordTextField.getText().trim();
		String ConFirmPassWord = ConfirmTextField.getText();
		String Day = getDayComboBox(event);
		String month = getMonthComboBox(event);
		String year = YearTextField.getText();
		String gender = getGenderComboBox(event);
		LocalDate birthDay = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(Day));
		System.out.println(birthDay);
		int check = UserDao.CheckUserName(UserName);
		// kiểm tra password và confirmPassWord
		if(passWord.equals(ConFirmPassWord)) {
			if(check == 0) {
				UserDao.SignUp(UserName, passWord,birthDay,gender);
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Sign Up successful");
				alert.setHeaderText(null);
				alert.setContentText("Sign Up successful");
				alert.showAndWait();
				switchIntoLogin(event);
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("UserName is already exits");
				alert.setHeaderText(null);
				alert.setContentText("UserName is already exits");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Confirm PassWord is wrong");
			alert.setHeaderText(null);
			alert.setContentText("Confirm PassWord is wrong");
			alert.showAndWait();
		}
	}
	
	public void switchIntoLogin(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			scene = new Scene(root);
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public String getDayComboBox(ActionEvent event) {
		String Day = DayComboBox.getValue();
		return Day;
	}
	public String getMonthComboBox(ActionEvent event) {
		String Month = MonthComboBox.getValue();
		return Month;
	}
	public String getGenderComboBox(ActionEvent event) {
		String Gender = GenderComboBox.getValue();
		return Gender;
	}

}

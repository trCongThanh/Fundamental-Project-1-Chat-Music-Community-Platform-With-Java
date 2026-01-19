package CONTROL;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.UserDao;
import MODEL.nguoiDung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class profileControl extends menuBar implements Initializable  {
	@FXML
	private Label UsernameLabel;
    @FXML
    private TextField UserNameTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private ComboBox<String> DayComboBox;
    @FXML
    private ComboBox<String> MonthComboBox;
    @FXML
    private TextField YearTextField;
    @FXML
    private ComboBox<String> GenderComboBox;
    @FXML
    private Button SaveButton;
    @FXML
    private Button ChangeAvatarButton;
    @FXML
    private Button LogoutButton;
    @FXML
    private ImageView AvatarImageView;
    private Parent root;
    private Scene scene;
    private Stage stage;
    private String username;
    private String Days [] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18",
			"19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private String Months [] = {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private String Genders [] = {"Male","Female"};

   
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	double radius = Math.min(AvatarImageView.getFitWidth(), AvatarImageView.getFitHeight()) / 2;
	      Circle clip = new Circle(radius, radius, radius);
	      AvatarImageView.setFitWidth(200);
	       AvatarImageView.setFitHeight(200);
	        AvatarImageView.setPreserveRatio(true);
	      // Đặt Circle này làm clip cho ImageView
	      AvatarImageView.setClip(clip);
    	DayComboBox.getItems().addAll(Days);
        MonthComboBox.getItems().addAll(Months);
        GenderComboBox.getItems().addAll(Genders);
    	
        String Username = UserDao.getUserNameTemp();
		nguoiDung user = UserDao.GetDataUser(Username);
		
		String path = UserDao.getURLUserTemp();
		if(path != null) {
			Image image = new Image(path);
			AvatarImageView.setImage(image);
		}
		
		
    	String[] birthDayParts = user.getBirthDay().toString().split("-");
        String year = birthDayParts[0];
        String month = birthDayParts[1];
        String day = birthDayParts[2];
        UsernameLabel.setText(user.getUsername());
        UserNameTextField.setText(user.getUsername());
        PasswordTextField.setText(user.getPassword());
        
        
        DayComboBox.setValue(day);
        MonthComboBox.setValue(month);
        YearTextField.setText(year);

        GenderComboBox.setValue(user.getGender());
		
	}
    
    public void ChangeInformation(ActionEvent event) {
    	String username = UserNameTextField.getText();
    	String password = PasswordTextField.getText();
    	String day = DayComboBox.getValue();
    	String month = MonthComboBox.getValue();
    	String year = YearTextField.getText();
    	String Gender = GenderComboBox.getValue();
    	LocalDate localDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
              UserDao.UpdateInformation(username, password, localDate, Gender,UserDao.getUserNameTemp());
              String path = UserDao.getURLUserTemp();
              UserDao.refreshDataUser();
              UserDao.AddDataUser(username,path);
              Alert alert_succes = new Alert(Alert.AlertType.CONFIRMATION);
              alert_succes.setTitle("Saving");
              alert_succes.setHeaderText(null);
              alert_succes.setContentText("Save Successful");
              alert_succes.showAndWait();
        }
    }
    
    public void ChangeAvatar(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	File fileSelected = fileChooser.showOpenDialog(stage);
    	if(fileSelected != null) {
    		String path = fileSelected.toURI().toString();
    		System.out.println(path);
    		UserDao.UpdateAvatar(path, UserDao.getUserNameTemp());
    		Image image = new Image(path);
    		AvatarImageView.setImage(image);
    		String Username = UserDao.getUserNameTemp();
    		UserDao.refreshDataUser();
    		UserDao.AddDataUser(Username, path);
    	}
    }
    
    public void LogOut(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
              Alert alert_succes = new Alert(Alert.AlertType.CONFIRMATION);
              alert_succes.setTitle("Saving");
              alert_succes.setHeaderText(null);
              alert_succes.setContentText("Save Successful");
              alert_succes.showAndWait();
              SwitchIntoLogin(event);
        }
    }
    
    public void SwitchIntoLogin(ActionEvent event) {
    	try {
    		try {
    			root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
    			scene = new Scene(root);
    			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    			stage.setScene(scene);
    			stage.show();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

  
	
   
    
}

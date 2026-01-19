package CONTROL;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.UserDao;
import DAO.actionThuvien;
import MODEL.listBaihat;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class addmusicControl implements Initializable {
@FXML
private Label checkLabel;
@FXML
private AnchorPane Parent;
@FXML
private StackPane rootpane;

private String idUser ="1";
private String idMusic="";
	public String getIdMusic() {
	return idMusic;
}
public void setIdMusic(String idMusic) {
	this.idMusic = idMusic;
	actionThuvien actionThuvien = new actionThuvien();
	if(!actionThuvien.checkPlaylist(idUser)) checkLabel.setVisible(true);
	else 
		{
		checkLabel.setVisible(false);
		ArrayList<listBaihat> arr = actionThuvien.layDanhsach(idUser);
		generateList(arr);
		
		}
}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		UserDao ud = new UserDao();
		idUser=ud.getIduser();
	}
	public void generateList(ArrayList<listBaihat> arrlistBaihat)
	{
		int ch=0;
		actionThuvien actv = new actionThuvien();
		for (listBaihat list : arrlistBaihat) {
			if(actv.isPlaylisthavethisSong(list.getIdList(), idMusic)==true) {}
			else {
				ch++;
			int i= Parent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(list.getTen());
			ten.setWrapText(true);
			ten.setPrefWidth(400);ten.setPrefHeight(30);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(105);ten.setLayoutY(15);
			
			
			Image image=null;
			try {
				if(list.getImgUrl()!=null) {
					File file = new File(list.getImgUrl());
	            image = new Image(file.toURI().toString());}
				else {
					  File file = new File("img/song.png");
				      image = new Image(file.toURI().toString());
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	           
			ImageView img = new ImageView();
			 img.setImage(image);
			 img.setFitHeight(100);
			 img.setFitWidth(100);
			 img.setStyle("-fx-background-radius: 5;");
			 
			RadioButton radiobtn = new RadioButton();
			radiobtn.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			radiobtn.setLayoutX(430);
			radiobtn.setLayoutY(50);
			radiobtn.getStyleClass().add("add "+list.getIdList());
			
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: GRAY;-fx-margin-bottom: 10px;-fx-background-radius:10;");
			ap.getChildren().add(ten);
			ap.getChildren().add(img);
			ap.getChildren().add(radiobtn);
			ap.setPrefWidth(500);
			ap.setPrefHeight(100);
			ap.setLayoutX(25);
			ap.setLayoutY(15+(i-1)*110);
			ap.setOnMouseClicked(event->{
			
			});
			Parent.getChildren().add(ap);
			}
		}
		if(ch==0) {checkLabel.setVisible(true);checkLabel.setText("you have already added this song to all of your list 😎");}
	}
	public void left()
	{
		TranslateTransition translate = new TranslateTransition();
		  translate.setNode(rootpane);
		  translate.setDuration(Duration.millis(1000));
		  translate.setByX(-900);
		  translate.setOnFinished(e->{
//			  try {
//				  Parent secondView;
//				  secondView = (StackPane) FXMLLoader.load(getClass().getResource("GUImusic.fxml"));
//				  Scene newScene = new Scene (secondView);
//				  Stage curStage = (Stage) rootpane.getScene().getWindow();
//				  curStage.setScene (newScene);
//				  }
//			  catch (Exception e1) {
//				  e1.printStackTrace();
//				// TODO: handle exception
//			}
		  });
		  translate.play();
	}
	public void addtoPlaylist()
	{
		Object[] children = Parent.lookupAll("*").toArray();
		for (Object object : children) {
			RadioButton temp=null;
			try {
			temp = (RadioButton) object;
			System.out.println(temp.getStyleClass());
			if(temp.getStyleClass().toString().contains("add")&&temp.isSelected()) {
				String s = temp.getStyleClass().toString();
				String iddanhsach = s.substring(s.indexOf("add")+4, s.length());
				actionThuvien actionThuvien = new actionThuvien();
				actionThuvien.addtoList(iddanhsach, idMusic);
				left();
				}
			}
			catch (Exception e) {
				
				// TODO: handle exception
			}
			
		}
	}
}

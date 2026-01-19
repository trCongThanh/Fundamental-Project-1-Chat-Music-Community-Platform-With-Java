package CONTROL;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import DAO.actionThuvien;
import DAO.actionTimkiem;
import MODEL.baiHat;
import MODEL.caSi;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class miniSearchControl {
	@FXML
	private AnchorPane rootpane;
	@FXML
	private Button Searchbtn;
	@FXML
	private AnchorPane Parent;
	@FXML
	private TextField searchField;
	private String idList;
	
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	public void left()
	{
		TranslateTransition translate = new TranslateTransition();
		  translate.setNode(rootpane);
		  translate.setDuration(Duration.millis(1000));
		  translate.setByY(600);
		  translate.setOnFinished(e->{
			  try {
				  Parent secondView;
				  secondView = (StackPane) FXMLLoader.load(getClass().getResource("/application/libraryGUI.fxml"));
				  Scene newScene = new Scene (secondView);
				  Stage curStage = (Stage) rootpane.getScene().getWindow();
				  curStage.setScene (newScene);
				  }
			  catch (Exception e1) {
//				  e1.printStackTrace();
				// TODO: handle exception
			}
		  });
		  translate.play();
	}
	public void searchbaihat()
	{
		Parent.getChildren().clear();
		if(searchField.getText().isBlank()) {System.out.println("not ok");return;}
		actionTimkiem actk = new actionTimkiem();
		ArrayList<baiHat> arrBaihat = actk.minitimkiem(searchField.getText(),idList);
		for (baiHat baiHat : arrBaihat) {
			int i= Parent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(baiHat.getTenBaihat());
			ten.setWrapText(true);
			ten.setPrefWidth(150);ten.setPrefHeight(80);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(105);ten.setLayoutY(5);
			
			ArrayList<caSi> arrCasi = actk.timCasitheoIDbaihat(baiHat.getIdBaihat());
			StringBuilder casiNameBuilder = new StringBuilder();
			StringBuilder casiTieusu = new StringBuilder();
			ArrayList<String> arrTencasi = new ArrayList<String>();
			
			 casiTieusu.append(arrCasi.get(0).getTieuSu());
			ArrayList<String> imgAvatar = new ArrayList<String>();
			for (caSi s : arrCasi) {
				arrTencasi.add(s.getTenCasi());
			    casiNameBuilder.append(s.getTenCasi());
			    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
			    imgAvatar.add(s.getImgA());
			}
			String casiName = casiNameBuilder.toString();
			Label casi = new Label(casiName);
			casi.setPrefWidth(300);casi.setPrefHeight(30);
			casi.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
			casi.setTextFill(Color.WHITE);
			casi.setLayoutX(105);casi.setLayoutY(70);
			
			Image avatar =null;
			
			Image image=null;
			try {
			 URL url = new URL(baiHat.getImgUrl());
	            InputStream inputStream = url.openStream();
	            image = new Image(inputStream);
	            
	            URL url1 = new URL(imgAvatar.get(0));
	            InputStream inputStream1 = url1.openStream();
	            avatar = new Image(inputStream1);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ImageView imgA = new ImageView();
			imgA.setImage(avatar);
			
			ImageView img = new ImageView();
			 img.setImage(image);
			 img.setFitHeight(100);
			 img.setFitWidth(100);
			 img.setStyle("-fx-background-radius: 5;");
			
			 RadioButton radiobtn = new RadioButton();
			 radiobtn.setPrefWidth(50);radiobtn.setPrefHeight(50);
			 radiobtn.setFont(Font.font("Arial", FontWeight.BOLD, 25));
			 radiobtn.setLayoutX(305);radiobtn.setLayoutY(25);
			 radiobtn.getStyleClass().add("add "+baiHat.getIdBaihat());
			 
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: GRAY;-fx-margin-bottom: 10px;-fx-background-radius:10;");
			ap.getChildren().add(ten);
			ap.getChildren().add(casi);
			ap.getChildren().add(img);
			ap.getChildren().add(radiobtn);
			ap.setPrefWidth(100);
			ap.setPrefHeight(100);
			ap.setLayoutX(5);
			ap.setLayoutY(30+i*120);
			
			Parent.getChildren().add(ap);
			}
	}
	public void addtoList()
	{
		Object[] children = Parent.lookupAll("*").toArray();
		for (Object object : children) {
			RadioButton temp=null;
			try {
			temp = (RadioButton) object;
			System.out.println(temp.getStyleClass());
			if(temp.getStyleClass().toString().contains("add")&&temp.isSelected()) {
				String s = temp.getStyleClass().toString();
				String idbaihat = s.substring(s.indexOf("add")+4, s.length());
				actionThuvien actionThuvien = new actionThuvien();
				actionThuvien.addtoList(idList, idbaihat);
				left();
				}
			}
			catch (Exception e) {

				// TODO: handle exception
			}}
	}
}

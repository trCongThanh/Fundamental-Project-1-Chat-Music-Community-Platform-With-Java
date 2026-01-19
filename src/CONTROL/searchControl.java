package CONTROL;

import java.io.InputStream;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.actionTimkiem;
import MODEL.baiHat;
import MODEL.caSi;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class searchControl extends menuBar implements Initializable {
@FXML
private ScrollPane scrollPane;
@FXML
private AnchorPane Parent;
@FXML
private Button searchBtn;
@FXML
private TextField searchField;
@FXML
private AnchorPane anchor;

private String option="";
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		 Object[] childrenArray = Parent.getChildren().toArray();
		 for (Object object : childrenArray) {
			 AnchorPane temp =(AnchorPane) object;
			 temp.setOnMouseClicked(event->{
				 ScaleTransition scale = new ScaleTransition();
				  scale.setNode((Node) object);
				  scale.setDuration(Duration.seconds(1));
				  scale.setCycleCount(1);
				  scale.setInterpolator(Interpolator.LINEAR);
				  scale.setByX(-1);
				  scale.setByY(-1);
				  scale.setOnFinished(e -> {
					  Parent.getChildren().clear();
					  searchTheloai(temp.getId()); 
				  });
				  scale.play();
			 });
			
			 }
	}
	public void animtionScale()
	{
		 Object[] childrenArray = Parent.getChildren().toArray();
		 for (Object object : childrenArray) {
			 ScaleTransition scale = new ScaleTransition();
			  scale.setNode((Node) object);
			  scale.setDuration(Duration.seconds(1));
			  scale.setCycleCount(1);
			  scale.setInterpolator(Interpolator.LINEAR);
			  scale.setByX(-1);
			  scale.setByY(-1);
			  scale.setOnFinished(e -> {
				  Parent.getChildren().clear();
				  if(object == childrenArray[childrenArray.length-1]) searchbaihat();
			  });
			  scale.play();
		}
		
	}
	public void searchTheloai(String theloai)
	{
		actionTimkiem actk = new actionTimkiem();
		ArrayList<baiHat> arrBaihat = actk.timkiem(theloai,"theloai");
		generateSong(arrBaihat);
	}
	public void searchbaihat()
	{
		if(searchField.getText().isBlank()) {System.out.println("not ok");return;}
		actionTimkiem actk = new actionTimkiem();
		ArrayList<baiHat> arrBaihat = actk.timkiem(searchField.getText(),"tim");
		ArrayList<caSi> arrCasi = actk.timkiemCasi(searchField.getText());
		generateCasi(arrCasi);
		if(Parent.getChildren().size()>0) arrBaihat= actk.timkiembaihatTheocasi(Parent.getChildren().get(0).getId());
		generateSong(arrBaihat);	
	}
	
	public void generateSong(ArrayList<baiHat> arrBaihat)
	{
		for (baiHat baiHat : arrBaihat) {
			int i= Parent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(baiHat.getTenBaihat());
			ten.setWrapText(true);
			ten.setPrefWidth(400);ten.setPrefHeight(30);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(105);ten.setLayoutY(15);
			
			actionTimkiem actk = new actionTimkiem();
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
			casi.setPrefWidth(400);casi.setPrefHeight(30);
			casi.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
			casi.setTextFill(Color.WHITE);
			casi.setLayoutX(105);casi.setLayoutY(50);
			
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
				
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: GRAY;-fx-margin-bottom: 10px;-fx-background-radius:10;");
			ap.getChildren().add(ten);
			ap.getChildren().add(casi);
			ap.getChildren().add(img);
			ap.setPrefWidth(500);
			ap.setPrefHeight(100);
			ap.setLayoutX(25);
			ap.setLayoutY(30+i*120);
			ap.setOnMouseClicked(event->{
				try {
					
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GUImusic.fxml"));	
				Parent root = loader.load();
				songControl songControl = loader.getController();
				songControl.setIdMusic(baiHat.getIdBaihat());
				songControl.setUrl1(baiHat.getSongUrl());
				songControl.setName(baiHat.getTenBaihat());
				songControl.setArtistName(arrTencasi);
				songControl.setIdMusic(baiHat.getIdBaihat());
				songControl.setLore(casiTieusu.toString());
				songControl.setAvatar(imgA.getImage());
				songControl.setcurrSong(baiHat);
				
		        Scene scene = searchBtn.getScene();
		        root.translateYProperty().set(scene.getHeight());

		        StackPane parentContainer = (StackPane) searchBtn.getScene().getRoot();

		        parentContainer.getChildren().add(root);

		        Timeline timeline = new Timeline();
		        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		        timeline.getKeyFrames().add(kf);
		        timeline.setOnFinished(t -> {
		        });
		        timeline.play();}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			});
			Parent.getChildren().add(ap);
			}
	}
	public void generateCasi(ArrayList<caSi> arrCasi)
	{
		for (caSi casi : arrCasi) {
			int i= Parent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(casi.getTenCasi());
			ten.setWrapText(true);
			ten.setPrefWidth(400);ten.setPrefHeight(30);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(115);ten.setLayoutY(30);		
			Label tieusu = new Label(casi.getTieuSu());
			Image image=null;
			Image imageBG=null;
			try {
			 URL url = new URL(casi.getImgA());
	            InputStream inputStream = url.openStream();
	            image = new Image(inputStream);
	            
	            URL url1 = new URL(casi.getImgBG());
	            InputStream inputStream1 = url1.openStream();
	            imageBG = new Image(inputStream1);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			ImageView BG = new ImageView();
			BG.setImage(imageBG);
			
			ImageView img = new ImageView();
			 img.setImage(image);
			 img.setFitHeight(100);
			 img.setFitWidth(100);
			 double radius = Math.min(img.getFitWidth(), img.getFitHeight()) / 2;
		      Circle clip = new Circle(radius, radius, radius);
		      // Đặt Circle này làm clip cho ImageView
		      img.setClip(clip);
		      
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: transparent;-fx-margin-bottom: 10px;-fx-background-radius:10;");
			ap.getChildren().add(ten);
			ap.getChildren().add(img);
			ap.setPrefWidth(500);
			ap.setPrefHeight(100);
			ap.setLayoutX(25);
			ap.setLayoutY(30+i*120);
			ap.setId(ten.getText());
			ap.setOnMouseClicked(event->{
				try {
					
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/casiGUI.fxml"));	
				Parent root = loader.load();
				casiControl casicontrol = loader.getController();
				casicontrol.setstart(casi.getIdCasi(),ten.getText(),tieusu.getText(), img.getImage(), BG.getImage());
		        Scene scene = searchBtn.getScene();
		        root.translateYProperty().set(scene.getHeight());

		        StackPane parentContainer = (StackPane) searchBtn.getScene().getRoot();

		        parentContainer.getChildren().add(root);

		        Timeline timeline = new Timeline();
		        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		        timeline.getKeyFrames().add(kf);
		        timeline.setOnFinished(t -> {
		        });
		        timeline.play();}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			});
			Parent.getChildren().add(ap);
			}
	}
}

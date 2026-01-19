package CONTROL;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import DAO.UserDao;
import DAO.actionCasi;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class casiControl {
@FXML
private AnchorPane imgBG;
@FXML
private ImageView imgA;
@FXML
private Label name;
@FXML
private Label tieusu;
@FXML
private AnchorPane Parent;
@FXML
private AnchorPane rootPane;
@FXML
private Button followbtn;
@FXML
private Button unfollowbtn;
private String idsinger;
private String idUser;
	public void setstart(String id,String nameArtist,String lore, Image img, Image imgB)
	{
		UserDao usd = new UserDao();
		idUser= usd.getIduser();
		idsinger=id;
		name.setText(nameArtist);
		imgA.setImage(img);
		 imgA.setFitHeight(100);
		 imgA.setFitWidth(100);
		 double radius = Math.min(imgA.getFitWidth(), imgA.getFitHeight()) / 2;
	      Circle clip = new Circle(radius, radius, radius);
	      // Đặt Circle này làm clip cho ImageView
	      imgA.setClip(clip);
	      if(imgB!=null) {
	      BackgroundImage bgImage = new BackgroundImage(
	                imgB,
	                BackgroundRepeat.NO_REPEAT, // Repeat options
	                BackgroundRepeat.NO_REPEAT, // Repeat options
	                BackgroundPosition.DEFAULT, // Positioning options
	                BackgroundSize.DEFAULT // Sizing options
	        );
	      imgBG.setBackground(new Background(bgImage));}
	      tieusu.setText(lore);
	      actionTimkiem actk = new actionTimkiem();
	      ArrayList<baiHat> arrBaihat = actk.timkiembaihatTheocasi(nameArtist);
	      actionCasi acs = new actionCasi();
	      if(acs.checkiffollow(idsinger, idUser)==false) {;followbtn.setVisible(true);unfollowbtn.setVisible(false);}
	      else  {followbtn.setVisible(false);unfollowbtn.setVisible(true);}
	      generateSong(arrBaihat);
	      
	}
	public void follow()
	{
		followbtn.setVisible(false);
		unfollowbtn.setVisible(true);
		actionCasi acs = new actionCasi();
		acs.follow(idsinger, idUser);
	}
	public void unfollow()
	{
		followbtn.setVisible(true);
		unfollowbtn.setVisible(false);
		actionCasi acs = new actionCasi();
		acs.unfollow(idsinger, idUser);
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
			
			ArrayList<String> imgAvatar = new ArrayList<String>();
			ArrayList<String> arrTencasi = new ArrayList<String>();
			for (caSi s : arrCasi) {
				arrTencasi.add(s.getTenCasi());
			    casiNameBuilder.append(s.getTenCasi());
			    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
			    casiTieusu.append(s.getTieuSu());
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
				
		        Scene scene = name.getScene();
		        root.translateYProperty().set(scene.getHeight());

		        StackPane parentContainer = (StackPane) name.getScene().getRoot();

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
	if(Parent.getChildren().size()>2)	Parent.setPrefHeight(Parent.getPrefHeight()+Parent.getChildren().size()*60);
	}
	public void left()
	{
		TranslateTransition translate = new TranslateTransition();
		  translate.setNode(rootPane);
		  translate.setDuration(Duration.millis(1000));
		  translate.setByY(600);
		  translate.play();
	}

}

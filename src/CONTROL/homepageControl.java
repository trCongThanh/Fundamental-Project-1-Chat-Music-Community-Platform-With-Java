package CONTROL;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.BaiHatDao;
import DAO.LibraryDao;
import DAO.UserDao;
import DAO.actionCasi;
import DAO.actionFavourite;
import DAO.actionTimkiem;
import MODEL.baiHat;
import MODEL.caSi;
import MODEL.listBaihat;
import MODEL.nguoiDung;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class homepageControl extends menuBar implements Initializable {
	@FXML
	private Button searchBtn;
	@FXML
	private AnchorPane menuBar;
	@FXML
	private AnchorPane ListBaiHatAnchorpane;
	@FXML
	private AnchorPane VpopAnchorpane;
	@FXML
	private AnchorPane KpopAnchorpane;
	@FXML
	private AnchorPane USUKAnchorpane;
	@FXML
	private AnchorPane JpopAnchorpane;
	@FXML
	private AnchorPane favPane;
	@FXML
	private AnchorPane artistPane;
	private ArrayList<listBaihat> listBaiHat;
	
	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		
		ListBaiHatAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
		
		nguoiDung nguoiDung = UserDao.GetDataUser(UserDao.getUserNameTemp());
		ArrayList<listBaihat> list = LibraryDao.ListUser(nguoiDung.getIDuser());
		if(list != null) {
			int valueX = 0;
			for (listBaihat listBaihat : list) {
				AnchorPane anchorPane = new AnchorPane();
				anchorPane.setPrefWidth(145);
				anchorPane.setPrefHeight(66);
				anchorPane.getStyleClass().add("button-style");
				
				Image image=null;
				try {
					if(listBaihat.getImgUrl()!=null) {
						File file = new File(listBaihat.getImgUrl());
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
				ImageView imageView = new ImageView();
				imageView.setFitWidth(50);
				imageView.setFitHeight(50);
				imageView.setImage(image);
				
				
				Label label = new Label(listBaihat.getTen());
				label.setTextFill(Color.WHITE);
				
				label.setPrefWidth(90);
				label.setPrefHeight(45);
				
				anchorPane.setLeftAnchor(imageView, 00.0);
				anchorPane.setTopAnchor(imageView, 0.0);
				anchorPane.setBottomAnchor(imageView, 0.0);
				
				anchorPane.setRightAnchor(label, 0.0);
				anchorPane.setBottomAnchor(label, 0.0);
				anchorPane.setTopAnchor(label, 0.0);
				anchorPane.setLeftAnchor(label, 50.0);

				anchorPane.getChildren().addAll(imageView,label);
				anchorPane.setStyle("-fx-background-color: gray");
				anchorPane.setLayoutX(valueX);
				valueX = valueX + 200;
				anchorPane.setOnMouseClicked(event -> {
					Label tenBaiHatLabel = (Label) anchorPane.getChildren().get(1); // Lấy label_tenbaihat
					String tenBaiHat = tenBaiHatLabel.getText();
					System.out.println("Bạn đã nhấp vào bài hát: " + tenBaiHat);
					// Hoặc thực hiện các hành động khác ở đây
				});
				ListBaiHatAnchorpane.getChildren().addAll(anchorPane);
			}
		}
		VpopAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
		KpopAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
		USUKAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
		JpopAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
//		ArrayList<baiHat> vpopSongs = BaiHatDao.getVpopSongs();
		ArrayList<baiHat> allsong = BaiHatDao.getallmusic();
//		if(vpopSongs != null) {
			int valueX_vpop = 0;
			int valueX_kpop = 0;
			int valueX_usuk = 0;
			int valueX_jpop = 0;
			for (baiHat song: allsong) {
				AnchorPane anchorPane = new AnchorPane();
				anchorPane.setPrefWidth(120);
				anchorPane.setPrefHeight(165);
				anchorPane.getStyleClass().add("button-style");
				
				
				
				ImageView imageView = new ImageView();
				imageView.setFitWidth(110);
				imageView.setFitHeight(100);
//				imageView.setPreserveRatio(true);
				if(song.getImgUrl()!=null) {
					Image image = new Image(song.getImgUrl());
				imageView.setImage(image);}
				
				Label label_tenbaihat = new Label(song.getTenBaihat());
				label_tenbaihat.setTextFill(Color.WHITE);
				label_tenbaihat.setPrefWidth(90);
				label_tenbaihat.setPrefHeight(30);
				
				label_tenbaihat.setAlignment(Pos.CENTER);
				
//				ArrayList<String> Arrtencasi = song.getCasi();
//				StringBuilder tencasi = new StringBuilder();
//				for (String s : Arrtencasi) {
//				if(s.equals(Arrtencasi.get(Arrtencasi.size()-1))) tencasi.append(s);
//				else tencasi.append(s).append(",");
//				}
//				Label label_tencasi = new Label(tencasi.toString());
//				label_tencasi.setTextFill(Color.WHITE);
//				label_tencasi.setPrefWidth(90);
//				label_tencasi.setPrefHeight(30);
//				
//				label_tencasi.setAlignment(Pos.CENTER);
				
				
				anchorPane.setTopAnchor(imageView, 0.0);
				anchorPane.setLeftAnchor(imageView, 40.0);
				anchorPane.setRightAnchor(imageView, 40.0);

				anchorPane.setTopAnchor(label_tenbaihat, 99.0);
		        anchorPane.setLeftAnchor(label_tenbaihat, 00.0);
		        anchorPane.setRightAnchor(label_tenbaihat, 0.0);

//		        anchorPane.setTopAnchor(label_tencasi, 130.0);
//		        anchorPane.setLeftAnchor(label_tencasi, 0.0);
//		        anchorPane.setRightAnchor(label_tencasi, 0.0);
//		        anchorPane.setBottomAnchor(label_tencasi, 0.0);
				
//				anchorPane.getChildren().addAll(imageView,label_tenbaihat,label_tencasi);
				anchorPane.getChildren().addAll(imageView,label_tenbaihat);
		        anchorPane.setStyle("-fx-background-color: black");
			if(song.getTheLoai().contains("VPOP"))	anchorPane.setLayoutX(valueX_vpop);
			else if(song.getTheLoai().contains("KPOP"))	anchorPane.setLayoutX(valueX_kpop);
			else if(song.getTheLoai().contains("US-UK"))	anchorPane.setLayoutX(valueX_usuk);
			else if(song.getTheLoai().contains("JPOP")) anchorPane.setLayoutX(valueX_jpop);

				actionTimkiem actk = new actionTimkiem();
				ArrayList<caSi> arrCasi = actk.timCasitheoIDbaihat(song.getIdBaihat());
				StringBuilder casiNameBuilder = new StringBuilder();
				StringBuilder casiTieusu = new StringBuilder();
				ArrayList<String> arrTencasi = new ArrayList<String>();
				ArrayList<String> imgAvatar = new ArrayList<String>();
				for (caSi s : arrCasi) {
					 imgAvatar.add(s.getImgA());
					arrTencasi.add(s.getTenCasi());
				    casiNameBuilder.append(s.getTenCasi());
				    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
				    casiTieusu.append(s.getTieuSu());
				}
				String casiName = casiNameBuilder.toString();
				Label casi = new Label(casiName);
				casi.setPrefWidth(100);casi.setPrefHeight(30);
				casi.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
				casi.setTextFill(Color.WHITE);
				casi.setLayoutX(35);casi.setLayoutY(150);
				
				Image avatar =null;
				try {
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
				anchorPane.setOnMouseClicked(event -> {
					Label tenBaiHatLabel = (Label) anchorPane.getChildren().get(1); // Lấy label_tenbaihat
//					Label tenCasiLabel = (Label) anchorPane.getChildren().get(2); // Lấy label_tencasi
//				
//					String tenCasi = tenCasiLabel.getText();
					
					String tenBaiHat = tenBaiHatLabel.getText();
					System.out.println("Bạn đã nhấp vào bài hát: " + tenBaiHat + " của ca sĩ: " + casiNameBuilder.toString());
					// Hoặc thực hiện các hành động khác ở 

					try {
						
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GUImusic.fxml"));	
						Parent root = loader.load();
						songControl songControl = loader.getController();
						songControl.setIdMusic(song.getIdBaihat());
						songControl.setUrl1(song.getSongUrl());
						songControl.setName(song.getTenBaihat());
						songControl.setArtistName(arrTencasi);
						songControl.setIdMusic(song.getIdBaihat());
						songControl.setLore(casiTieusu.toString());
						songControl.setAvatar(imgA.getImage());
						songControl.setcurrSong(song);
						
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
							// TODO: handle exception
				        	e.printStackTrace();
						}
				});
			if(song.getTheLoai().contains("VPOP"))	{valueX_vpop = valueX_vpop + 240;;VpopAnchorpane.getChildren().addAll(anchorPane);}
			else if(song.getTheLoai().contains("KPOP")) {valueX_kpop = valueX_kpop + 240;KpopAnchorpane.getChildren().addAll(anchorPane);}
			else if(song.getTheLoai().contains("US-UK")) {valueX_usuk = valueX_usuk + 240;USUKAnchorpane.getChildren().addAll(anchorPane);}
			else if(song.getTheLoai().contains("JPOP")) {valueX_jpop = valueX_jpop + 240;JpopAnchorpane.getChildren().addAll(anchorPane);}
		}
		
		
//		ArrayList<baiHat> kpopSongs = BaiHatDao.getKpopSongs();
//		if(kpopSongs != null) {
//			int valueX_kpop = 0;
//			for (baiHat song: kpopSongs) {
//				AnchorPane anchorPane = new AnchorPane();
//				anchorPane.setPrefWidth(120);
//				anchorPane.setPrefHeight(165);
//				anchorPane.getStyleClass().add("button-style");
//				
//				
//				
//				ImageView imageView = new ImageView();
//				imageView.setFitWidth(110);
//				imageView.setFitHeight(100);
//				imageView.setPreserveRatio(true);
//				if(song.getImgUrl()!=null) {
//					Image image = new Image(song.getImgUrl());
//				imageView.setImage(image);}
//				
//				
//				Label label_tenbaihat = new Label(song.getTenBaihat());
//				label_tenbaihat.setTextFill(Color.WHITE);
//				label_tenbaihat.setPrefWidth(90);
//				label_tenbaihat.setPrefHeight(30);
//				
//				label_tenbaihat.setAlignment(Pos.CENTER);
//				
//				ArrayList<String> Arrtencasi = song.getCasi();
//				StringBuilder tencasi = new StringBuilder();
//				for (String s : Arrtencasi) {
//				if(s.equals(Arrtencasi.get(Arrtencasi.size()-1))) tencasi.append(s);
//				else tencasi.append(s).append(",");
//				}
//				Label label_tencasi = new Label(tencasi.toString());
//				label_tencasi.setTextFill(Color.WHITE);
//				label_tencasi.setPrefWidth(90);
//				label_tencasi.setPrefHeight(30);
//			
//				label_tencasi.setAlignment(Pos.CENTER);
//				
//				
//				anchorPane.setTopAnchor(imageView, 0.0);
//				anchorPane.setLeftAnchor(imageView, 40.0);
//				anchorPane.setRightAnchor(imageView, 40.0);
//
//				anchorPane.setTopAnchor(label_tenbaihat, 99.0);
//		        anchorPane.setLeftAnchor(label_tenbaihat, 00.0);
//		        anchorPane.setRightAnchor(label_tenbaihat, 0.0);
//
//		        anchorPane.setTopAnchor(label_tencasi, 130.0);
//		        anchorPane.setLeftAnchor(label_tencasi, 0.0);
//		        anchorPane.setRightAnchor(label_tencasi, 0.0);
//		        anchorPane.setBottomAnchor(label_tencasi, 0.0);
//				
//				anchorPane.getChildren().addAll(imageView,label_tenbaihat,label_tencasi);
//				anchorPane.setStyle("-fx-background-color: black");
//				anchorPane.setLayoutX(valueX_kpop);
//				valueX_kpop = valueX_kpop + 200;
//				actionTimkiem actk = new actionTimkiem();
//				ArrayList<caSi> arrCasi = actk.timCasitheoIDbaihat(song.getIdBaihat());
//				StringBuilder casiNameBuilder = new StringBuilder();
//				StringBuilder casiTieusu = new StringBuilder();
//				ArrayList<String> arrTencasi = new ArrayList<String>();
//				ArrayList<String> imgAvatar = new ArrayList<String>();
//				for (caSi s : arrCasi) {
//					 imgAvatar.add(s.getImgA());
//					arrTencasi.add(s.getTenCasi());
//				    casiNameBuilder.append(s.getTenCasi());
//				    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
//				    casiTieusu.append(s.getTieuSu());
//				}
//				String casiName = casiNameBuilder.toString();
//				Label casi = new Label(casiName);
//				casi.setPrefWidth(100);casi.setPrefHeight(30);
//				casi.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//				casi.setTextFill(Color.WHITE);
//				casi.setLayoutX(35);casi.setLayoutY(150);
//				
//				Image avatar =null;
//				try {
//		            URL url1 = new URL(imgAvatar.get(0));
//		            InputStream inputStream1 = url1.openStream();
//		            avatar = new Image(inputStream1);
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				ImageView imgA = new ImageView();
//				imgA.setImage(avatar);
//				anchorPane.setOnMouseClicked(event -> {
//					Label tenBaiHatLabel = (Label) anchorPane.getChildren().get(1); // Lấy label_tenbaihat
////					Label tenCasiLabel = (Label) anchorPane.getChildren().get(2); // Lấy label_tencasi
////				
////					String tenCasi = tenCasiLabel.getText();
//					
//					String tenBaiHat = tenBaiHatLabel.getText();
//					System.out.println("Bạn đã nhấp vào bài hát: " + tenBaiHat + " của ca sĩ: " + casiNameBuilder.toString());
//					// Hoặc thực hiện các hành động khác ở 
//
//					try {
//						
//						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GUImusic.fxml"));	
//						Parent root = loader.load();
//						songControl songControl = loader.getController();
//						songControl.setIdMusic(song.getIdBaihat());
//						songControl.setUrl1(song.getSongUrl());
//						songControl.setName(song.getTenBaihat());
//						songControl.setArtistName(arrTencasi);
//						songControl.setIdMusic(song.getIdBaihat());
//						songControl.setLore(casiTieusu.toString());
//						songControl.setAvatar(imgA.getImage());
//						songControl.setcurrSong(song);
//						
//				        Scene scene = searchBtn.getScene();
//				        root.translateYProperty().set(scene.getHeight());
//
//				        StackPane parentContainer = (StackPane) searchBtn.getScene().getRoot();
//
//				        parentContainer.getChildren().add(root);
//
//				        Timeline timeline = new Timeline();
//				        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
//				        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//				        timeline.getKeyFrames().add(kf);
//				        timeline.setOnFinished(t -> {
//				        });
//				        timeline.play();}
//				        catch (Exception e) {
//							// TODO: handle exception
//				        	e.printStackTrace();
//						}
//				});				
//				KpopAnchorpane.getChildren().addAll(anchorPane);
//			}
//		}
//		
//		USUKAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
//		ArrayList<baiHat> USUKSongs = BaiHatDao.getUSUKSongs();
//		if(USUKSongs != null) {
//			int valueX_usuk = 0;
//			for (baiHat song: USUKSongs) {
//				AnchorPane anchorPane = new AnchorPane();
//				anchorPane.setPrefWidth(120);
//				anchorPane.setPrefHeight(165);
//				anchorPane.getStyleClass().add("button-style");
//				
//				
//				
//				ImageView imageView = new ImageView();
//				imageView.setFitWidth(110);
//				imageView.setFitHeight(100);
//				imageView.setPreserveRatio(true);
//				if(song.getImgUrl()!=null) {
//					Image image = new Image(song.getImgUrl());
//				imageView.setImage(image);}
//				
//				
//				Label label_tenbaihat = new Label(song.getTenBaihat());
//				label_tenbaihat.setTextFill(Color.WHITE);
//				label_tenbaihat.setPrefWidth(90);
//				label_tenbaihat.setPrefHeight(30);
//		
//				label_tenbaihat.setAlignment(Pos.CENTER);
//				
//				ArrayList<String> Arrtencasi = song.getCasi();
//				StringBuilder tencasi = new StringBuilder();
//				for (String s : Arrtencasi) {
//				if(s.equals(Arrtencasi.get(Arrtencasi.size()-1))) tencasi.append(s);
//				else tencasi.append(s).append(",");
//				}
//				Label label_tencasi = new Label(tencasi.toString());
//				label_tencasi.setTextFill(Color.WHITE);
//				label_tencasi.setPrefWidth(90);
//				label_tencasi.setPrefHeight(30);
//				
//				label_tencasi.setAlignment(Pos.CENTER);
//				
//				
//				anchorPane.setTopAnchor(imageView, 0.0);
//				anchorPane.setLeftAnchor(imageView, 40.0);
//				anchorPane.setRightAnchor(imageView, 40.0);
//
//				anchorPane.setTopAnchor(label_tenbaihat, 99.0);
//		        anchorPane.setLeftAnchor(label_tenbaihat, 00.0);
//		        anchorPane.setRightAnchor(label_tenbaihat, 0.0);
//
//		        anchorPane.setTopAnchor(label_tencasi, 130.0);
//		        anchorPane.setLeftAnchor(label_tencasi, 0.0);
//		        anchorPane.setRightAnchor(label_tencasi, 0.0);
//		        anchorPane.setBottomAnchor(label_tencasi, 0.0);
//				
//				anchorPane.getChildren().addAll(imageView,label_tenbaihat,label_tencasi);
//				anchorPane.setStyle("-fx-background-color: black");
//				anchorPane.setLayoutX(valueX_usuk);
//				valueX_usuk = valueX_usuk + 200;
//				actionTimkiem actk = new actionTimkiem();
//				ArrayList<caSi> arrCasi = actk.timCasitheoIDbaihat(song.getIdBaihat());
//				StringBuilder casiNameBuilder = new StringBuilder();
//				StringBuilder casiTieusu = new StringBuilder();
//				ArrayList<String> arrTencasi = new ArrayList<String>();
//				ArrayList<String> imgAvatar = new ArrayList<String>();
//				for (caSi s : arrCasi) {
//					 imgAvatar.add(s.getImgA());
//					arrTencasi.add(s.getTenCasi());
//				    casiNameBuilder.append(s.getTenCasi());
//				    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
//				    casiTieusu.append(s.getTieuSu());
//				}
//				String casiName = casiNameBuilder.toString();
//				Label casi = new Label(casiName);
//				casi.setPrefWidth(100);casi.setPrefHeight(30);
//				casi.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//				casi.setTextFill(Color.WHITE);
//				casi.setLayoutX(35);casi.setLayoutY(150);
//				
//				Image avatar =null;
//				try {
//		            URL url1 = new URL(imgAvatar.get(0));
//		            InputStream inputStream1 = url1.openStream();
//		            avatar = new Image(inputStream1);
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				ImageView imgA = new ImageView();
//				imgA.setImage(avatar);
//				anchorPane.setOnMouseClicked(event -> {
//					Label tenBaiHatLabel = (Label) anchorPane.getChildren().get(1); // Lấy label_tenbaihat
////					Label tenCasiLabel = (Label) anchorPane.getChildren().get(2); // Lấy label_tencasi
////				
////					String tenCasi = tenCasiLabel.getText();
//					
//					String tenBaiHat = tenBaiHatLabel.getText();
//					System.out.println("Bạn đã nhấp vào bài hát: " + tenBaiHat + " của ca sĩ: " + casiNameBuilder.toString());
//					// Hoặc thực hiện các hành động khác ở 
//
//					try {
//						
//						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GUImusic.fxml"));	
//						Parent root = loader.load();
//						songControl songControl = loader.getController();
//						songControl.setIdMusic(song.getIdBaihat());
//						songControl.setUrl1(song.getSongUrl());
//						songControl.setName(song.getTenBaihat());
//						songControl.setArtistName(arrTencasi);
//						songControl.setIdMusic(song.getIdBaihat());
//						songControl.setLore(casiTieusu.toString());
//						songControl.setAvatar(imgA.getImage());
//						songControl.setcurrSong(song);
//						
//				        Scene scene = searchBtn.getScene();
//				        root.translateYProperty().set(scene.getHeight());
//
//				        StackPane parentContainer = (StackPane) searchBtn.getScene().getRoot();
//
//				        parentContainer.getChildren().add(root);
//
//				        Timeline timeline = new Timeline();
//				        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
//				        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//				        timeline.getKeyFrames().add(kf);
//				        timeline.setOnFinished(t -> {
//				        });
//				        timeline.play();}
//				        catch (Exception e) {
//							// TODO: handle exception
//				        	e.printStackTrace();
//						}
//				});
//				USUKAnchorpane.getChildren().addAll(anchorPane);
//			}
//		}
//		
//		JpopAnchorpane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
//		ArrayList<baiHat> JpopSongs = BaiHatDao.getUSUKSongs();
//		if(JpopSongs != null) {
//			int valueX_Jpop = 0;
//			for (baiHat song: JpopSongs) {
//				AnchorPane anchorPane = new AnchorPane();
//				anchorPane.setPrefWidth(120);
//				anchorPane.setPrefHeight(165);
//				anchorPane.getStyleClass().add("button-style");
//				
//				
//				
//				ImageView imageView = new ImageView();
//				imageView.setFitWidth(110);
//				imageView.setFitHeight(100);
//				imageView.setPreserveRatio(true);
//				if(song.getImgUrl()!=null) {
//					Image image = new Image(song.getImgUrl());
//				imageView.setImage(image);}
//				
//				
//				Label label_tenbaihat = new Label(song.getTenBaihat());
//				label_tenbaihat.setTextFill(Color.WHITE);
//				label_tenbaihat.setPrefWidth(90);
//				label_tenbaihat.setPrefHeight(30);
//				
//				label_tenbaihat.setAlignment(Pos.CENTER);
//				
//				ArrayList<String> Arrtencasi = song.getCasi();
//				StringBuilder tencasi = new StringBuilder();
//				for (String s : Arrtencasi) {
//				if(s.equals(Arrtencasi.get(Arrtencasi.size()-1))) tencasi.append(s);
//				else tencasi.append(s).append(",");
//				}
//				Label label_tencasi = new Label(tencasi.toString());
//				label_tencasi.setTextFill(Color.WHITE);
//				label_tencasi.setPrefWidth(90);
//				label_tencasi.setPrefHeight(30);
//			
//				label_tencasi.setAlignment(Pos.CENTER);
//				
//				
//				anchorPane.setTopAnchor(imageView, 0.0);
//				anchorPane.setLeftAnchor(imageView, 40.0);
//				anchorPane.setRightAnchor(imageView, 40.0);
//
//				anchorPane.setTopAnchor(label_tenbaihat, 99.0);
//		        anchorPane.setLeftAnchor(label_tenbaihat, 00.0);
//		        anchorPane.setRightAnchor(label_tenbaihat, 0.0);
//
//		        anchorPane.setTopAnchor(label_tencasi, 130.0);
//		        anchorPane.setLeftAnchor(label_tencasi, 0.0);
//		        anchorPane.setRightAnchor(label_tencasi, 0.0);
//		        anchorPane.setBottomAnchor(label_tencasi, 0.0);
//				
//				anchorPane.getChildren().addAll(imageView,label_tenbaihat,label_tencasi);
//				anchorPane.setStyle("-fx-background-color: black");
//				anchorPane.setLayoutX(valueX_Jpop);
//				valueX_Jpop = valueX_Jpop + 200;
//				actionTimkiem actk = new actionTimkiem();
//				ArrayList<caSi> arrCasi = actk.timCasitheoIDbaihat(song.getIdBaihat());
//				StringBuilder casiNameBuilder = new StringBuilder();
//				StringBuilder casiTieusu = new StringBuilder();
//				ArrayList<String> arrTencasi = new ArrayList<String>();
//				ArrayList<String> imgAvatar = new ArrayList<String>();
//				for (caSi s : arrCasi) {
//					 imgAvatar.add(s.getImgA());
//					arrTencasi.add(s.getTenCasi());
//				    casiNameBuilder.append(s.getTenCasi());
//				    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
//				    casiTieusu.append(s.getTieuSu());
//				}
//				String casiName = casiNameBuilder.toString();
//				Label casi = new Label(casiName);
//				casi.setPrefWidth(100);casi.setPrefHeight(30);
//				casi.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
//				casi.setTextFill(Color.WHITE);
//				casi.setLayoutX(35);casi.setLayoutY(150);
//				
//				Image avatar =null;
//				try {
//		            URL url1 = new URL(imgAvatar.get(0));
//		            InputStream inputStream1 = url1.openStream();
//		            avatar = new Image(inputStream1);
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//				ImageView imgA = new ImageView();
//				imgA.setImage(avatar);
//				anchorPane.setOnMouseClicked(event -> {
//					Label tenBaiHatLabel = (Label) anchorPane.getChildren().get(1); // Lấy label_tenbaihat
////					Label tenCasiLabel = (Label) anchorPane.getChildren().get(2); // Lấy label_tencasi
////				
////					String tenCasi = tenCasiLabel.getText();
//					
//					String tenBaiHat = tenBaiHatLabel.getText();
//					System.out.println("Bạn đã nhấp vào bài hát: " + tenBaiHat + " của ca sĩ: " + casiNameBuilder.toString());
//					// Hoặc thực hiện các hành động khác ở 
//
//					try {
//						
//						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GUImusic.fxml"));	
//						Parent root = loader.load();
//						songControl songControl = loader.getController();
//						songControl.setIdMusic(song.getIdBaihat());
//						songControl.setUrl1(song.getSongUrl());
//						songControl.setName(song.getTenBaihat());
//						songControl.setArtistName(arrTencasi);
//						songControl.setIdMusic(song.getIdBaihat());
//						songControl.setLore(casiTieusu.toString());
//						songControl.setAvatar(imgA.getImage());
//						songControl.setcurrSong(song);
//						
//				        Scene scene = searchBtn.getScene();
//				        root.translateYProperty().set(scene.getHeight());
//
//				        StackPane parentContainer = (StackPane) searchBtn.getScene().getRoot();
//
//				        parentContainer.getChildren().add(root);
//
//				        Timeline timeline = new Timeline();
//				        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
//				        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
//				        timeline.getKeyFrames().add(kf);
//				        timeline.setOnFinished(t -> {
//				        });
//				        timeline.play();}
//				        catch (Exception e) {
//							// TODO: handle exception
//				        	e.printStackTrace();
//						}
//				});
//				JpopAnchorpane.getChildren().addAll(anchorPane);
//			}
//		}
		songinFav();
		artistPane();
    }
	public void songinFav()
	{
		System.out.println("Song in fav");
		favPane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
		UserDao ud = new UserDao();
		actionFavourite af = new actionFavourite();
		ArrayList<baiHat> arrBaihat =  af.yeuthich(ud.getIduser());
		for (baiHat baiHat : arrBaihat) {
			int i= favPane.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(baiHat.getTenBaihat());
			ten.setWrapText(true);
			ten.setPrefWidth(150);ten.setPrefHeight(70);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(35);ten.setLayoutY(100);
			
			actionTimkiem actk = new actionTimkiem();
			ArrayList<caSi> arrCasi = actk.timCasitheoIDbaihat(baiHat.getIdBaihat());
			StringBuilder casiNameBuilder = new StringBuilder();
			StringBuilder casiTieusu = new StringBuilder();
			ArrayList<String> arrTencasi = new ArrayList<String>();
			ArrayList<String> imgAvatar = new ArrayList<String>();
			for (caSi s : arrCasi) {
				 imgAvatar.add(s.getImgA());
				arrTencasi.add(s.getTenCasi());
			    casiNameBuilder.append(s.getTenCasi());
			    if(s!=arrCasi.get(arrCasi.size()-1)) casiNameBuilder.append(", ");
			    casiTieusu.append(s.getTieuSu());
			}
			String casiName = casiNameBuilder.toString();
			Label casi = new Label(casiName);
			casi.setPrefWidth(100);casi.setPrefHeight(30);
			casi.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
			casi.setTextFill(Color.WHITE);
			casi.setLayoutX(35);casi.setLayoutY(150);
			
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
			}
			ImageView imgA = new ImageView();
			imgA.setImage(avatar);
	           
			ImageView img = new ImageView();
			 img.setImage(image);
			 img.setFitHeight(110);
			 img.setFitWidth(110);
			 img.setStyle("-fx-background-radius: 5;");
			 img.setLayoutX(40);
			 img.setLayoutY(10);
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: #6246ea;-fx-margin-bottom: 10px;");
			ap.getChildren().add(ten);
			ap.getChildren().add(casi);
			ap.getChildren().add(img);
			ap.setPrefWidth(200);
			ap.setPrefHeight(165);
			ap.setLayoutX(0+i*230);
			ap.setLayoutY(5);
			ap.getStyleClass().add("button-style");
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
			//			e.printStackTrace();
						// TODO: handle exception
					}
				});
			favPane.getChildren().add(ap);
			}
	}
	public void artistPane()
	{
		System.out.println("Artist pane");
		actionCasi acs = new actionCasi();
		artistPane.getStylesheets().add(getClass().getResource("/application/Button.css").toExternalForm());
		ArrayList<caSi> arrCasi =  acs.getrandSinger();
		for (caSi cs : arrCasi) {
			int i= artistPane.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(cs.getTenCasi());
			ten.setWrapText(true);
			ten.setPrefWidth(150);ten.setPrefHeight(70);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 12));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(0);ten.setLayoutY(100);
			ten.setAlignment(Pos.CENTER);  // Đảm bảo Label căn giữa văn bản
			Label tieusu = new Label(cs.getTieuSu());
			Image image=null;
			Image imageBG=null;
			try {
			 URL url = new URL(cs.getImgA());
	            InputStream inputStream = url.openStream();
	            image = new Image(inputStream);
	            
	            URL url1 = new URL(cs.getImgBG());
	            InputStream inputStream1 = url1.openStream();
	            imageBG = new Image(inputStream1);
			}
			catch (Exception e) {
				// TODO: handle exception
			//	e.printStackTrace();
			}
			ImageView BG = new ImageView();
			BG.setImage(imageBG);
	           
			ImageView img = new ImageView();
			 img.setImage(image);
			 img.setFitHeight(110);
			 img.setFitWidth(110);
			 img.setStyle("-fx-background-radius: 5;");
			 img.setLayoutX(20);
			 img.setLayoutY(10);
			 double radius = Math.min(img.getFitWidth(), img.getFitHeight()) / 2;
		      Circle clip = new Circle(radius, radius, radius);
		      img.setClip(clip);
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: transparent;-fx-margin-bottom: 10px;");
			ap.getChildren().add(img);
			ap.getChildren().add(ten);
			ap.setPrefWidth(150);
			ap.setPrefHeight(165);
			ap.setLayoutX(0+i*150);
			ap.setLayoutY(5);
			ap.getStyleClass().add("button-style");
			ap.setOnMouseClicked(event->{
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/casiGUI.fxml"));	
					Parent root = loader.load();
					casiControl casicontrol = loader.getController();
					casicontrol.setstart(cs.getIdCasi(),ten.getText(),tieusu.getText(), img.getImage(), BG.getImage());
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
			        timeline.play();
				}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			});
			artistPane.getChildren().add(ap);
			}
	}
	double isp=0;
	public void nextScrollV()
	{
		if(isp==1) return;
		else {
			System.out.println("next sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), VpopAnchorpane);
	      transition.setToX(VpopAnchorpane.getTranslateX() - (isp+0.5)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  isp = isp+0.5;
	      });
	      transition.play();
		}
	}
	public void backScrollV()
	{
		if(isp==0) return;
		else {
			System.out.println("back sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), VpopAnchorpane);
	      transition.setToX(VpopAnchorpane.getTranslateX() + (isp)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  isp = isp-0.5;
	      });
	      transition.play();
		}
	}
	double ispk=0;
	public void nextScrollK()
	{
		if(ispk==1) return;
		else {
			System.out.println("next sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), KpopAnchorpane);
	      transition.setToX(KpopAnchorpane.getTranslateX() - (ispk+0.5)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispk = ispk+0.5;
	      });
	      transition.play();
		}
	}
	public void backScrollK()
	{
		if(ispk==0) return;
		else {
			System.out.println("back sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), KpopAnchorpane);
	      transition.setToX(KpopAnchorpane.getTranslateX() + (ispk)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispk = ispk-0.5;
	      });
	      transition.play();
		}
	}
	double ispu=0;
	public void nextScrollU()
	{
		if(ispu==1) return;
		else {
			System.out.println("next sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), USUKAnchorpane);
	      transition.setToX(USUKAnchorpane.getTranslateX() - (ispu+0.5)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispu = ispu+0.5;
	      });
	      transition.play();
		}
	}
	public void backScrollU()
	{
		if(ispu==0) return;
		else {
			System.out.println("back sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), USUKAnchorpane);
	      transition.setToX(USUKAnchorpane.getTranslateX() + (ispu)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispu = ispu-0.5;
	      });
	      transition.play();
		}
	}
	double ispj=0;
	public void nextScrollJ()
	{
		if(ispj==1) return;
		else {
			System.out.println("next sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), JpopAnchorpane);
	      transition.setToX(JpopAnchorpane.getTranslateX() - (ispj+0.5)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispj = ispj+0.5;
	      });
	      transition.play();
		}
	}
	public void backScrollJ()
	{
		if(ispj==0) return;
		else {
			System.out.println("back sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), JpopAnchorpane);
	      transition.setToX(JpopAnchorpane.getTranslateX() + (ispj)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispj = ispj-0.5;
	      });
	      transition.play();
		}
	}
	double ispf=0;
	public void nextScrollfav()
	{
		if(ispf==1) return;
		else {
			System.out.println("next sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), favPane);
	      transition.setToX(favPane.getTranslateX() - (ispf+0.5)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispf = ispf+0.5;
	      });
	      transition.play();
		}
	}
	public void backScrollfav()
	{
		if(ispf==0) return;
		else {
			System.out.println("back sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), favPane);
	      transition.setToX(favPane.getTranslateX() + (ispf)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  ispf = ispf-0.5;
	      });
	      transition.play();
		}
	}
}

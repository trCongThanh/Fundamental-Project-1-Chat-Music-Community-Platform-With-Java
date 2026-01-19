package CONTROL;

import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import DAO.UserDao;
import DAO.actionThuvien;
import DAO.actionTimkiem;
import MODEL.baiHat;
import MODEL.caSi;
import MODEL.listBaihat;
import javafx.animation.FadeTransition;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

public class playlistControl implements Initializable{
@FXML
private TextField nameField;
@FXML
private AnchorPane haveList;
@FXML
private AnchorPane createList;
@FXML
private ImageView imgList;
@FXML
private Label nameList;
@FXML
private AnchorPane anchor;
@FXML
private StackPane rootpane;
@FXML
private AnchorPane Parent;
@FXML
private ImageView auto;
@FXML
private ImageView random;
@FXML
private Label leftbtn;
private listBaihat list;
public void setList(listBaihat list)
{
	this.list=list;
}
private String idUser="1";
private String option="";

private String nameoflist="";
private String idofList="";

	public String getIdofList() {
	return idofList;
}
public void setIdofList(String idofList) {
	this.idofList = idofList;
}
	public String getNameoflist() {
	return nameoflist;
}
public void setNameoflist(String nameoflist) {
	this.nameoflist = nameoflist;
}
	public String getOption() {
	return option;
}
public void setOption(String option) {
	this.option = option;
	if(option.equals("create")) {
		nameField.setStyle("-fx-text-fill: white;-fx-background-color: #484b4b");
		haveList.setVisible(false);
		nameList.setVisible(false);
		imgList.setVisible(false);}
		else {
			createList.setVisible(false);
			haveList.setVisible(true);
			nameList.setVisible(true);
			nameList.setText(nameoflist);
			imgList.setVisible(true);
			musicInlist();
		}
}
private Color color;
public void setcolor(Color color)
{
	this.color=color;
	try {
	    //moves mouse to the middle of the screen
	    new Robot().mouseMove((int)  MouseInfo.getPointerInfo().getLocation().x+1, ((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2)+200);
	    //remember to use try-catch block (always, and remember to delete this)
		} catch (Exception e) {
	    e.printStackTrace();
		
	}
}
public boolean checksettheme=false;
public void settheme()
{
	if(checksettheme==false && color!=null) {
        LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(0.3, color.darker()),
		 		new Stop(1, color.darker().darker()));
		anchor.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		checksettheme=true;
	}
}
public void setImg(Image img)
{
	imgList.setImage(img);
}
public static ArrayList<baiHat> playlist = new ArrayList<baiHat>();
public static ArrayList<baiHat> originPlaylist = new ArrayList<baiHat>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		random.setVisible(false);
		UserDao ud = new UserDao();
		idUser=ud.getIduser();
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
	public void imageChooser()
	{
		 actionThuvien actv = new actionThuvien();
		FileChooser fileChooser = new FileChooser();
		 fileChooser.getExtensionFilters().addAll(
                 new ExtensionFilter("JPG", "*.jpg"),
                 new ExtensionFilter("PNG", "*.png"),
                 new ExtensionFilter("JFIF", "*.jfif")
         );
		 Stage stage = (Stage) rootpane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);

        // Check if a file was selected
        if (selectedFile != null) {
            System.out.println("Selected File: " + selectedFile.getAbsolutePath());
            imgList.setImage(new Image(selectedFile.toURI().toString()));
            actv.updateImglist(selectedFile.toString(), idofList);
            // Do something with the selected file
        } else {
        	if(!imgList.getImage().getUrl().equals("file:/D:/Code%20&%20IT/testupload/img/song.png")) return;
        	else {
            File file = new File("img/song.png");
            imgList.setImage(new Image(file.toURI().toString()));
            actv.updateImglist(file.toString(), idofList);
        	}
        }
	}
	public void createPlaylist()
	{
		String timeStamp = new SimpleDateFormat("ddMMyyyy").format(Calendar.getInstance().getTime());
		Date today=null;
		 java.sql.Date sqlDate=null;
		try {
		    today = (Date) new SimpleDateFormat("ddMMyyyy").parse(timeStamp);
		    if (today != null) {
		         sqlDate = new java.sql.Date(today.getTime());
		        // Now, 'sqlDate' contains the SQL date representation
		    }
		} catch (Exception e) {
		    e.printStackTrace(); // Handle parsing exception
		}
		Random r = new Random();
		char c = (char)(r.nextInt(26) + 'a');
		int i = r.nextInt(100);
		StringBuilder s = new StringBuilder();
		s.append(c).append(i);
		actionThuvien actionThuvien = new actionThuvien();
		actionThuvien.createPlaylist(s.toString(),nameField.getText(), null, sqlDate, idUser);
		left();
	}
	
	public void musicInlist()
	{
		
		playlist.clear();
		originPlaylist.clear();
		actionThuvien atv = new actionThuvien();
		actionTimkiem actk = new actionTimkiem();
		ArrayList<baiHat> arrBaihat = atv.Musicinlist(idofList);
		for (baiHat baiHat : arrBaihat) {
			playlist.add(baiHat);
			int i= Parent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(baiHat.getTenBaihat());
			ten.setWrapText(true);
			ten.setPrefWidth(400);ten.setPrefHeight(30);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 30));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(105);ten.setLayoutY(15);
			
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
				songControl.setUrl1(baiHat.getSongUrl());
				songControl.setName(baiHat.getTenBaihat());
				songControl.setArtistName(arrTencasi);
				songControl.setIdMusic(baiHat.getIdBaihat());
				songControl.setLore(casiTieusu.toString());
				songControl.setAvatar(imgA.getImage());
				songControl.setCheckaddList(false,idofList);
				songControl.setcurrSong(baiHat);
				songControl.setList(list);
				
		        Scene scene = nameList.getScene();
		        root.translateYProperty().set(scene.getHeight());

		        StackPane parentContainer = (StackPane) nameList.getScene().getRoot();

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
		originPlaylist=playlist;
	}
	public void changetoRandom()
	{
		random.setVisible(true);
		auto.setVisible(false);
		Collections.shuffle(playlist);
	}
	public void changetoAuto()
	{
		auto.setVisible(true);
		random.setVisible(false);
		playlist=originPlaylist;
	}
	
	public void startPlaylist()
	{
		baiHat baiHat = playlist.get(0);
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
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/GUImusic.fxml"));	
			Parent root = loader.load();
			songControl songControl = loader.getController();
			songControl.setUrl1(baiHat.getSongUrl());
			songControl.setName(baiHat.getTenBaihat());
			songControl.setArtistName(arrTencasi);
			songControl.setIdMusic(baiHat.getIdBaihat());
			songControl.setLore(casiTieusu.toString());
			songControl.setAvatar(imgA.getImage());
			songControl.setCheckaddList(false,idofList);
			songControl.setcurrSong(baiHat);
			songControl.setList(list);
			
	        Scene scene = nameList.getScene();
	        root.translateYProperty().set(scene.getHeight());

	        StackPane parentContainer = (StackPane) nameList.getScene().getRoot();

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
	}
	public void addmusic()
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/miniSearchGUI.fxml"));	
		Parent root = loader.load();
		miniSearchControl minisearch = loader.getController();
		minisearch.setIdList(idofList);
        Scene scene = leftbtn.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) leftbtn.getScene().getRoot();

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
	}
}

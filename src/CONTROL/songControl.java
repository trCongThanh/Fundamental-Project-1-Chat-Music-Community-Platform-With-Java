package CONTROL;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import DAO.actionAmnhac;
import DAO.actionTimkiem;
import MODEL.baiHat;
import MODEL.caSi;
import MODEL.listBaihat;
import MODEL.nguoiDung;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class songControl implements Initializable {
	private File file;
	@FXML
	private AnchorPane CDpane;
	@FXML
	private AnchorPane blur;
	@FXML
	private ImageView pausebtn;
	@FXML
	private ImageView playbtn;
	@FXML
	private ImageView backten;
	@FXML
	private ImageView goten;
	@FXML
	private Label removeList;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Label name;
	@FXML
	private Label artistName;
	@FXML
	private Label lore;
	public Label getLore() {
		return lore;
	}
	public void setLore(String lore) {
		this.lore.setText(lore);
	}
	@FXML
	private ScrollPane performScroll;
	@FXML
	private AnchorPane performPane;
	@FXML 
	private ImageView imgA;
	public void setAvatar(Image img)
	{
		imgA.setImage(img);
		 double radius = Math.min(imgA.getFitWidth(), imgA.getFitHeight()) / 2;
	      Circle clip = new Circle(radius, radius, radius);
	      // Đặt Circle này làm clip cho ImageView
	      imgA.setClip(clip);
	}
	@FXML
	private AnchorPane CDcontain;
	@FXML
	private ImageView CD;
	@FXML
	private Label addList;
	@FXML
	private AnchorPane youlikePane;
	@FXML
	private AnchorPane likeParent;
	@FXML
	private ImageView note;
	private boolean checkaddList=true;
	
	public boolean isCheckaddList() {
		return checkaddList;
	}
	public void setCheckaddList(boolean checkaddList, String idList) {
		this.checkaddList = checkaddList;
		if(checkaddList) addList.setVisible(true);
		else {addList.setVisible(false);youlikePane.setVisible(false);removeList.setVisible(true);this.idList=idList;}
	}
	private Media media;
	private MediaPlayer mediaplayer;
	private Timer timer;
	private TimerTask task;
	private RotateTransition rotate;
	private boolean isRun=false;
	
	private String url1;
	private String idMusic;
	private String idList;
public String getIdMusic() {
		return idMusic;
		
	}
	public void setIdMusic(String idMusic) {
		this.idMusic = idMusic;
		
	}
public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url) {
		this.url1 = url;
		media = new Media(url1);
	    mediaplayer = new MediaPlayer(media);
	    rotate = new RotateTransition();
	    pausebtn.setVisible(false);
	    goiybaihat();
	}
	
public Label getName() {
		return name;
	}
	public void setName(String ten) {
		name.setText(ten);
	}
	public Label getArtistName() {
		return artistName;
	}
	public void setArtistName(ArrayList<String> casi) {
		if(casi.size()>0) {
			for(int i =0;i<casi.size();i++)
				{
				actionTimkiem actk = new actionTimkiem();
				caSi cs = actk.timkiemCasiinSong(casi.get(i));
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
				catch (Exception e) {}
				ImageView BG = new ImageView();
				BG.setImage(imageBG);
				
				ImageView img = new ImageView();
				img.setImage(image);
				
					Label a = new Label(casi.get(i));
					a.setLayoutY(35+i*35);
					a.setLayoutX(15);
					a.setFont(Font.font("Arial", FontWeight.BOLD, 25));
					a.setTextFill(Color.WHITE);
					a.setOnMouseClicked(event->{
						try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/casiGUI.fxml"));	
						Parent root = loader.load();
						casiControl casicontrol = loader.getController();
						casicontrol.setstart(cs.getIdCasi(),cs.getTenCasi(),cs.getTieuSu(), img.getImage(), BG.getImage());
				        Scene scene = CDpane.getScene();
				        root.translateYProperty().set(scene.getHeight());
				        StackPane parentContainer = (StackPane) CDpane.getScene().getRoot();
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
				performPane.getChildren().add(a);
				}
			artistName.setText(casi.get(0));
			}
	
	}
	public void setviBlur()
	{
		blur.setVisible(true);
	}
	public void setdiBlur()
	{
		blur.setVisible(false);
	}
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
	}
	public void playsong()
	{
		note.setVisible(true);
		pausebtn.setVisible(true);
		playbtn.setVisible(false);
		
		beginTimer();
		mediaplayer.play();
	}
	public void stopsong()
	{
		note.setVisible(false);
		pausebtn.setVisible(false);
		playbtn.setVisible(true);
		rotate.stop();
		
		cancelTimer();
		mediaplayer.pause();
	}
	public void beginTimer() {
		
		timer = new Timer();
		
		task = new TimerTask() {
			
			public void run() {
				
				isRun = true;
				double current = mediaplayer.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				progressBar.setProgress(current/end);
				rotateCD(2);
				if(current/end == 1) {
					cancelTimer();
				}
			}
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000);
	}
	
	public void cancelTimer() {
		
		isRun = false;
		timer.cancel();
		note.setVisible(false);
	}
	public void gettime()
	{
		 progressBar.setOnMouseClicked(event -> {
		 double mouseX = event.getX();
         double progressBarWidth = progressBar.getWidth();
         double progress = mouseX / progressBarWidth;
         if(mediaplayer!=null) {
				mediaplayer.seek(Duration.seconds(progress*mediaplayer.getTotalDuration().toSeconds()));
			}
		 });
	}
	public void goten()
	{
		 if(mediaplayer!=null) {
				mediaplayer.seek(mediaplayer.getCurrentTime().add(Duration.seconds(10)));
				double current = mediaplayer.getCurrentTime().toSeconds()+10;
				double end = mediaplayer.getTotalDuration().toSeconds();
				progressBar.setProgress(current/end);
			}
	}
	public void backten()
	{
		 if(mediaplayer!=null) {
			 mediaplayer.seek(mediaplayer.getCurrentTime().add(Duration.seconds(-10)));
			 double current = mediaplayer.getCurrentTime().toSeconds()-10;
				double end = mediaplayer.getTotalDuration().toSeconds();
				progressBar.setProgress(current/end);
			}
	}
	public void rotateCD(int number)
	{
		rotate.setNode(CD);
		rotate.setDuration(Duration.millis(5000));
		rotate.setCycleCount(number);
		rotate.setByAngle(360);
		rotate.play();
	}
	public void addtoPlaylist()
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/addmusicGUI.fxml"));	
		Parent root = loader.load();
		addmusicControl addmusic= loader.getController();
		addmusic.setIdMusic(idMusic);
        Scene scene = addList.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) addList.getScene().getRoot();

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
	
	private listBaihat list;
	public void setList(listBaihat list)
	{
		this.list=list;
	}
	private baiHat currSong;
	public void setcurrSong(baiHat baiHat)
	{
		this.currSong=baiHat;
	}
	private baiHat nextSong;
	public static ArrayList<baiHat> beforeSong = new ArrayList<baiHat>();
	
	public void goiybaihat()
	{
		actionAmnhac acan = new actionAmnhac();
		ArrayList<baiHat> arrBaihat = acan.getsongLike(idMusic);
		nextSong = new baiHat();
		nextSong.setCasi(arrBaihat.get(0).getCasi());
		nextSong.setIdBaihat(arrBaihat.get(0).getIdBaihat());
		nextSong.setSongUrl(arrBaihat.get(0).getSongUrl());
		nextSong.setTenBaihat(arrBaihat.get(0).getTenBaihat());
		
		for (baiHat baiHat : arrBaihat) {
			int i= likeParent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(baiHat.getTenBaihat());
			ten.setWrapText(true);
			ten.setPrefWidth(100);ten.setPrefHeight(70);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(25);ten.setLayoutY(100);
			
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
			casi.setLayoutX(25);casi.setLayoutY(150);
			
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
			 img.setFitHeight(100);
			 img.setFitWidth(100);
			 img.setStyle("-fx-background-radius: 5;");
			 img.setLayoutX(25);
			 img.setLayoutY(10);
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: #6246ea;-fx-margin-bottom: 10px;-fx-background-radius:10;");
			ap.getChildren().add(ten);
			ap.getChildren().add(casi);
			ap.getChildren().add(img);
			ap.setPrefWidth(150);
			ap.setPrefHeight(180);
			ap.setLayoutX(10+i*160);
			ap.setLayoutY(5);
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
				songControl.setdiBlur();
				beforeSong.add(currSong);
				songControl.setcurrSong(baiHat);
				
		        Scene scene = goten.getScene();
		        root.translateYProperty().set(scene.getHeight());

		        StackPane parentContainer = (StackPane) goten.getScene().getRoot();

		        parentContainer.getChildren().add(root);

		        Timeline timeline = new Timeline();
		        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
		        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
		        timeline.getKeyFrames().add(kf);
		        timeline.setOnFinished(t -> {
		        	parentContainer.getChildren().remove(CDpane);
		        	songControl.setviBlur();
		        });
		        timeline.play();}
				catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			});
			likeParent.getChildren().add(ap);
			}
	}
	public void nextSong()
	{
		if(checkaddList==false) {nextSongplaylist(currSong);}
		else {
		baiHat baiHat = nextSong;
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
		
		String casiName = casiNameBuilder.toString();
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
		songControl.setdiBlur();
		beforeSong.add(currSong);
		songControl.setcurrSong(baiHat);
		
        Scene scene = goten.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) goten.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
        	parentContainer.getChildren().remove(CDpane);
        	songControl.setviBlur();
        });
        timeline.play();}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}}
		System.out.println("size of list:"+beforeSong.size());
	}
	public void beforeSong()
	{
		if(checkaddList==false) {beforeSongplaylist(currSong);}
		else {
		baiHat baiHat = beforeSong.get(beforeSong.size()-1);
		beforeSong.remove(beforeSong.size()-1);
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
		}
		ImageView imgA = new ImageView();
		imgA.setImage(avatar);
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
		songControl.setdiBlur();
		songControl.setcurrSong(baiHat);
		
        Scene scene = goten.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) goten.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
        	parentContainer.getChildren().remove(CDpane);
        	songControl.setviBlur();
        });
        timeline.play();}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}}
	}
	public void nextSongplaylist(baiHat song)
	{
		baiHat baiHat=null;
		if(playlistControl.playlist.indexOf(song)+1==playlistControl.playlist.size()) {baiHat =playlistControl.playlist.get(0);}
		else baiHat =playlistControl.playlist.get(playlistControl.playlist.indexOf(song)+1); //nếu play list về cuối danh sách thì phát lại từ đầu
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
		Image avatar =null;Image image=null;
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
		songControl.setIdMusic(baiHat.getIdBaihat());
		songControl.setUrl1(baiHat.getSongUrl());
		songControl.setName(baiHat.getTenBaihat());
		songControl.setArtistName(arrTencasi);
		songControl.setIdMusic(baiHat.getIdBaihat());
		songControl.setLore(casiTieusu.toString());
		songControl.setAvatar(imgA.getImage());
		songControl.setdiBlur();
		songControl.setCheckaddList(false,idList);
		songControl.setcurrSong(baiHat);
		
        Scene scene = goten.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) goten.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
        	parentContainer.getChildren().remove(CDpane);
        	songControl.setviBlur();
        });
        timeline.play();}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void beforeSongplaylist(baiHat song)
	{
		baiHat baiHat=null;
		if(playlistControl.playlist.indexOf(song)-1<0) {baiHat =playlistControl.playlist.get(playlistControl.playlist.size()-1);}
		else baiHat =playlistControl.playlist.get(playlistControl.playlist.indexOf(song)-1);
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
		Image avatar =null;Image image=null;
		try {
		 URL url = new URL(baiHat.getImgUrl());
            InputStream inputStream = url.openStream();
            image = new Image(inputStream);
            
            URL url1 = new URL(imgAvatar.get(0));
            InputStream inputStream1 = url1.openStream();
            avatar = new Image(inputStream1);
		}
		catch (Exception e) {
		}
		ImageView imgA = new ImageView();
		imgA.setImage(avatar);
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
		songControl.setdiBlur();
		songControl.setCheckaddList(false,idList);
		songControl.setcurrSong(baiHat);
		
        Scene scene = goten.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) goten.getScene().getRoot();

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
        	parentContainer.getChildren().remove(CDpane);
        	songControl.setviBlur();
        });
        timeline.play();}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void removeFromlist()
	{
		actionAmnhac acan = new actionAmnhac();
		acan.removesongFromlist(idList, idMusic);
		lefttoPlaylist();
	}
	public void lefttoPlaylist()
	{
		
		TranslateTransition translate = new TranslateTransition();
		  translate.setNode(CDpane);
		  translate.setDuration(Duration.millis(1000));
		  translate.setByY(600);
		  translate.setOnFinished(e->{
			  try {
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
					catch (Exception e2) {
						e2.printStackTrace();
					}
				  
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/libraryGUI.fxml"));	
					Parent root = loader.load();
					Stage stage = (Stage) CDpane.getScene().getWindow();
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.show();
					
					FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/application/playlistGUI.fxml"));	
					Parent root1 = loader1.load();
					playlistControl playlist = loader1.getController();
					playlist.setIdofList(list.getIdList());
					playlist.setNameoflist(list.getTen());
					playlist.setOption("listen");
					playlist.setImg(image);
					playlist.setList(list);
					
			        Scene scene1 = scene;
			        root1.translateYProperty().set(scene1.getHeight());
			        StackPane parentContainer = (StackPane) stage.getScene().getRoot();

			        parentContainer.getChildren().add(root1);

			        Timeline timeline = new Timeline();
			        KeyValue kv = new KeyValue(root1.translateYProperty(), 0, Interpolator.EASE_IN);
			        
			        KeyFrame kf = new KeyFrame(Duration.seconds(0.01), kv);
			        timeline.getKeyFrames().add(kf);
			        timeline.setOnFinished(t -> {
			        });
			        timeline.play();
					}
					catch(Exception e1)
					{
					}
		  });
		  translate.play();
	}
	public void left()
	{
		TranslateTransition translate = new TranslateTransition();
		  translate.setNode(CDpane);
		  translate.setDuration(Duration.millis(1000));
		  translate.setByY(600);
		  translate.play();
	}
	private double isp=0;
	public void nextScroll()
	{
		if(isp==1) return;
		else {
			System.out.println("next sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), likeParent);
	      transition.setToX(likeParent.getTranslateX() - (isp+0.5)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  isp = isp+0.5;
	      });
	      transition.play();
		}
	}
	public void backScroll()
	{
		if(isp==0) return;
		else {
			System.out.println("back sir");
		  TranslateTransition transition = new TranslateTransition(Duration.seconds(1), likeParent);
	      transition.setToX(likeParent.getTranslateX() + (isp)*85); // Slide content down by 50 pixels
	      transition.setOnFinished(e->{
	    	  isp = isp-0.5;
	      });
	      transition.play();
		}
	}
	public void downloadFile() {
		try {
	    String saveFilePath = "D:\\HinhAnh\\nhac.mp3";
	    String fileUrl = url1;
	    DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a folder");
        File selectedDirectory = directoryChooser.showDialog(null);
        
        if (selectedDirectory != null) {
            saveFilePath = selectedDirectory.getPath() + File.separator + name.toString().replace("Label[id=name, styleClass=label]'", "").replaceAll("'", "") + ".mp3";
            System.out.println("Save file path: " + saveFilePath);
            // Add your file creation logic here
        } else {
            System.out.println("No directory selected");
        }
      
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        
        try (InputStream inputStream = connection.getInputStream();
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(saveFilePath))) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }
}

package CONTROL;
import javafx.scene.Node;

import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.UserDao;
import DAO.actionThuvien;
import MODEL.baiHat;
import MODEL.listBaihat;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
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
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class libraryControl extends menuBar implements Initializable {
	@FXML
	private StackPane rootPane;
	@FXML
	private ColorPicker colorPicker;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Label addBtn;
	@FXML
	private Label checkLabel;
	@FXML
	private AnchorPane Parent;
	private String idUser ="1";
	private ArrayList<Color> colorTheme = new ArrayList<Color>();
	
	private boolean check=false;
	public void settheme()
	{
		
		if(check) return;
		String fileName = "theme.txt";
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + File.separator + fileName;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
               colorTheme.add(Color.web(line));
            }
            ColorPicker colorp = new ColorPicker();
            colorp.setValue(colorTheme.get(0));
            Color color = colorp.getValue();
            System.out.println(colorp.getValue());
            LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                    new Stop(0, color),
                    new Stop(0.3, color.darker()),
    		 		new Stop(1, color.darker().darker()));
    		anchor.setBackground(new Background(new BackgroundFill(gradient, null, null)));
        } catch (Exception e) {
           

        }
        check=true;
	}
	public void changeTheme()
	{
		
		Color color =colorPicker.getValue();
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                new Stop(0, color),
                new Stop(0.3, color.darker()),
		 		new Stop(1, color.darker().darker()));
		anchor.setBackground(new Background(new BackgroundFill(gradient, null, null)));
		try {
		  String content = color+"\n"+color.darker()+"\n"+color.darker().darker();
		  
		  String fileName = "theme.txt";
	        String projectPath = System.getProperty("user.dir"); // This gets the current project directory
	        String filePath = projectPath + File.separator + fileName;

		  File txt = new File(filePath);
		  if (!txt.exists()) {
              txt.createNewFile();
          }
		  FileWriter fw = new FileWriter(txt.getAbsoluteFile());
          BufferedWriter bw = new BufferedWriter(fw);

          // Write in file
          bw.write(content);

          // Close connection
          bw.close();}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		colorTheme.clear();
		colorTheme.add(color);
	}
	public void addnewPlaylist()
	{
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/playlistGUI.fxml"));	
		Parent root = loader.load();
		playlistControl playlist = loader.getController();
		playlist.setOption("create");
        Scene scene = addBtn.getScene();
        root.translateYProperty().set(scene.getHeight());

        StackPane parentContainer = (StackPane) addBtn.getScene().getRoot();

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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		UserDao ud = new UserDao();
		idUser=ud.getIduser();
		actionThuvien actionThuvien = new actionThuvien();
		if(!actionThuvien.checkPlaylist(idUser)) checkLabel.setVisible(true);
		else 
			{
			checkLabel.setVisible(false);
			ArrayList<listBaihat> arr = actionThuvien.layDanhsach(idUser);
			generateList(arr);
			try {
			    //moves mouse to the middle of the screen
			    new Robot().mouseMove((int)  MouseInfo.getPointerInfo().getLocation().x+1, (int) MouseInfo.getPointerInfo().getLocation().y+1);
			    //remember to use try-catch block (always, and remember to delete this)
				} catch (Exception e) {
			    e.printStackTrace();
				}
			}
		
		
	}
	public void generateList(ArrayList<listBaihat> arrlistBaihat)
	{
		for (listBaihat list : arrlistBaihat) {
			int i= Parent.getChildren().size(); // lấy số lượng để set Y
			Label ten = new Label(list.getTen());
			ten.setWrapText(true);
			ten.setPrefWidth(300);ten.setPrefHeight(60);
			ten.setFont(Font.font("Arial", FontWeight.BOLD, 40));
			ten.setTextFill(Color.WHITE);
			ten.setLayoutX(105);ten.setLayoutY(30);

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
			 
			AnchorPane ap = new AnchorPane();
			ap.setStyle("-fx-background-color: GRAY;-fx-margin-bottom: 10px;-fx-background-radius:10;");
			
			ap.setPrefWidth(500);
			ap.setPrefHeight(100);
			ap.setLayoutX(25);
			ap.setLayoutY(15+(i-1)*110);
			Label trash = new Label("🗑");
			trash.setPrefWidth(30);ten.setPrefHeight(50);
			trash.setFont(Font.font("Arial", FontWeight.BOLD, 50));
			trash.setTextFill(Color.WHITE);
			trash.setLayoutX(455);trash.setLayoutY(20);
			trash.setOnMouseClicked(e->{
				actionThuvien actv = new actionThuvien();
				actv.deletePlaylist(list.getIdList());
				Parent.getChildren().remove(ap);
				e.consume();
			});
			ap.setOnMouseClicked(event->{
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/playlistGUI.fxml"));	
					Parent root = loader.load();
					
					playlistControl playlist = loader.getController();
					playlist.setIdofList(list.getIdList());
					playlist.setNameoflist(ten.getText());
					playlist.setOption("listen");
					playlist.setImg(img.getImage());
					playlist.setList(list);
				if(colorTheme.isEmpty()==false) {playlist.setcolor(colorTheme.get(0));}
					
			        Scene scene = addBtn.getScene();
			        root.translateYProperty().set(scene.getHeight());
			        StackPane parentContainer = (StackPane) addBtn.getScene().getRoot();

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
			ap.getChildren().add(ten);
			ap.getChildren().add(img);
			ap.getChildren().add(trash);
			Parent.getChildren().add(ap);
			}
		
	}
	 public static Color fromArgbString(String argb) {
	        // Remove the "0x" prefix
	        if (argb.startsWith("0x")) {
	            argb = argb.substring(2);
	        }

	        // Parse the color components
	        int a = Integer.parseInt(argb.substring(0, 2), 16);
	        int r = Integer.parseInt(argb.substring(2, 4), 16);
	        int g = Integer.parseInt(argb.substring(4, 6), 16);
	        int b = Integer.parseInt(argb.substring(6, 8), 16);

	        // Convert to a JavaFX Color object
	        return Color.rgb(r, g, b, a / 255.0);
	    }
}
module testupload {
	requires javafx.controls;
	requires javafx.fxml;
	requires cloudinary.core;
	requires javafx.media;
	requires org.apache.httpcomponents.httpclient.cache;
	requires java.desktop;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
	
	exports CONTROL; // export + open sẽ chia được class
	opens CONTROL;
}

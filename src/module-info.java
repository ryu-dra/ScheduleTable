module fxproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires java.net.http;
	
	opens application to javafx.graphics, javafx.fxml;
}

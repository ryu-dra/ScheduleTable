module fxproject {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	requires java.net.http;
	
	opens application to javafx.graphics, javafx.fxml;
	opens calendar to javafx.graphics, javafx.fxml;
	opens calendar.packageChange to javafx.graphics, javafx.fxml;
	opens calendar.scheduleTable to javafx.graphics, javafx.fxml;
	opens calendar.toDo to javafx.graphics, javafx.fxml;
}

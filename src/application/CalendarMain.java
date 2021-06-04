package application;

import calendar.CalendarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CalendarMain extends Application {
	public static CalendarController cController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/calendar/CalendarIndividual.fxml"));
		Scene scene = new Scene((BorderPane)fxmlLoader.load(),700,600);	
		cController = fxmlLoader.getController();
		scene.getStylesheets().add(getClass().getResource("/calendar/Calendar.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch();
	}

}

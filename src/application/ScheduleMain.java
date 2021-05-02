package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ScheduleMain extends Application {
	

	public static ScheduleTableController stController;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleIndividual.fxml"));
			Scene scene = new Scene((VBox)fxmlLoader.load(),150,600);			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stController = fxmlLoader.getController();
			primaryStage.setScene(scene);
			primaryStage.show();
			System.out.println(stController.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

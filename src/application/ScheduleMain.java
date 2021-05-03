package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
			
			try {
				if(!isEmpty()) {
					initScheduleLabel(loading());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
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
	
	boolean isEmpty() throws IOException {
    	File file = new File("SaveDataFile.ser") ;
    	boolean empty = !file.exists() || file.length()==0;
    	return empty;
    }
	
	ScheduleData loading() {
    	try (var in = new ObjectInputStream(new FileInputStream("SaveDataFile.ser"))){
    		var saveData = (ScheduleData)in.readObject();
    		return saveData;
    	}catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("static-access")
	void initScheduleLabel(ScheduleData data) {
	    var sLabel = new Label();
	 	double stNum = (data.getsTime().getHour()+data.getsTime().getMinute()/60)*30+4;
	 	double ftNum = (data.getfTime().getHour()+data.getfTime().getMinute()/60)*30+4;
	 	double tNum = ftNum-stNum;
	 	String str = data.gettitle()+"\n"+data.gettime()+"\n"+data.getDetail();
	 	sLabel.setText(str);
	 	
	 	stController.getaPane().getChildren().add(sLabel);
	 	stController.getaPane().setTopAnchor(sLabel, stNum);
	 	sLabel.setPrefHeight(tNum);
	 		
	}
}

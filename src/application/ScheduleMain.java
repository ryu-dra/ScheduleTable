package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ScheduleMain extends Application {
	

	public static ScheduleTableController stController;
	public static ScheduleSelectController ssController;
	public static List<ScheduleData> list = new ArrayList<ScheduleData>();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleIndividual.fxml"));
			Scene scene = new Scene((VBox)fxmlLoader.load(),150,600);			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stController = fxmlLoader.getController();
			
			try {
				if(!isEmpty()) {
					List<ScheduleData> initList = loading();
					for(ScheduleData sd : initList) {
						list.add(sd);
						initScheduleLabel(sd);
					}
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
		
		 primaryStage.showingProperty().addListener((observable, oldValue, newValue) -> {
	            if (oldValue == true && newValue == false) {
	                saveStatus();
	            }
	        });
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	boolean isEmpty() throws IOException {
    	File file = new File("SaveDataFile.ser") ;
    	boolean empty = !file.exists() || file.length()==0;
    	return empty;
    }
	
	@SuppressWarnings("unchecked")
	List<ScheduleData> loading() {
		try(var in = new ObjectInputStream(new FileInputStream("SaveDataFile.ser"))){
			var initList = (List<ScheduleData>) in.readObject();
			return initList;
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
	 	sLabel.setStyle("-fx-background-color:#CCCCCC;");
	 	stController.getaPane().getChildren().add(sLabel);
	 	stController.getaPane().setTopAnchor(sLabel, stNum);
	 	sLabel.setPrefHeight(tNum);
	 		
	}
	
	void saveStatus() {
		 try (var out = new ObjectOutputStream(new FileOutputStream( "SaveDataFile.ser"))){
             if(list != null) {
            	 out.writeObject(list);
             }
         }catch (IOException e) {
             e.printStackTrace();
         }
	}
}

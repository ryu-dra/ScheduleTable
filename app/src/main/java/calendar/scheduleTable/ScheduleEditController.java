package calendar.scheduleTable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScheduleEditController {
	
	public static ScheduleEditController sec;
	public static Stage stage;
	public static Stage editStage;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane scheduleEdit;

    @FXML
    void DELETE(MouseEvent event) {
    		var alert = new Alert(AlertType.WARNING,"削除する？",ButtonType.OK,ButtonType.CANCEL);
    		alert.showAndWait().ifPresent(response -> {
    			if(response == ButtonType.OK) {
    				ShowDeleteWindow();
    				alert.close();
    			}else {
    				alert.close();
    			}
    		});
    }

    @FXML
    void EDIT(MouseEvent event) throws IOException {
    	showEditWindow();
    }
    
    void showEditWindow() throws IOException {
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/calendar/scheduleTable/ScheduleEditSelect.fxml"));
    		AnchorPane root = (AnchorPane) fxmlLoader.load();
    		Scene scene = new Scene(root);
    		editStage = new Stage();
    		editStage.setScene(scene);
    		editStage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    void ShowDeleteWindow(){
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../res/calendar/scheduleTable/ScheduleDelete.fxml"));
    		AnchorPane root = (AnchorPane) fxmlLoader.load();
    		Scene scene = new Scene(root);
    		stage = new Stage();
    		stage.setScene(scene);
    		stage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	public AnchorPane getScheduleEdit() {
		return scheduleEdit;
	}
}

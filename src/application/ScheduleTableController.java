/**
 * Sample Skeleton for 'ScheduleIndividual.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScheduleTableController  {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pane"
    private VBox pane; // Value injected by FXMLLoader
    
    @FXML // fx:id="aPane"
    private AnchorPane aPane; // Value injected by FXMLLoader
    
    @FXML // fx:id="aPane"
    private VBox scheduleIndividual; // Value injected by FXMLLoader
    
    
    //ここからScheduleSelectのid

   
    
    @FXML
    void sets(MouseEvent event) {
    	try {
			showSecondWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

   
    
    void showSecondWindow() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleSelect.fxml"));
		AnchorPane root = (AnchorPane) fxmlLoader.load();
		ScheduleMain.ssController = fxmlLoader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();

	}
    
    
	

	public AnchorPane getaPane() {
		return aPane;
	}



	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
   		
		
 		
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";
       
    }
	
}

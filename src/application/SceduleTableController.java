/**
 * Sample Skeleton for 'ScheduleIndividual.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class SceduleTableController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pane"
    private Pane pane; // Value injected by FXMLLoader

    @FXML // fx:id="spanOfTime"
    private Label spanOfTime; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	
    	
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";
        assert spanOfTime != null : "fx:id=\"spanOfTime\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";

    }
}

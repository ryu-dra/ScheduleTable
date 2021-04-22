package application;

/**
 * Sample Skeleton for 'ScheduleIndividual.fxml' Controller Class
 */

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SceduleTableController implements Initializable {
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="hours"
    private Label hours; // Value injected by FXMLLoader

    @FXML // fx:id="list"
    private ListView<ScheduleData> list; // Value injected by FXMLLoader
    

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	list.setCellFactory(new Callback<ListView<ScheduleData>, ListCell<ScheduleData>>() { // (1)
            @Override
            public ListCell<ScheduleData> call(ListView<ScheduleData> list) {
                return new ScheduleDataCell();
            }
        });
   
            ObservableList<ScheduleData> schedules = FXCollections.observableArrayList(
            		new ScheduleData("部活", LocalTime.of(12, 30), LocalTime.of(16, 0), "試合"),
            		new ScheduleData("部活", LocalTime.of(12, 30), LocalTime.of(16, 0), "試合"));
    				list.setItems(schedules);
    				
    	
    }
}

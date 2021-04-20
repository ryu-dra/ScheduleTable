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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SceduleTableController implements Initializable {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="table"
    private TableView<ScheduleData> table; // Value injected by FXMLLoader

    @FXML // fx:id="scheduleColumn"
    private TableColumn<ScheduleData,ScheduleData> scheduleColumn; // Value injected by FXMLLoader		//ScheduleDataを直で渡す。

    @FXML // fx:id="timeColumn"
    private TableColumn< ?,String> timeColumn; // Value injected by FXMLLoader
    

    @Override
	public void initialize(URL location, ResourceBundle resources) {
            scheduleColumn.setCellValueFactory(x -> x.getValue());				//ラムダ式を習得してから見直す。
            scheduleColumn.setCellFactory(x -> new ScheduleDataCell());
   
            ObservableList<ScheduleData> schedules = FXCollections.observableArrayList(
            		new ScheduleData("部活", LocalTime.of(12, 30), LocalTime.of(16, 0), "試合"),
            		new ScheduleData("部活", LocalTime.of(12, 30), LocalTime.of(16, 0), "試合"));
    				table.setItems(schedules);
    }
}

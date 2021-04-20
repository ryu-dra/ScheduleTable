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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class SceduleTableController implements Initializable {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="table"
    private TableView<ScheduleData> table; // Value injected by FXMLLoader

    @FXML // fx:id="scheduleColumn"
    private TableColumn<String,ScheduleData> scheduleColumn; // Value injected by FXMLLoader

    @FXML // fx:id="timeColumn"
    private TableColumn<String, ?> timeColumn; // Value injected by FXMLLoader
    

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	scheduleColumn.setCellFactory(new Callback<TableColumn<String,ScheduleData>, TableCell<String,ScheduleData>>() { // (1)
            @Override
            public TableCell<String,ScheduleData> call(TableColumn<String,ScheduleData> tableColumn) {
                return new ScheduleDataCell();
            }
    	});
    	ObservableList<ScheduleData> scheduleDatas = createScheduleDatas();   	
    	table.setItems(scheduleDatas);
    	
        assert scheduleColumn != null : "fx:id=\"sheduleColumn\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";
        assert timeColumn != null : "fx:id=\"timColumn\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";

    }
    
    private ObservableList<ScheduleData> createScheduleDatas(){
    	ObservableList<ScheduleData> scheduleDatas = FXCollections.observableArrayList();
    	
    	ScheduleData data1 = new ScheduleData("部活",LocalTime.of(12,30),LocalTime.of(16,0),"試合");
    	
    	scheduleDatas.add(data1);
    	
    	return scheduleDatas;
    }
}

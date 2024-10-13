package calendar.toDo;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dataClass.ScheduleData;
import database.PackagesDAO;
import database.ScheduleConnection;
import database.ScheduleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ToDoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ScheduleData, LocalDate> date;

    @FXML
    private TableColumn<ScheduleData, String> toDo;
    
    @FXML
    private CheckBox hudan;

    @FXML
    private CheckBox mukasi;

    @FXML
    private VBox packages;

    @FXML
    private TableView<ScheduleData> table;
    
    @FXML
    private ScrollPane sp;
    
    @FXML
    private VBox vb;
    
    private ObservableList<ScheduleData> oList;
    private ScheduleConnection scn = new ScheduleConnection();
   	private ScheduleDAO dao = new ScheduleDAO(scn.getConnection());
   	private PackagesDAO pdao = new PackagesDAO(scn.getConnection());
   	
   	@FXML
    void reset(MouseEvent event) {
   		oList.clear();
   		oList.addAll(dao.findByDateCompare(LocalDate.now()));
    }
   	
   	

    @FXML
    void initialize() {
    	//表に追加
    	oList = FXCollections.observableArrayList();
    	for(ScheduleData data :dao.findByDateCompare(LocalDate.now())) {
    		oList.add(data);
    	}   	
        date.setCellValueFactory(x -> x.getValue().dateProperty());
        toDo.setCellValueFactory(x -> x.getValue().titleProperty());
        table.itemsProperty().setValue(oList);       
        ArrayList<String> packList = new ArrayList<String>();
        packList.addAll(pdao.find());
        for(String data :packList) {
        	Button button = new Button(data);
        	button.setOnAction(new EventHandler<ActionEvent>() {				
				@Override
				public void handle(ActionEvent var1) {
					choice(data);					
				}
			});
        	packages.getChildren().add(button);
        }
        table.setEditable(true);
        

    }
    
    private void choice(String pack) {
    	oList.clear();
    	if(mukasi.selectedProperty().get()) {
    		for(ScheduleData data :dao.findByPackage(pack)) {
        		oList.add(data);
        	} 
    	}else {
    		for(ScheduleData data :dao.findByPackageFuture(pack,LocalDate.now())) {
        		oList.add(data);
        	} 
    	}
    }
}

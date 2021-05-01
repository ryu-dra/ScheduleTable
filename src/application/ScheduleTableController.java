/**
 * Sample Skeleton for 'ScheduleIndividual.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML // fx:id="scheduleName"
    private TextField scheduleName; // Value injected by FXMLLoader

    @FXML // fx:id="sHour"
    private ComboBox<String> sHour; // Value injected by FXMLLoader

    @FXML // fx:id="sakusei"
    private Button sakusei; // Value injected by FXMLLoader

    @FXML // fx:id="packageSelect"
    private ComboBox<?> packageSelect; // Value injected by FXMLLoader

    @FXML // fx:id="month"
    private ComboBox<?> month; // Value injected by FXMLLoader

    @FXML // fx:id="scheduleSelct"
    private AnchorPane scheduleSelect; // Value injected by FXMLLoader

    @FXML // fx:id="year"
    private ComboBox<String> year; // Value injected by FXMLLoader

    @FXML // fx:id="fHour"
    private ComboBox<String> fHour; // Value injected by FXMLLoader

    @FXML // fx:id="fMinute"
    private ComboBox<String> fMinute; // Value injected by FXMLLoader

    @FXML // fx:id="memo"
    private TextArea memo; // Value injected by FXMLLoader

    @FXML // fx:id="sMinute"
    private ComboBox<String> sMinute; // Value injected by FXMLLoader

    @FXML // fx:id="day"
    private ComboBox<String> day; // Value injected by FXMLLoader

    @FXML
    void clickMemo(MouseEvent event) {
    	
    }
    
    @FXML
    void sets(MouseEvent event) {
    	try {
			showSecondWindow();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void jikkou(MouseEvent event) {
    	addScheduleLabel(addData());
    }
    
    void showSecondWindow() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("ScheduleSelect.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();

	}
    
    ScheduleData addData() {
    	String name = scheduleName.getText();
    	var sTime = LocalTime.of(Integer.parseInt(sHour.getValue()),Integer.parseInt(sMinute.getValue()));
    	var fTime = LocalTime.of(Integer.parseInt(fHour.getValue()),Integer.parseInt(fMinute.getValue()));
    	String detail = memo.getText();
       	var sd = new ScheduleData(name, sTime, fTime, detail);
    	return sd;
    }
    
    @SuppressWarnings("static-access")
	void addScheduleLabel(ScheduleData data) {
    	var sLabel = new Label();
 		double stNum = (data.getsTime().getHour()+data.getsTime().getMinute()/60)*30+4;
 	    double ftNum = (data.getfTime().getHour()+data.getfTime().getMinute()/60)*30+4;
 	    double tNum = ftNum-stNum;
 		String str = data.gettitle()+"\n"+data.gettime()+"\n"+data.getDetail();
 		sLabel.setText(str);
 		aPane.getChildren().add(sLabel);
 		aPane.setTopAnchor(sLabel, stNum);
 		sLabel.setPrefHeight(tNum);
 		}
	

	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
   		
 		
 		
    	 
 				
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";
       
    }
}

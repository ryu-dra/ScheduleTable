/**
 * Sample Skeleton for 'ScheduleIndividual.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SceduleTableController {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pane"
    private VBox pane; // Value injected by FXMLLoader
    
    @FXML // fx:id="aPane"
    private AnchorPane aPane; // Value injected by FXMLLoader


    @SuppressWarnings("static-access")
	@FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
   		var sLabel = new Label();
 		var data = new ScheduleData("部活",LocalTime.of(12,0),LocalTime.of(13,0),"試合");
 		double stNum = (data.getsTime().getHour()+data.getsTime().getMinute()/60)*30+4;
 	    double ftNum = (data.getfTime().getHour()+data.getfTime().getMinute()/60)*30+4;
 	    double tNum = ftNum-stNum;
 		String str = data.gettitle()+"\n"+data.gettime()+"\n"+data.getDetail();
 		sLabel.setText(str);
 		aPane.getChildren().add(sLabel);
 		aPane.	setTopAnchor(sLabel, stNum);
 		sLabel.setPrefHeight(tNum);
 		
 		
    	 
 				
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'ScheduleIndividual.fxml'.";
       
    }
}

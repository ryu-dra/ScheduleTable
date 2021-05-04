package application;

import java.io.IOException;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ScheduleSelectController {

	 @FXML // fx:id="scheduleName"
	    private TextField scheduleName; // Value injected by FXMLLoader

	    @FXML // fx:id="sHour"
	    private ComboBox<String> sHour; // Value injected by FXMLLoader

	    @FXML // fx:id="sakusei"
	    private Button sakusei; // Value injected by FXMLLoader

	    @FXML // fx:id="packageSelect"
	    private ComboBox<String> packageSelect; // Value injected by FXMLLoader

	    @FXML // fx:id="month"
	    private ComboBox<String> month; // Value injected by FXMLLoader

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
	    void jikkou(MouseEvent event) {
	    	var editData = addData();
	    	addScheduleLabel(editData);
	    	ScheduleMain.list.add(editData);
	    	System.out.println("追加しました。");
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
	 		
	 		ScheduleTableController controller = ScheduleMain.stController;	 		
	 		controller.getaPane().getChildren().add(sLabel);
	 		controller.getaPane().setTopAnchor(sLabel, stNum);
	 		sLabel.setPrefHeight(tNum);
	 		}
	    
	    @FXML private void initialize() throws IOException {
	    	
		}
	    //実行されるたびにListを展開して、新しいListに加える（Mainで）。
	    
}

package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ScheduleEditSelectController {

	 @FXML
	    private TextField scheduleName;

	    @FXML
	    private ComboBox<String> oldMonth;

	    @FXML
	    private ComboBox<String> sHour;

	    @FXML
	    private ComboBox<String> packageSelect;

	    @FXML
	    private ComboBox<String> year;

	    @FXML
	    private ComboBox<String> fMinute;

	    @FXML
	    private TextArea memo;

	    @FXML
	    private ComboBox<String> sMinute;

	    @FXML
	    private ComboBox<String> oldYear;

	    @FXML
	    private AnchorPane scheduleSelect;

	    @FXML
	    private ComboBox<String> month;

	    @FXML
	    private ComboBox<String> fHour;

	    @FXML
	    private ComboBox<String> oldName;

	    @FXML
	    private ComboBox<String> day;

	    @FXML
	    private ComboBox<String> oldDay;
	    
	    private ScheduleConnection scn = new ScheduleConnection();
		private ScheduleDAO dao = new ScheduleDAO(scn.getConnection());
	    

	    @FXML
	    void edit(MouseEvent event) {	
	    	int oldYear = Integer.parseInt(this.oldYear.getValue());
	    	int oldMonth = Integer.parseInt(this.oldMonth.getValue());
	    	int oldDay = Integer.parseInt(this.oldDay.getValue());
	    	String oldName = this.oldName.getValue();
	    	dao.update(LocalDate.of(oldYear,oldMonth,oldDay),oldName,addData());
	    	
	    }
	    
	    ScheduleData addData() {
	    	int year = Integer.parseInt(this.year.getValue());
	    	int month = Integer.parseInt(this.month.getValue());
	    	int date = Integer.parseInt(this.day.getValue());
	    	String packageSelect = this.packageSelect.getValue();
	    	String name = scheduleName.getText();
	    	var sTime = LocalTime.of(Integer.parseInt(sHour.getValue()),Integer.parseInt(sMinute.getValue()));
	    	var fTime = LocalTime.of(Integer.parseInt(fHour.getValue()),Integer.parseInt(fMinute.getValue()));
	    	String detail = memo.getText();
	       	var sd = new ScheduleData(LocalDate.of(year, month, date),name, sTime, fTime, detail,packageSelect);
	    	return sd;
	    }
	    
	    
	    @SuppressWarnings("static-access")
		void addScheduleLabel(ScheduleData data) {
	    	var sLabel = new Label();
	 		double stNum = (data.getStartTime().getHour()+data.getStartTime().getMinute()/60)*30+4;
	 	    double ftNum = (data.getFinishTime().getHour()+data.getFinishTime().getMinute()/60)*30+4;
	 	    double tNum = ftNum-stNum;
	 		String str = data.getTitle()+"\n"+data.gettime()+"\n"+data.getDetail();
	 		sLabel.setText(str);
	 		
	 		ScheduleTableController controller = CalendarController.stController;	 		
	 		controller.getaPane().getChildren().add(sLabel);
	 		controller.getaPane().setTopAnchor(sLabel, stNum);
	 		sLabel.setPrefHeight(tNum);
	 		}
	    
	    @SuppressWarnings("static-access")
		@FXML private void initialize() throws IOException {
	    	var ld = CalendarMain.cController.ld;
	    	year.setValue(Integer.toString(ld.getYear()));
	    	oldYear.setValue(Integer.toString(ld.getYear()));
	    	month.setValue(Integer.toString(ld.getMonthValue()));
	    	oldMonth.setValue(Integer.toString(ld.getMonthValue()));
	    	day.setValue(Integer.toString(ld.getDayOfMonth()));
	    	oldDay.setValue(Integer.toString(ld.getDayOfMonth()));
	    	System.out.println(year.getEditor());
		}
	    
}
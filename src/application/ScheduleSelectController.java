package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

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
import javafx.stage.Stage;

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
	    
	    private ScheduleConnection scn = new ScheduleConnection();
		private ScheduleDAO dao = new ScheduleDAO(scn.getConnection());
	    

	    @FXML
	    void edit(MouseEvent event) {
	    	
	    	try {
				var root = (AnchorPane)FXMLLoader.load(getClass().getResource("ScheduleEditSelect.fxml"));
				Scene scene = new Scene(root);			
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void jikkou(MouseEvent event) {
	    	var editData = addData();
	    	addScheduleLabel(editData);
	    	dao.insert(editData);
	    	System.out.println("追加しました。");
	    	
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
	 		sLabel.setOnMouseClicked(event -> {
				try {
					showEditWindow();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			});
	 		ScheduleTableController controller = CalendarController.stController;	 		
	 		controller.getaPane().getChildren().add(sLabel);
	 		controller.getaPane().setTopAnchor(sLabel, stNum);
	 		sLabel.setPrefHeight(tNum);
	 		}
	    
	    @SuppressWarnings("static-access")
		@FXML private void initialize() throws IOException {
	    	var ld = CalendarMain.cController.ld;
	    	year.setValue(Integer.toString(ld.getYear()));
	    	month.setValue(Integer.toString(ld.getMonthValue()));
	    	day.setValue(Integer.toString(ld.getDayOfMonth()));
	    	System.out.println(year.getEditor());
		}
	    
	    void showEditWindow() throws IOException {
	    	try {
	    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleEdit.fxml"));
	    		AnchorPane root = (AnchorPane) fxmlLoader.load();
	    		Scene scene = new Scene(root);
	    		Stage stage = new Stage();
	    		stage.setScene(scene);
	    		stage.showAndWait();
			} catch(Exception e) {
				e.printStackTrace();
			}
	    }
}

/**
 * Sample Skeleton for 'CalendarIndividual.fxml' Controller Class
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalendarController {
	
	static LocalDate ld;
	public static ScheduleTableController stController;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="hiduke"
    private AnchorPane hiduke; // Value injected by FXMLLoader

    @FXML // fx:id="file"
    private Tab file; // Value injected by FXMLLoader

    @FXML // fx:id="tools"
    private AnchorPane tools; // Value injected by FXMLLoader

    @FXML // fx:id="colors"
    private Tab colors; // Value injected by FXMLLoader

    @FXML // fx:id="calendarTable"
    private GridPane calendarMatrix; // Value injected by FXMLLoader
    
    @FXML
    private Label tuki;
    
    int year;

    int month;

    int date;

    int lastDate;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	List<String> youbi = new ArrayList<String>(7) {
	    	{	add("日");
	    		add("月");
	    		add("火");
	    		add("水");
	    		add("木");
	    		add("金");
	    		add("土");
    		
    		}
    	};
    	
    	for(int i=0; i<7; i++) {
    		Label label = new Label(youbi.get(i));
    		label.setAlignment(Pos.CENTER);
    		calendarMatrix.add(label, i,0);
    		label.setPrefWidth(Integer.MAX_VALUE);
    	}
    	
    	ld = LocalDate.now();
    	setCalendar(ld);
    	
    }
    


    @SuppressWarnings("static-access")
	private void setCalendar(LocalDate ld) {
     year = ld.getYear();
     month = ld.getMonthValue();
     
	     // 月の初めの曜日を求めます。
	 var sYoubi = LocalDate.of(year, month, 1).getDayOfWeek();
     
	      // 月末の日付を求めます。
	  lastDate = ld.lengthOfMonth();
	      // カレンダー表を作成します。
	  int row = 1;
	  int column = sYoubi.getValue() ; 
	  for (date = 1; date <= lastDate; date++) {
		  Label label = new Label(String.valueOf(date));
	    calendarMatrix.add(label, column, row);
	    label.setAlignment(Pos.TOP_CENTER);
	    label.setPrefWidth(Integer.MAX_VALUE);
	    label.setOnMouseClicked(event -> {
			try {
				int day = Integer.valueOf(label.getText());
				this.ld = LocalDate.of(year, month, day);
				showScheduleTable();
				} catch (IOException e) {
			 e.printStackTrace();}});//イベント
	    
		    if (column == 6) {
		      row++;
		      column = 0;
		    } else {
		      column++;
		    }
	  }
	  
	  //月ラベルに値を設定
	  tuki.setText(String.valueOf(month)+"月");
    }
    
    void showScheduleTable() throws IOException  {
    	var scn = new ScheduleConnection();
		var dao = new ScheduleDAO(scn.getConnection());
	
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleIndividual.fxml"));
			Scene scene;
			try {
				scene = new Scene((VBox)fxmlLoader.load(),150,600);		
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stController = fxmlLoader.getController();
			
				List<ScheduleData> initList = dao.findByDate(ld);
				for(ScheduleData sd : initList) {
					initScheduleLabel(sd);
				}
				
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
    }
    
    @SuppressWarnings("static-access")
	void initScheduleLabel(ScheduleData data) {
	 	double stNum = (data.getStartTime().getHour()+data.getStartTime().getMinute()/60)*30+4;
	 	double ftNum = (data.getFinishTime().getHour()+data.getFinishTime().getMinute()/60)*30+4;
	 	double tNum = ftNum-stNum;
	 	String str = data.getTitle()+"\n"+data.gettime()+"\n"+data.getDetail();
	 	
	 	var sLabel = new Label();
	 	sLabel.setText(str);
	 	sLabel.setOnMouseClicked(event -> {
			try {
				showEditWindow();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		});
	 	sLabel.setStyle("-fx-background-color:#CCCCCC;");		 	
	 	stController.getaPane().getChildren().add(sLabel);
		stController.getaPane().setTopAnchor(sLabel, stNum);
		sLabel.setPrefHeight(tNum);
	}
    
    void showEditWindow() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScheduleEdit.fxml"));
		AnchorPane root = (AnchorPane) fxmlLoader.load();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.showAndWait();
    }

    
}

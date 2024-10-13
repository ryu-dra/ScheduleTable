/**
 * Sample Skeleton for 'CalendarIndividual.fxml' Controller Class
 */

package calendar;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import calendar.packageChange.PackageAndColorCell;
import calendar.scheduleTable.AddDataAndLabel;
import calendar.scheduleTable.CreateDataAndLabel;
import calendar.scheduleTable.ScheduleTableController;
import dataClass.PackAndColorData;
import dataClass.ScheduleData;
import database.PackagesDAO;
import database.ScheduleConnection;
import database.ScheduleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CalendarController {
	
	public static LocalDate ld;
	public static ScheduleTableController stController;
	public static Stage imageStage;
	

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
    
    @FXML
    private BorderPane calendar;
    
    @FXML
    private ComboBox<String> pp;
    
    @FXML
    private ColorPicker cp;
    
    @FXML
    private DatePicker dp;
    
    @FXML
    private ListView<PackAndColorData> packColor;
    
    int year;

    int month;

    int date;

    int lastDate;
    
    CreateDataAndLabel adal = new AddDataAndLabel();
    private ScheduleConnection scn = new ScheduleConnection();
	private ScheduleDAO dao = new ScheduleDAO(scn.getConnection());
	private PackagesDAO pdao = new PackagesDAO(scn.getConnection());	
	private ObservableList<String> pItems = FXCollections.observableArrayList();

	 @FXML
	 void toDo(MouseEvent event) throws IOException {
	   	var fxmlLoader = new FXMLLoader(getClass().getResource("../res/calendar/todo/ToDo.fxml"));
		Scene scene = new Scene((BorderPane)fxmlLoader.load(),400,400);	
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
	   }

	 @FXML
	 void edit(MouseEvent event) throws IOException {
		var fxmlLoader = new FXMLLoader(getClass().getResource("../res/calendar/packageChange/PackageEdit.fxml"));
		Scene scene = new Scene((BorderPane)fxmlLoader.load(),400,400);	
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
	   }
	 
	   @FXML
	    void imageEdit(MouseEvent event) throws IOException {
		   var fxmlLoader = new FXMLLoader(getClass().getResource("../res/calendar/ImageEdit.fxml"));
			Scene scene = new Scene((AnchorPane)fxmlLoader.load(),400,400);	
			imageStage = new Stage();
			imageStage.setScene(scene);
			imageStage.show();
	    }
	 
	 @FXML
	 void kettei(MouseEvent event) throws Exception, URISyntaxException {
		 calendarMatrix.getChildren().clear();
		 calendarMatrix.setGridLinesVisible(false);
		 calendarMatrix.setGridLinesVisible(true);
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
	    		label.getStyleClass().add("label_");
	    		label.setPrefWidth(Integer.MAX_VALUE);
	    	}
	    	
	    	ld = dp.getValue();
			 setCalendar(dp.getValue());
		   }
	 
	 @FXML
	    void tekiyou(MouseEvent event) {
	    	var pack = pp.getValue();
	    	var col = cp.getValue().toString();
	    	pdao.setColors(pack, col);
	    }
	 
	 @FXML
	 void reload(MouseEvent event) throws Exception, Exception {
		 calendarMatrix.getChildren().clear();
		 calendarMatrix.setGridLinesVisible(false);
		 calendarMatrix.setGridLinesVisible(true);
		 initialize();
	 }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, URISyntaxException {
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
    		label.getStyleClass().add("label_");
    		label.setPrefWidth(Integer.MAX_VALUE);
    	}
    	
    	ld = LocalDate.now();
    	setCalendar(ld);
    	
    	pItems.addAll(pdao.find());
    	pp.setItems(pItems);
    }
    
    
    @SuppressWarnings("static-access")
	private void setCalendar(LocalDate ld) throws IOException, URISyntaxException {
    	calendarMatrix.setGridLinesVisible(true);
     year = ld.getYear();
     month = ld.getMonthValue();
     
	     // 月の初めの曜日を求めます。
	 var sYoubi = LocalDate.of(year, month, 1).getDayOfWeek();
     
	      // 月末の日付を求めます。
	  lastDate = ld.lengthOfMonth();
	      // カレンダー表を作成します。
	  int row = 1;
	  int column = sYoubi.getValue();		//以下で、配列の値とこの値を対応させる処理をおこなう。
	  if(column == 7) {
		  column = 0;			//日曜日を0とすれば、youbi<String>の配列と対応して都合がよい。
	  }
	  for (date = 1; date <= lastDate; date++) {
		  VBox vb = new VBox(); 
		  Label label = new Label(String.valueOf(date));
		  label.getStyleClass().add("label_");
		  label.setAlignment(Pos.TOP_CENTER);
		  label.setPrefWidth(Integer.MAX_VALUE);
	      vb.setOnMouseClicked(event -> {
	    	try {
				int day = Integer.valueOf(label.getText());
				this.ld = LocalDate.of(year, month, day);
				showScheduleTable();
				} catch (IOException e) {
			 e.printStackTrace();}});//イベント
	    	vb.getChildren().add(label);
	      if(LocalDate.of(year, month, date).equals(ld)) {
	    	  vb.setStyle("-fx-background-color:#fbf8c4");
	      }
	       
	    	var toDoToDay = dao.findByDate(LocalDate.of(year, month, date));
	        for(var data :toDoToDay) {
	        	String colStr = pdao.findColor(data.packageSelectProperty().get());
	        	if(colStr==null) {
	        		colStr="#FFFFFF";
	        	}
	        	var rx = "0x";
	        	if(colStr.contains(rx)) {
	        		colStr = colStr.replaceAll("0x","#");
	        	}
	        	var toDoLabel = new Label(data.titleProperty().get());
	        	toDoLabel.setStyle("-fx-text-fill: #006464; -fx-border-radius: 20; -fx-background-radius: 20; -fx-background-color: " +colStr+";"); 
	    	    vb.getChildren().add(toDoLabel);
	        }
	        calendarMatrix.add(vb, column, row);
		    if (column == 6) {
		      row++;
		      column = 0;
		    } else {
		      column++;
		    }
		    
		    //パッケージと色の表
		    packColor.setCellFactory(new Callback<ListView<PackAndColorData>, ListCell<PackAndColorData>>() { // (1)
	            @Override
	            public ListCell<PackAndColorData> call(ListView<PackAndColorData> listView) {
	                return new PackageAndColorCell();
	            }
	        });
	        ObservableList<PackAndColorData> datas = FXCollections.observableArrayList();
	        datas.addAll(pdao.findAll());
	        packColor.setItems(datas);	        
	  }
	  
	  //月ラベルに値を設定
	  tuki.setText(String.valueOf(month)+"月");
	  
	  var str = "";
	  
	  try(var reader = Files.newBufferedReader(
			  Paths.get(this.getClass().getResource("../res/calendarImage.txt").toURI()))){
		  var line = "";
		  while((line = reader.readLine()) != null) {
			  str = line;
		  }
	  }
	  
	  
	  calendarMatrix.setStyle("-fx-background-image: url(file:"+str+");-fx-background-repeat:stretch;	-fx-background-position: center center;	-fx-background-size: 400 400;-fx-background-radius: 5.0;-fx-border-style:  solid;-fx-effect: dropshadow(three-pass-box,rgba(128,128,128,0.5),200,0.5,0,0);-fx-text-fill: chocolate;");
    }
    
    void showScheduleTable() throws IOException  {
	
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/calendar/scheduleTable/ScheduleIndividual.fxml"));
		Scene scene = new Scene((VBox)fxmlLoader.load(),150,670);		
		stController = fxmlLoader.getController();
		
		List<ScheduleData> initList = dao.findByDate(ld);
		for(ScheduleData sd : initList) {
			adal.createScheduleLabel(sd,stController.getaPane());
		}
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
}

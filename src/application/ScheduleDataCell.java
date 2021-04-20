package application;

import javafx.scene.control.TableCell;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScheduleDataCell extends TableCell<String,ScheduleData> {
	   private VBox cellContainer;
	   private Text scheduleTitle;
	   private Text time;
	   private Text txtDetail;
	   private boolean bound = false;
	   
	   public ScheduleDataCell() {
		   initComponent();
	        initStyle();
	   }
	   
	   public void initStyle() {
		   scheduleTitle.setFont(new Font("System Bold", 18.0));
	   }
	   
	   
	   
	   public void initComponent() {
		   cellContainer = new VBox(5);		//Nodeを作成
		   scheduleTitle = new Text();
		   VBox.setVgrow(scheduleTitle, Priority.NEVER);
		   time = new Text();
		   VBox.setVgrow(time, Priority.ALWAYS);
		   txtDetail = new Text();
		   VBox.setVgrow(txtDetail, Priority.ALWAYS);
	   }
	   
	   @Override
	   public void updateItem(ScheduleData scheduleData, boolean empty) {
		   super.updateItem(scheduleData, empty);
		   if (!bound) { // セルの幅に合わせて折り返されるように、親となる ListView の width プロパティと、それぞれの wrappingWidth プロパティをバインドしています。
	            scheduleTitle.wrappingWidthProperty().bind(getTableView().widthProperty().subtract(25));
	            time.wrappingWidthProperty().bind(getTableView().widthProperty().subtract(25));
	            txtDetail.wrappingWidthProperty().bind(getTableView().widthProperty().subtract(25));
	            bound = true;
	        }
		   		   
		   
		   if (empty) {
	            setText(null);
	            setGraphic(null);
	        } else { // タイトルやら詳細やらを設定
	        	scheduleTitle.setText(scheduleData.titleProperty().get());
	            time.setText(scheduleData.timeProperty().get());
	            txtDetail.setText(scheduleData.detailProperty().get());
	            setGraphic(cellContainer);
	        }
	   }
}

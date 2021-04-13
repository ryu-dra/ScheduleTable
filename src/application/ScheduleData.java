package application;

import java.time.LocalTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ブックマークを表すモデル.
 */
public class ScheduleData {
    private StringProperty title = new SimpleStringProperty();
    private StringProperty time = new SimpleStringProperty();
    private StringProperty detail = new SimpleStringProperty();

    /**
     * コンストラクタ.
     * @param title スケジュールのタイトル
     * @param startTime 開始時間
     * @param finishTime 終了時刻
     * @param detail 詳細
     */
    public ScheduleData(String title,LocalTime startTime, LocalTime finishTime, String detail) {
    	this.title.set(title);    	
    	String time = startTime+"～"+finishTime;
    	this.time.set(time);
        this.detail.set(detail);
    }

    public StringProperty titleProperty() {
    	if(title==null) {
      	  title = new SimpleStringProperty("");
         }
         return title;
    }

    public StringProperty timeProperty() {
       if(time==null) {
    	  time = new SimpleStringProperty("");
       }
       return time;
    }

    public StringProperty detailProperty() {
    	if(detail==null) {
      	  detail = new SimpleStringProperty("");
         }
    	return detail;
    }
}
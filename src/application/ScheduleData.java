package application;

import java.time.LocalTime;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ブックマークを表すモデル.
 */
public class ScheduleData extends ObjectBinding<ScheduleData> {
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
    	this.time.set(startTime+"～"+finishTime);
        this.detail.set(detail);
        
        bind(this.title, this.time, this.detail);
    }

    public StringProperty titleProperty() {
    	if(title==null) {
      	  title = new SimpleStringProperty("TITLE");
         }
         return title;
    }

    public StringProperty timeProperty() {
       if(time==null) {
    	  time = new SimpleStringProperty("TIME");
       }
       return time;
    }

    public StringProperty detailProperty() {
    	if(detail==null) {
      	  detail = new SimpleStringProperty("DETAIL");
         }
    	return detail;
    }
    
    @Override
    protected ScheduleData computeValue() {
    	return this;
    }
}
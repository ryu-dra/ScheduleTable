package application;

import java.time.LocalTime;

/**
 * ブックマークを表すモデル.
 */
public class ScheduleData {
    private String title = new String();
    private String time = new String();
    private String detail = new String();
    private LocalTime startTime;
    private LocalTime finishTime;
    
    

    /**
     * コンストラクタ.
     * @param title スケジュールのタイトル
     * @param startTime 開始時間
     * @param finishTime 終了時刻
     * @param detail 詳細
     */
    public ScheduleData(String title,LocalTime startTime, LocalTime finishTime, String detail) {
    	this.title=title;    	
    	this.time=startTime+"～"+finishTime;
        this.detail=detail;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    
    public String gettitle() {
    	
         return title;
    }

    public String gettime() {
       
       return time;
    }

    public String getDetail() {
    	
    	return detail;
    }
    
    public LocalTime getsTime() {
    	return startTime;
    }
    
    public LocalTime getfTime() {
    	return finishTime;
    }
    
    
}
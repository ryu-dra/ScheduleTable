package application;

import java.time.LocalDate;
import java.time.LocalTime;


//DTOクラス

public class ScheduleData {
	
    private String title = null;
    private String time = null;
    private String detail = null;
    private LocalTime startTime;
    private LocalTime finishTime;
    private LocalDate date;
    private String packageSelect;
    
    /**
     * コンストラクタ.
     * @param year 年
     * @param month 月
     * @param date 日
     * @param title スケジュールのタイトル
     * @param startTime 開始時間
     * @param finishTime 終了時刻
     * @param detail 詳細
     * @param packageSelect パッケージ
     */
    public ScheduleData(LocalDate ld,String title,LocalTime startTime, LocalTime finishTime, String detail,String packageSelect) {
    	this.setDate(ld);
    	this.setTitle(title);    	
    	this.setTime(startTime+"～"+finishTime);
        this.setDetail(detail);
        this.setStartTime(startTime);
        this.setFinishTime(finishTime);
        this.setPackageSelect(packageSelect);
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public LocalDate gettDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalTime finishTime) {
		this.finishTime = finishTime;
	}

	
	public String getPackageSelect() {
		return packageSelect;
	}

	public void setPackageSelect(String packageSelect) {
		this.packageSelect = packageSelect;
	}
    
	public String gettime() {
	       
	       return time;
	    }
    
    
}
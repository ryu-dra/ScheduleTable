package application;

import java.io.Serializable;
import java.time.LocalTime;

public class ScheduleData  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private String title = new String();
    private String time = new String();
    private String detail = new String();
    private LocalTime startTime;
    private LocalTime finishTime;
    private int year;
    private int month;
    private int date;
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
    public ScheduleData(int year,int month,int date,String title,LocalTime startTime, LocalTime finishTime, String detail,String packageSelect) {
    	this.setYear(year);
    	this.setMonth(month);
    	this.setDate(date);
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
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
package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ScheduleDAO {
	private Connection con;
	
	public ScheduleDAO(Connection con) {
		this.con = con;
	}
	
	public ArrayList<ScheduleData> findAll(){
		ArrayList<ScheduleData> list = new ArrayList<ScheduleData>();
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from datatable");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				LocalDate date = rs.getDate("date").toLocalDate();
				String title = rs.getString("title");
				LocalTime startTime = rs.getTime("startTime").toLocalTime();
				LocalTime finishTime = rs.getTime("finishTime").toLocalTime();
				String detail = rs.getString("detail");
				String packageSelect = rs.getString("packageSelect");
				

				var sd = new ScheduleData(date,title,startTime,finishTime,detail,packageSelect);
				list.add(sd);
			}

			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return list;
	}
	
	public ArrayList<ScheduleData> findByDate(LocalDate date){
		ArrayList<ScheduleData> list = new ArrayList<ScheduleData>();
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from datatable where date = ?");
			stmt.setDate(1, Date.valueOf(date));
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				var ymd = rs.getDate("date").toLocalDate();
				String title = rs.getString("title");
				LocalTime startTime = rs.getTime("startTime").toLocalTime();
				LocalTime finishTime = rs.getTime("finishTime").toLocalTime();
				String detail = rs.getString("detail");
				String packageSelect = rs.getString("packageSelect");
				

				var sd = new ScheduleData(ymd,title,startTime,finishTime,detail,packageSelect);
				list.add(sd);
			}

			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return list;
	}
	
	public void insert(ScheduleData sd) {
		try{
			String sql = "INSERT INTO datatable VALUES(?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setDate(1, Date.valueOf(sd.dateProperty().get()));;
			stmt.setString(2,sd.titleProperty().get());
			stmt.setTime(3,Time.valueOf(sd.startTimeProperty().get()));
			stmt.setTime(4,Time.valueOf(sd.finishTimeProperty().get()));
			stmt.setString(5,sd.detailProperty().get());
			stmt.setString(6,sd.packageSelectProperty().get());
			

			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("INSERTエラー：" + e.getMessage());
		}
	}
	
	public void update(LocalDate oldDate,String oldTitle,ScheduleData newSd) {
		try{
			String sql = "UPDATE datatable SET startTime=?,finishTime=?,detail=?,packageSelect=?,date=?,title=? WHERE date=? AND title =?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setTime(1,Time.valueOf(newSd.startTimeProperty().get()));
			stmt.setTime(2,Time.valueOf(newSd.finishTimeProperty().get()));
			stmt.setString(3,newSd.detailProperty().get());
			stmt.setString(4,newSd.packageSelectProperty().get());
			stmt.setDate(5, Date.valueOf(newSd.dateProperty().get()));;
			stmt.setString(6,newSd.titleProperty().get());
			stmt.setDate(7, Date.valueOf(oldDate));
			stmt.setString(8,oldTitle);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("UPDATE エラー：" + e.getMessage());
		}
	}
	
	public void delete(LocalDate ld, String title) {
		try{
			String sql = "DELETE FROM datatable WHERE date=? AND title=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDate(1,Date.valueOf(ld));
			stmt.setString(2,title);

			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("DELETE エラー：" + e.getMessage());
		}
	}
}

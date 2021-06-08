package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ScheduleConnection {
	private Connection con=null ;

	public Connection getConnection(){
		try {
			con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user\\SQLiteDatabase\\schedules");
		} catch (SQLException e) {
			System.out.println("DBコネクションエラー:" + e.getMessage());
		}
		return con;
	}

	public void close() {
		try {
			if( con != null ) {
				con.close();
			}
		}catch (SQLException e) {
			System.out.println("DBクローズエラー");
		}
	}
}

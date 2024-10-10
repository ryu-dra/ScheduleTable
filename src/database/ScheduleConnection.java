package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleConnection {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;

	public Connection getConnection(){
		try {
			con = DriverManager.getConnection("jdbc:sqlite:schedules.db");
			st = con.createStatement();
		} catch (SQLException e) {
//						//データベースが作られていない場合、新しく作る。
//		    String url = "jdbc:sqlite:schedules";
//
//		        try (Connection conn = DriverManager.getConnection(url)) {
//		            if (conn != null) {
//		                System.out.println("データベースが作成されました:schedules ");
//		            }
//		        } catch (SQLException e_1) {
//		            System.out.println("作成時のエラー:"+e_1.getMessage());
//		        }
			System.out.println(e.getMessage());
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

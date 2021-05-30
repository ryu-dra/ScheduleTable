package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PackagesDAO {
	private Connection con;
	
	public PackagesDAO(Connection con) {
		this.con = con;
	}
	
	public ArrayList<String> find(){
		ArrayList<String> list = new ArrayList<String>();
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from packages");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String packageSelect = rs.getString("package_");
				list.add(packageSelect);
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return list;
	}
	
	public ArrayList<PackAndColorData> findAll(){
		ArrayList<PackAndColorData> list = new ArrayList<PackAndColorData>();
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from packages");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String packageSelect = rs.getString("package_");
				String color = rs.getString("colors");
				list.add(new PackAndColorData(packageSelect, color));
			}
			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return list;
	}
	
	public void insert(String pack) {
		try{
			String packSql1 = "SELECT package_ FROM packages WHERE package_ = ?";
			PreparedStatement p1 = con.prepareStatement(packSql1);
			p1.setString(1, pack);
			ResultSet rs = p1.executeQuery();
			while (rs.next()) {
				String packageSelect = rs.getString("package_");
				if(packageSelect != null) {
					System.out.println("重複するパッケージが存在します。");
					return;
				}
			}
			String sql = "INSERT INTO packages VALUES(?,null)";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, pack);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("INSERTエラー：" + e.getMessage());
		}
	}
	
	
	public void delete(String pack) {
		try{
			String sql = "DELETE FROM packages WHERE package_ =?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,pack);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("DELETE エラー：" + e.getMessage());
		}
	}
	
	public void update(String oldPack,String newPack) {
		try{
			String packSql1 = "SELECT package_ FROM packages WHERE package_ = ?";
			PreparedStatement p1 = con.prepareStatement(packSql1);
			p1.setString(1, newPack);
			ResultSet rs = p1.executeQuery();
			while (rs.next()) {
				String packageSelect = rs.getString("package_");
				if(packageSelect != null) {
					System.out.println("重複するパッケージが存在します。");
					return;
				}
			}
			rs.close();
			p1.close();
			String sqlPack = "UPDATE packages SET package_=? WHERE package_ =?";
			PreparedStatement stmt = con.prepareStatement(sqlPack);
			stmt.setString(1,newPack);
			stmt.setString(2,oldPack);
			stmt.executeUpdate();
			
			String sqlSche = "UPDATE datatable SET packageSelect=? WHERE packageSelect =?";
			PreparedStatement stmtSche = con.prepareStatement(sqlSche);
			stmtSche.setString(1,newPack);
			stmtSche.setString(2,oldPack);
			stmtSche.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("UPDATE エラー：" + e.getMessage());
		}
	}
	
	public void setColors(String pack, String color) {
		try{
			String sql = "UPDATE packages SET colors =? WHERE package_ =?";
			PreparedStatement stmt = con.prepareStatement(sql);			
			stmt.setString(1, color);
			stmt.setString(2, pack);
			
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("INSERTエラー：" + e.getMessage());
		}
	}
	
	public String findColor(String pack){
		String str = null;
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT colors FROM packages WHERE package_ = ?");
			stmt.setString(1,pack);
			ResultSet rs = stmt.executeQuery();
			//str = rs.getString("colors");  while(rs.next())をしないといけない
			while(rs.next()){
				str = rs.getString("colors");
			}
			stmt.close();
			rs.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return str;
	}
	
	public boolean compare(String newPack) {
		var datas = find();
		for(var data : datas) {
			if(newPack.equals(data)){
				return false;
			}
		}
		return true;
	}
}

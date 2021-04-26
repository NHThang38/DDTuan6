package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.database;
import entity.ChiTietDatTour;
import entity.KhachSan;

public class control_KS {
	ArrayList<KhachSan> dsKS;
	KhachSan KS;
	public control_KS(){
		dsKS= new ArrayList<KhachSan>();	
		
	}	
	public ArrayList<KhachSan> show() {
		Connection con = database.Con();
		try {
			String sql = "Select * from KhachSan";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    int maKS; String tenKS; String diaChi; int tieuchuan; int sDT;
		    while (rs.next()) {
						 maKS =rs.getInt(1);
						 tenKS =rs.getString(2);
						 diaChi =rs.getString(3);
						 tieuchuan =rs.getInt(4);
						 sDT =rs.getInt(5);
						
						
				
				 KhachSan a = new KhachSan(maKS, tenKS, diaChi, tieuchuan, sDT);
				dsKS.add(a);
				
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
		return dsKS;
	}
	public void SaveKS( int maKS, String tenKS, String diaChi, int tieuchuan, int sDT) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into KhachSan values(? ,? ,?, ? ,?  )");
			stmt.setInt(1,maKS);
			stmt.setString(2,tenKS);
			stmt.setString(3,diaChi);
			stmt.setInt(4, tieuchuan);
			stmt.setInt(5, sDT);
		
			
			stmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	
	}
	public void deleteKS(int ma) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from KhachSan where maKhachSan= ?");
			stmt.setInt(1, ma);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void SuaKS(int maKS, String tenKS, String diaChi, int tieuchuan, int sDT) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
		try {
			
			 stmt = con.prepareStatement("update KhachSan "
						+ "set tenKhachSan = ?,"
						+ "diaChi = ?,"
						+ "tieuChuan = ? ,"
						+ "SDT= ? "
						+ "where maKhachSan= ?");
			
			stmt.setString(1,tenKS);
			
			stmt.setString(2,diaChi);
			stmt.setInt(3, tieuchuan);
			stmt.setInt(4,sDT);
			stmt.setInt(5, maKS);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
}

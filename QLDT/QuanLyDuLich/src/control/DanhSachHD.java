package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.database;
import entity.HoaDon;
import entity.Tuor;


public class DanhSachHD {
	ArrayList<HoaDon> dsHD;
	HoaDon HD;
	public DanhSachHD(){
		dsHD= new ArrayList<HoaDon>();	
		
	}	
	public ArrayList<HoaDon> show() {
		Connection con = database.Con();
		try {
			String sql = "Select * from HoaDon";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    int maHoaDon, maNV, maKH;Date ngayLapHD;
		    while (rs.next()) {
						 maHoaDon =rs.getInt(1);
						 ngayLapHD = rs.getDate(2);
						 maNV=rs.getInt(3);
						 maKH =rs.getInt(4);
						 
				
				 HoaDon a = new HoaDon(maHoaDon, ngayLapHD, maNV, maKH);
				dsHD.add(a);
				
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
		return dsHD;
	}
	public void SaveTuor(int maHoaDon, Date ngayLapHD, int maNV,int maKH) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into HoaDon values(? ,? ,?, ?)");
			stmt.setInt(1,maHoaDon);
			stmt.setDate(2,ngayLapHD);
			stmt.setInt(3,maNV);
			stmt.setInt(4, maKH);
		;
			stmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	
	}
	
}

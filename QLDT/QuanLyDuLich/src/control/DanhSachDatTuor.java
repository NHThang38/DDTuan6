package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.database;
import entity.ChiTietDatTour;
import entity.HoaDon;

public class DanhSachDatTuor {
	ArrayList<ChiTietDatTour> dsDT;
	ChiTietDatTour DT;
	public DanhSachDatTuor(){
		dsDT= new ArrayList<ChiTietDatTour>();	
		
	}	
	public ArrayList<ChiTietDatTour> show() {
		Connection con = database.Con();
		try {
			String sql = "Select * from DatTour";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    int maDat,maTuor,maKH;int soLuongNguoiLon,soLuongTreEm,dotuoi;
		    while (rs.next()) {
						 maDat =rs.getInt(1);
						 maTuor =rs.getInt(2);
						 maKH =rs.getInt(3);
						 soLuongNguoiLon =rs.getInt(4);
						 soLuongTreEm =rs.getInt(5);
						
						
				
				 ChiTietDatTour a = new ChiTietDatTour(maDat,maTuor,maKH,soLuongNguoiLon,soLuongTreEm);
				dsDT.add(a);
				
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
		return dsDT;
	}
	public void SaveDT( int maDat,int maTuor,int maKH,int soLuongNL,int soLuongTE ) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into DatTour values(? ,? ,?, ? ,?  )");
			stmt.setInt(1,maDat);
			stmt.setInt(2,maTuor);
			stmt.setInt(3,maKH);
			stmt.setInt(4, soLuongNL);
			stmt.setInt(5, soLuongTE);
		
			
			stmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	
	}
	public void delete(int ma) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from DatTour where maDT = ?");
			stmt.setInt(1, ma);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}


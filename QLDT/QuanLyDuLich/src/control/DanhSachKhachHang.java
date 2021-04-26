package control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import database.database;
import entity.KhachHang;


public class DanhSachKhachHang {

	ArrayList<KhachHang> dsKH;
	KhachHang kh;

	public DanhSachKhachHang(){
		dsKH = new ArrayList<KhachHang>();
		
	}	
	
	public ArrayList<KhachHang> show() {
		Connection con = database.Con();
		try {
			String sql = "Select * from KhachHang";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		   String tenKH,cmnd,diaChi,ngaySinh,email;Date ngay;
		int maKH;int SDT;
		    while (rs.next()) {
		    	
		   	 			 maKH=rs.getInt(1);
						 tenKH=rs.getString(2);
						 cmnd=rs.getString(3);
						 SDT=rs.getInt(4);
						 diaChi=rs.getString(5);
						 ngay=rs.getDate(6);
						 email=rs.getString(7);
				
				KhachHang kh = new KhachHang(tenKH, diaChi, ngay, email, cmnd, maKH, SDT);
				dsKH.add(kh);
			}	
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		return dsKH;
	}
	
	public void SuaDSKH(String tenKH,int maKH,  String diaChi, Date ngaySinh, String email, String cmnd, int sDT) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
		try {
			
			 stmt = con.prepareStatement("update KhachHang "
						+ "set tenKH = ?,"
						+ "CMND = ?,"
						+ "SDT = ? 	,"
						+ "diaChi = ? ,"
						+ "ngaySinh = ? ,"				
						+ "email = ? "
						+ "where maKH = ?");
			
			stmt.setString(1, tenKH);
			stmt.setString(2, cmnd);
			stmt.setInt(3, sDT);
			stmt.setString(4,diaChi );
			stmt.setDate(5,ngaySinh);
			stmt.setString(6, email );
			stmt.setInt(7, maKH);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
	
	public void SaveDSKH( int maKH,String tenKH, String cmnd,  int sDT, Date ngaySinh,String diaChi, String email) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into KhachHang values(? ,? ,?, ? ,? ,?, ?)");
			stmt.setInt(1,maKH);
			stmt.setString(2,tenKH);
			stmt.setString(3, cmnd);
			stmt.setInt(4,sDT);
			stmt.setString(5, diaChi);
			stmt.setDate(6, ngaySinh);
			stmt.setString(7, email);
			stmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	
	}
	
	
	
	
	
	public void delete(int maKH) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from KhachHang where maKH = ?");
			stmt.setInt(1, maKH);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void TimKiemTen(String tenKH,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from KhachHang Where tenKH Like N'%"+tenKH+"%'";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()) {
		    	model.addRow(new Object[] {
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						
						rs.getString(5),
						rs.getString(6),
						rs.getString(7)
						
				});
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	public void TimKiemsdt(int sdt,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from KhachHang Where SDT Like'%"+sdt+"%'";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()) {
		    	model.addRow(new Object[] {
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						
						rs.getString(5),
						rs.getString(6),
						rs.getString(7)
						
				});
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	

}

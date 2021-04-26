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
import entity.NhanVien;


public class DanhSachNhanVien {
	ArrayList<NhanVien> dsNV;
	NhanVien nv;

	public DanhSachNhanVien(){
		dsNV= new ArrayList<NhanVien>();	
		
	}	
	
	public ArrayList<NhanVien> show() {
		Connection con = database.Con();
		try {
			String sql = "Select * from NhanVien";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		   String tenNV,cmnd,diaChi,email;
		   Date ngaySinh;
		int maNV;int SDT;
		    while (rs.next()) {
		   	 			 maNV=rs.getInt(1);
						 tenNV=rs.getString(2);
						 cmnd=rs.getString(3);
						 SDT=rs.getInt(4);
						 diaChi=rs.getString(5);
						 ngaySinh=rs.getDate(6);
						 email=rs.getString(7);
				
				NhanVien nv = new NhanVien(tenNV, maNV, diaChi, ngaySinh, email, cmnd,SDT );
				dsNV.add(nv);
			}	
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		return dsNV;
	}
	public void SuaDSNV(String tenNV, int maNV, String diaChi, Date ngaySinh, String email, String cmnd, int sDT) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
		try {
			
			 stmt = con.prepareStatement("update NhanVien "
						+ "set tenNV = ?,"
						+ "CMND = ?,"
						+ "SDT = ? ,"
						+ "diaChi = ? ,"
						+ "ngaySinh = ? ,"
						+ "email = ? "
						+ "where maNV = ?");
			
			stmt.setString(1, tenNV);
			stmt.setString(2, cmnd);
			stmt.setInt(3, sDT);
			stmt.setString(4,diaChi );
			stmt.setDate(5,ngaySinh);
			stmt.setString(6, email );
			stmt.setInt(7, maNV);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
	
	public void SaveDSNV(String tenNV, int maNV, String diaChi, Date date, String email, String cmnd, int sDT) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into NhanVien values(? ,? ,?, ? ,? ,?, ?)");
			stmt.setInt(1,maNV);
			stmt.setString(2,tenNV);
			stmt.setString(3, cmnd);
			stmt.setInt(4,sDT);
			stmt.setString(5, diaChi);
			stmt.setDate(6, date);
			
			stmt.setString(7, email);
			stmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	
	}
	public void TimKiemTen(String tenKH,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from NhanVien Where tenNV Like N'%"+tenKH+"%'";
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
			String sql = "Select * from NhanVien Where SDT Like '%"+sdt+"%'";
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
	
	
	public void delete(int maNV) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from NhanVien where maNV = ?");
			stmt.setInt(1, maNV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
//	public void TimKiemTen(String tenTuor,DefaultTableModel model) {
//		Connection con = database.Con();
//		
//		try {
//			String sql = "Select * from Tuor Where tenTuor Like'%"+tenTuor+"%'";
//			Statement statement = con.createStatement();
//		    ResultSet rs = statement.executeQuery(sql);
//		    
//		    while (rs.next()) {
//		    	model.addRow(new Object[] {
//						rs.getInt(1),
//						rs.getString(2),
//						rs.getInt(3),
//						rs.getString(4),
//						rs.getString(5),
//						rs.getString(6),
//						rs.getString(7)
//						
//				});
//			}
//		} catch (SQLException e) {
//			
//			System.out.println("error"+e);
//		}
//	}	

}

package control;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import database.database;
import entity.Tuor;


public class DanhSachTuor {
	ArrayList<Tuor> dsTuor;
	Tuor Tuor;
	public DanhSachTuor(){
		dsTuor= new ArrayList<Tuor>();	
		
	}	
	
	public ArrayList<Tuor> show() {
		Connection con = database.Con();
		try {
			String sql = "Select * from Tour";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    int a,soChoNhan,maKS;String ten,tuyenDuong,gio;float donGia;Date ngayDi,ngayDen;
		    while (rs.next()) {
						 a =rs.getInt(1);
						 ten=rs.getString(2);
						 soChoNhan=rs.getInt(3);
						 ngayDen=rs.getDate(4);
						 ngayDi=rs.getDate(5);
						 gio=rs.getString(6);
						 tuyenDuong=rs.getString(7);
						 donGia=rs.getFloat(8);
						 maKS = rs.getInt(9);
				
				Tuor s = new Tuor(a, ten,soChoNhan, ngayDen, ngayDi, gio,tuyenDuong, donGia,maKS);
				dsTuor.add(s);
				
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
		return dsTuor;
	}
	public void SuaTuorne(int ma,String ten,int SochoNhan,Date ngayDen,Date ngayDi,String tgkh,String tuyenDuong,float gia,int maKS) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
		try {
			
			 stmt = con.prepareStatement("update Tour "
						+ "set tenTour = ?,"
						+ "soLuong = ?,"
						+ "ngayKH = ? ,"
						+ "ngayKT = ? ,"
						+ "ThoiGianKhoiHanh = ? ,"
						+ "diemKH = ? ,"
						+ "donGia = ? ,"
						+ "maKS = ? "
						+ "where maTour = ?");
			
			stmt.setString(1,ten);
			stmt.setInt(2, SochoNhan);
			stmt.setDate(3,ngayDen);
			stmt.setDate(4,ngayDi);
			stmt.setString(5,tgkh);
			stmt.setString(6, tuyenDuong);
			stmt.setFloat(7, gia);
			stmt.setInt(8, maKS);
			stmt.setInt(9, ma);
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
	public void SaveTuor(int ma ,String ten,int SochoCon,Date date1,Date date2,String tgkh,String tuyenDuong,float gia,int maKS) {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into Tour values(? ,? ,?, ? ,? ,? ,? ,? ,?)");
			stmt.setInt(1,ma);
			stmt.setString(2,ten);
			stmt.setInt(3,SochoCon);
			stmt.setDate(4,date1);
			stmt.setDate(5, date2);
			stmt.setString(6, tgkh);
			stmt.setString(7, tuyenDuong);
			stmt.setFloat(8, gia);
			stmt.setInt(9, maKS);
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
			stmt = con.prepareStatement("delete from Tour where maTour = ?");
			stmt.setInt(1, ma);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public void TimKiemTen(String tenTuor,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from Tour Where tenTour Like N'%"+tenTuor+"%'";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while (rs.next()) {
		    	model.addRow(new Object[] {
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						
				});
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	public void TimKiem(String tenTuor,Date date,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from Tour Where (diemKH Like N'%"+tenTuor+"%')And (ngayKH Like '"+date+"') ";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		   
		    while (rs.next()) {
		    	model.addRow(new Object[] {
		    			rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						
				});
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	public void TimKiemDiemden(String tenTuor,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from Tour Where diemKH Like N'%"+tenTuor+"%'";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    int a;String ngayDen,ten,ngayDi,tuyenDuong;float donGia;
		    while (rs.next()) {
		    	model.addRow(new Object[] {
		    			rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						
				});
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	public void TimKiemdaydu(String tenTuor,Date date,String Kh,DefaultTableModel model) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from Tour Where (diemKH Like'"+Kh+"%')And (ngayKH Like '%"+date+"')And(tenTour Like N'%"+tenTuor+"%') ";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    int a;String ngayDen,ten,ngayDi,tuyenDuong;float donGia;
		    while (rs.next()) {
		    	model.addRow(new Object[] {
		    			rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9),
						
				});
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	
	
	
	
}

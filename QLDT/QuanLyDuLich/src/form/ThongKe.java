package form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDayChooser;

import database.database;
import entity.Tuor;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ThongKe extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtnam;
	DefaultTableModel model;
	private JButton btnThongKe;
	private JLabel lblBangThongKe;
	private JComboBox cbThang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe frame = new ThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
private void showdatabaseAll() {
		
		model = new DefaultTableModel();
		

		model.addColumn("Mã Hóa Đơn");
		model.addColumn("Ngày Lập hóa đơn");
		model.addColumn("Mã Khách Hàng");
		model.addColumn("Tên Khách Hàng");
		model.addColumn("Mã Tour");
		model.addColumn("Tên Tour");
		
		Connection con = database.Con();
		try {
			
					String sql = "select HD.maHD,HD.ngayLapHD,HD.maKH,KH.tenKH,CT.maTour,Tou.tenTour  from HoaDon as HD INNER JOIN ChiTietHoaDon as CT ON HD.maHD=CT.maHD INNER JOIN KhachHang as KH ON HD.maKH = KH.maKH "
							+ "INNER JOIN Tour as Tou ON CT.maTour=Tou.maTour";
					Statement statement = con.createStatement();
				    ResultSet rs = statement.executeQuery(sql);
				   
				    while (rs.next()) {
				    	model.addRow(new Object[] {
				    			rs.getString(1),
				    			rs.getString(2),
				    			rs.getString(3),
				    			rs.getString(4),
				    			rs.getString(5),
				    			rs.getString(6),
				    			
				    			
				    	});
				    	
				    }
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
		    table.getColumnModel().getColumn(1).setPreferredWidth(153);
		    table.getColumnModel().getColumn(2).setPreferredWidth(70);
		    table.getColumnModel().getColumn(3).setPreferredWidth(153);
		    table.getColumnModel().getColumn(4).setPreferredWidth(153);
		    table.getColumnModel().getColumn(5).setPreferredWidth(153);
		    
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}

	private void showdatabase() {
		
		model = new DefaultTableModel();
		model.addColumn("Tháng");

		model.addColumn("Số Lượng Đặt Tour");
		model.addColumn("Tổng doanh thu");
		
		Connection con = database.Con();
		try {
			
					String sql = "select MONTH(HD.ngayLapHD),COUNT(HD.maHD),sum((CT.soLuongNguoiLon*tou.donGia)+(CT.soLuongTreEm*tou.donGia*75/100)) as sumprice from "
							+ "HoaDon as HD INNER JOIN ChiTietHoaDon as CT  ON HD.maHD=CT.maHD "
							+ "INNER JOIN Tour as Tou ON CT.maTour = Tou.maTour where (Year(HD.ngayLapHD)= "+txtnam.getText()+")group by MONTH(HD.ngayLapHD)";
					Statement statement = con.createStatement();
				    ResultSet rs = statement.executeQuery(sql);
				   
				    while (rs.next()) {
				    	model.addRow(new Object[] {
				    			rs.getString(1),
				    			rs.getString(2),
				    			rs.getString(3),
				    			
				    			
				    	});
				    	
				    }
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
		    table.getColumnModel().getColumn(1).setPreferredWidth(153);
		    table.getColumnModel().getColumn(2).setPreferredWidth(70);
		    
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
private void showdatabaseThang() {
		
		model = new DefaultTableModel();
		model.addColumn("Tháng");

		model.addColumn("Số Lượng Đặt Tour");
		model.addColumn("Tổng doanh thu");
		
		Connection con = database.Con();
		try {
			
					String sql = "select MONTH(HD.ngayLapHD),COUNT(HD.maHD),sum((CT.soLuongNguoiLon*tou.donGia)+(CT.soLuongTreEm*tou.donGia*75/100)) as sumprice from "
							+ "HoaDon as HD INNER JOIN ChiTietHoaDon as CT  ON HD.maHD=CT.maHD "
							+ "INNER JOIN Tour as Tou ON CT.maTour = Tou.maTour where (Year(HD.ngayLapHD)= "+txtnam.getText()+") And (MONTH(HD.ngayLapHD)="+cbThang.getSelectedItem()+")group by MONTH(HD.ngayLapHD)";
					Statement statement = con.createStatement();
				    ResultSet rs = statement.executeQuery(sql);
				   
				    while (rs.next()) {
				    	model.addRow(new Object[] {
				    			rs.getString(1),
				    			rs.getString(2),
				    			rs.getString(3),
				    			
				    			
				    	});
				    	
				    }
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
		    table.getColumnModel().getColumn(1).setPreferredWidth(153);
		    table.getColumnModel().getColumn(2).setPreferredWidth(70);
		    
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
private void showTopKhachHang() {
	
	model = new DefaultTableModel();
	model.addColumn("Tên khách hàng");
	model.addColumn("Số Lượng Tour đã đặt");

	
	Connection con = database.Con();
	try {
		
				String sql =" select Kh.TenKH,Count(Hd.maKH) as counted from HoaDon as Hd "
						+ "INNER JOIN KhachHang as Kh ON Hd.maKH = Kh.maKH group by Kh.tenKH having count(HD.maKH)"
						+ " =(select Max(counted)as Maxdo from(select Kh.TenKH,Count(Hd.maKH) as counted from HoaDon as Hd "
						+ "INNER JOIN KhachHang as Kh ON Hd.maKH = Kh.maKH group by Kh.tenKH)as t)";
				Statement statement = con.createStatement();
			    ResultSet rs = statement.executeQuery(sql);
			   
			    while (rs.next()) {
			    	model.addRow(new Object[] {
			    			rs.getString(1),
			    			rs.getString(2),
			    			
			    			
			    			
			    	});
			    	
			    }
	    table.setModel(model);
	    
	    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
	    table.getColumnModel().getColumn(1).setPreferredWidth(153);
	    table.getColumnModel().getColumn(2).setPreferredWidth(70);
	    
	    
	} catch (Exception e) {
		System.out.println("error"+e);
	}
	
}
private void DoanhThuTour() {

	model = new DefaultTableModel();
	
	model.addColumn("Tên Tour");
	model.addColumn("Số Lượng Đặt Tour");
	model.addColumn("Tổng Doanh thu");

	Connection con = database.Con();
	try {

		String sql = "select t.TenTour,COUNT(CT.maHD),sum((CT.soLuongNguoiLon*t.donGia)+(CT.soLuongTreEm*t.donGia*75/100)) as sumprice from ChiTietHoaDon as CT "
				+ "INNER JOIN HoaDon as HD ON HD.maHD =CT.maHD "
				+"INNER JOIN Tour as t ON t.maTour = CT.maTour  where (Year(HD.ngayLapHD)=" + txtnam.getText() 
				+ ")And (MONTH(HD.ngayLapHD)=" + cbThang.getSelectedItem() + ") group by t.TenTour";
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		while (rs.next()) {
			model.addRow(new Object[] {
					rs.getString(1), 
					rs.getString(2), 
					rs.getString(3),
				
			});

		}
		table.setModel(model);

		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		;
		table.getColumnModel().getColumn(1).setPreferredWidth(153);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);

	} catch (Exception e) {
		System.out.println("error" + e);
	}

}
private void showdatabaseNN() {
		
		model = new DefaultTableModel();
		model.addColumn("Mã Tuor");
		model.addColumn("Tên Tuor");
		model.addColumn("Số Lượng");
		model.addColumn("Ngày khởi hành ");
		model.addColumn("Ngày kết thúc");
		model.addColumn("Địa Điểm Khởi hành");
		model.addColumn("Thời Gian Khởi Hành");
		model.addColumn("Giá");
		model.addColumn("maKS");
		Connection con = database.Con();
		try {
			
					String sql = "select Ct.maTour   from(select maTour,count(*) as myTour From ChiTietHoaDon GROUP BY maHD) "
							+ "ChiTietHoaDon as Ct INNER JOIN Tour as Tou ON Ct.maTour = Tou.maTour group by Ct.maTour And Ct.maTour having count (Ct.maTour )";
					Statement statement = con.createStatement();
				    ResultSet rs = statement.executeQuery(sql);
				   
				    while (rs.next()) {
				    	model.addRow(new Object[] {
				    			rs.getString(1),
				    			rs.getString(2),
				    			
				    	});
				    	
				    }
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
		    table.getColumnModel().getColumn(1).setPreferredWidth(153);
		    table.getColumnModel().getColumn(2).setPreferredWidth(70);
		    table.getColumnModel().getColumn(3).setPreferredWidth(120);
		    table.getColumnModel().getColumn(4).setPreferredWidth(120);
		    table.getColumnModel().getColumn(5).setPreferredWidth(120);
		    table.getColumnModel().getColumn(6).setPreferredWidth(120);
		    table.getColumnModel().getColumn(7).setPreferredWidth(108);
		    table.getColumnModel().getColumn(8).setPreferredWidth(70);
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}

	/**
	 * Create the frame.
	 */
	public ThongKe() {
		setTitle("Thống kê");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				 showdatabaseAll();
			}
		});
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0,screen.width,screen.height - 30);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		setJMenuBar(menuBar);

		JMenu mnKhachHang = new JMenu("Khách hàng");
		mnKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnKhachHang);

		JMenuItem mntmTimKiemtour = new JMenuItem("Tìm kiếm tour");
		mntmTimKiemtour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmTimKiemtour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TimKiemTuor tim = new TimKiemTuor();
				tim.setVisible(true);
				setVisible(false);
			}
		});
		mnKhachHang.add(mntmTimKiemtour);

		JMenuItem mntmtDatTour = new JMenuItem("Đặt tour");
		mntmtDatTour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmtDatTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatTuorForm a = new DatTuorForm(" ");
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnKhachHang.add(mntmtDatTour);

		JMenuItem mntmNewMenuItem = new JMenuItem("Hủy tour");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HuyTuorForm("").setVisible(true);
				setVisible(false);
			}
		});
		mnKhachHang.add(mntmNewMenuItem);
		
		JMenu mnNVDV = new JMenu("Nhân viên dịch vụ");
		mnNVDV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNVDV);
		
		JMenuItem mntmQLTour = new JMenuItem("Quản lý Tour");
		mntmQLTour.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmQLTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tuorForm a = new tuorForm();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNVDV.add(mntmQLTour);
		
		JMenuItem mntmQLKH = new JMenuItem("Quản lý khách hàng");
		mntmQLKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmQLKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLKhachHang_Frm a = new QLKhachHang_Frm();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNVDV.add(mntmQLKH);

		JMenuItem mntmThanhToan = new JMenuItem("Thanh toán");
		mntmThanhToan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThanhToan a = new ThanhToan();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNVDV.add(mntmThanhToan);
		
		JMenuItem mntmTimkiemKH = new JMenuItem("Tìm khách hàng");
		mntmTimkiemKH.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmTimkiemKH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimKiemKH a = new TimKiemKH();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNVDV.add(mntmTimkiemKH);

		JMenu mnNhnVinQun = new JMenu("Nhân viên quản lý");
		mnNhnVinQun.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNhnVinQun);

		JMenuItem mntmQLNV = new JMenuItem("Quản lý nhân viên");
		mntmQLNV.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmQLNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLNhanVien_Frm a = new QLNhanVien_Frm();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNhnVinQun.add(mntmQLNV);

		JMenuItem mntmTimNhanVien = new JMenuItem("Tìm nhân viên");
		mntmTimNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmTimNhanVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimKiemNV a = new TimKiemNV();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNhnVinQun.add(mntmTimNhanVien);

		JMenuItem mntmThongKe = new JMenuItem("Thống kê");
		mntmThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKe a = new ThongKe();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnNhnVinQun.add(mntmThongKe);

		JMenu mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnHeThong);

		JMenuItem mntmDangNhap = new JMenuItem("Đăng nhập");
		mntmDangNhap.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhapForm a = new DangNhapForm();
				a.setVisible(true);
				setVisible(false);
			}
		});
		mnHeThong.add(mntmDangNhap);

		JMenuItem mntmnDangKy = new JMenuItem("Đăng ký");
		mntmnDangKy.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnHeThong.add(mntmnDangKy);

		JMenuItem mntmnDangXuat = new JMenuItem("Đăng xuất");
		mntmnDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnHeThong.add(mntmnDangXuat);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 206, 1407, 495);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblThang = new JLabel("Tháng:");
		lblThang.setBounds(35, 104, 53, 39);
		lblThang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblThang);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("Thống Kê Theo Doanh Thu Theo Năm")) {
					JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Năm");
				}
				if (comboBox.getSelectedItem().equals("Thống Kê Theo Doanh Thu Theo Tháng")) {
					JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Tháng và năm");
				}
				if(comboBox.getSelectedItem().equals("Thống Kê Doanh Thu Theo Từng Tour")) {
					JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Tháng và năm");
				}
				
			}
		});
		comboBox.setBounds(834, 102, 339, 42);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Thống Kê Theo Doanh Thu Theo Năm", "Thống Kê Theo Doanh Thu Theo Tháng", "Thống Kê khác hàng đặt tour nhiều nhất", "Thống Kê Doanh Thu Theo Từng Tour"}));
		contentPane.add(comboBox);
		
		txtnam = new JTextField();
		txtnam.setBounds(433, 102, 320, 42);
		txtnam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(txtnam);
		txtnam.setColumns(10);
		
		JLabel lblNam = new JLabel("Năm:");
		lblNam.setBounds(336, 104, 87, 38);
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblNam);
		
		btnThongKe = new JButton("Thống Kê");
		btnThongKe.setBounds(1284, 102, 199, 42);
		btnThongKe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("Thống Kê Theo Doanh Thu Theo Năm")) {
					
				 showdatabase();	
				}
				if (comboBox.getSelectedItem().equals("Thống Kê Theo Doanh Thu Theo Tháng")) {
					showdatabaseThang();
				}
				if(comboBox.getSelectedItem().equals("Thống Kê khác hàng đặt tour nhiều nhất")) {
					showTopKhachHang();
				}
				if(comboBox.getSelectedItem().equals("Thống Kê Doanh Thu Theo Từng Tour")) {
					DoanhThuTour();
				}
			}
		});
		contentPane.add(btnThongKe);
		
		lblBangThongKe = new JLabel("Bảng Thống Kê");
		lblBangThongKe.setBounds(79, 168, 176, 28);
		lblBangThongKe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblBangThongKe);
		
		JButton btnDong = new JButton("Đóng");
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		btnDong.setForeground(Color.BLACK);
		btnDong.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDong.setBounds(733, 724, 113, 35);
		contentPane.add(btnDong);
		
		JLabel lblThongKe = new JLabel("Thống Kê");
		lblThongKe.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblThongKe.setForeground(Color.BLUE);
		lblThongKe.setBounds(709, 10, 200, 50);
		contentPane.add(lblThongKe);
		
		cbThang = new JComboBox();
		cbThang.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbThang.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cbThang.setBounds(98, 104, 200, 39);
		contentPane.add(cbThang);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}

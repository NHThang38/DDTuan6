package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JDateChooser;

import control.DanhSachTuor;
import database.database;
import entity.Tuor;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class TimKiemTuor extends JFrame {

	private JPanel contentPane;
	private JTextField txtTen;
	private JTable table;
	private DefaultTableModel model;
	private DanhSachTuor ds = new DanhSachTuor();
	private JTextField txtKH;
	String maTuor,makS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemTuor frame = new TimKiemTuor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void showdatabase() {
		model = new DefaultTableModel();
		model.addColumn("Mã Tour");
		model.addColumn("Tên Tour");
		model.addColumn("Số Lượng");
		model.addColumn("Ngày khởi hành ");
		model.addColumn("Ngày kết thúc");
		model.addColumn("Thời Gian Khởi Hành");
		model.addColumn("Địa Điểm khởi Hành");
		model.addColumn("Giá");
		model.addColumn("Mã Khách sạn");
		try {
			List<Tuor> list = ds.show();
			for (Tuor tuor : list) {
				model.addRow(new Object[] {tuor.getMaTuor(),tuor.getTenTuor(),tuor.getSochoNhan(),tuor.getNgayDi(),tuor.getNgayKT(),tuor.getGio(),tuor.getTuyenDuong(),tuor.getGia(),tuor.getMaKS()});
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
	

	
	public TimKiemTuor() {
		setTitle("Tìm kiếm tour");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				showdatabase();
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
		
		JLabel txt = new JLabel("Tên Tour:");
		txt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt.setBounds(346, 86, 106, 40);
		
		txtTen = new JTextField();
		txtTen.setBounds(476, 86, 661, 34);
		txtTen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					if (txtTen.getText().equals("*")) {
						model.setRowCount(0);
						showdatabase();
					}else {
						model.setRowCount(0);
						ds.TimKiemTen(txtTen.getText(),model);
					    table.setModel(model);
					}
					
				    
				 
				
			}  
		});
		
		txtTen.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày Đi:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(112, 168, 130, 20);
		
		JLabel lblNewLabel_2 = new JLabel("Tìm Kiếm Tour");
		lblNewLabel_2.setBounds(515, 24, 244, 34);
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 29));
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(297, 154, 349, 40);
		
		JLabel lblNewLabel = new JLabel("Địa Điểm Khởi hành:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(669, 168, 149, 18);
		
		txtKH = new JTextField();
		txtKH.setBounds(851, 154, 400, 40);
		txtKH.setColumns(10);
		
		JButton btnTK = new JButton("Tìm Kiếm");
		btnTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTK.setBounds(1272, 163, 123, 31);
		btnTK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				if (txtTen.getText().trim().length()<0&&txtKH.getText().trim().length()<0&&dateChooser!=null) {
//					SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//					String date1 = df.format(dateChooser.getDate());
//					ds.TimKiemdaydu(txtTen.getText(), date1, txtKH.getText(), model);
//				}
//				if (txtKH.getText().trim().length()>0) {
//					ds.TimKiemDiemden(txtKH.getText(), model);
//					if (txtTen.getText().trim().length()>0) {
//						
//					}
//				}
//				if (txtTen.getText().trim().length()>0) {
//					ds.TimKiemTen(txtTen.getText(), model);
//				}
		
		
			
			if (txtTen.getText().trim().length()!=0) {
				model.setRowCount(0);
				ds.TimKiemTen(txtTen.getText(), model);
				
				if (txtKH.getText().trim().length()!=0 && dateChooser!=null) {
					model.setRowCount(0);
					Date date1 = new Date(dateChooser.getDate().getTime());
				
					ds.TimKiemdaydu(txtTen.getText(), date1, txtKH.getText(), model);
				}
				

			}else {
		
			String s =((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
				if (s.equals("")) {
					model.setRowCount(0);
					ds.TimKiemDiemden(txtKH.getText(), model);
				}else {
					model.setRowCount(0);
					Date date1 = new Date(dateChooser.getDate().getTime());
					ds.TimKiem(txtKH.getText(), date1, model);
				}
				
			}
			}
				
				
//				
			
				
			
				
		//}		
			
		});
		
		JButton btnNewButton = new JButton("Đóng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(1172, 684, 207, 46);
		contentPane.setLayout(null);
		contentPane.add(txt);
		contentPane.add(lblNewLabel_1);
		contentPane.add(dateChooser);
		contentPane.add(lblNewLabel);
		contentPane.add(txtKH);
		contentPane.add(btnTK);
		contentPane.add(txtTen);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(48, 241, 1347, 380);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				maTuor=(table.getValueAt(row, 0).toString());
				makS =(table.getValueAt(row, 8).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Đặt Tour");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DatTuorForm(maTuor).setVisible(true);
			}
		});
		btnNewButton_1.setBounds(148, 684, 231, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnLchTrnht = new JButton("Lịch trình Tour");
		btnLchTrnht.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLchTrnht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new formLichTrinh(maTuor,makS).setVisible(true);
			}
		});
		btnLchTrnht.setBounds(687, 684, 231, 46);
		contentPane.add(btnLchTrnht);
	}
}

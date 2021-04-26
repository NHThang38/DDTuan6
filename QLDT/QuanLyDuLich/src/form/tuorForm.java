package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;

import com.toedter.calendar.JDayChooser;

import control.DanhSachTuor;
import database.database;
import entity.Tuor;
import groovy.swing.factory.VBoxFactory;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class tuorForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtma;
	private JTextField txtTen;
	private JTextField txtTuyenDuong;
	private JTextField txtGia;
	private  JTable table;
	private JDateChooser dateChooserdi,dateChooserKetThuc;
	private DefaultTableModel model;
	private DanhSachTuor dstuor = new DanhSachTuor();
	private JTextField txtsotuor;
	private JTextField txtThoiGian;
	JComboBox cbmaKS;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tuorForm frame = new tuorForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void showjcombobox() {
		Connection con = database.Con();
		try {
			String sql = "select * from KhachSan";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				cbmaKS.addItem(rs.getString(1));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	private void SuaTuor() {
		
		Date date1 = new Date(dateChooserdi.getDate().getTime());
		Date date2 = new Date(dateChooserKetThuc.getDate().getTime());
		dstuor.SuaTuorne(Integer.parseInt(txtma.getText()), txtTen.getText(),Integer.parseInt(txtsotuor.getText()), date1, date2,txtThoiGian.getText(), txtTuyenDuong.getText(),Float.parseFloat(txtGia.getText()),Integer.parseInt(cbmaKS.getSelectedItem().toString()));
		
	}
	
	public void random() {
		Random ran = new Random();
		int n = ran.nextInt(1000000)+1;
		String val = String.valueOf(n);
		txtma.setText(val);
	}
	
	private void showdatabase() {
		
		model = new DefaultTableModel();
		model.addColumn("Mã Tuor");
		model.addColumn("Tên Tuor");
		model.addColumn("Số Lượng");
		model.addColumn("Ngày khởi hành ");
		model.addColumn("Ngày kết thúc");

		model.addColumn("Thời Gian Khởi Hành");
		model.addColumn("Địa Điểm Khởi hành");
		
		model.addColumn("Giá");
		model.addColumn("Mã Khách Sạn");
		try {
			List<Tuor> list = dstuor.show();
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
	
	
	/**
	 * Create the frame.
	 */
	public tuorForm() {
		setTitle("Quản lý tour");
		
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
		
		JLabel lblMaTour = new JLabel("Mã Tour:");
		lblMaTour.setBounds(36, 58, 73, 22);
		lblMaTour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtma = new JTextField();
		txtma.setBounds(181, 51, 407, 41);
		txtma.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtma.setEditable(false);
		txtma.setColumns(10);
		
		JLabel lblQLT = new JLabel("Quản Lý Tour");
		lblQLT.setBounds(598, 10, 263, 39);
		lblQLT.setForeground(Color.BLUE);
		lblQLT.setFont(new Font("Times New Roman", Font.PLAIN, 33));
		
		JLabel lblNgayKetThuc = new JLabel("Ng\u00E0y K\u1EBFt th\u00FAc");
		lblNgayKetThuc.setBounds(888, 81, 140, 32);
		lblNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtTen = new JTextField();
		txtTen.setBounds(181, 111, 407, 41);
		txtTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTen.setColumns(10);
		
		txtTuyenDuong = new JTextField();
		txtTuyenDuong.setBounds(1089, 140, 401, 39);
		txtTuyenDuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTuyenDuong.setColumns(10);
		
		txtGia = new JTextField();
		txtGia.setBounds(1089, 256, 401, 39);
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGia.setColumns(10);
		
		JLabel lblTenTour = new JLabel("Tên Tour:");
		lblTenTour.setBounds(36, 115, 98, 29);
		lblTenTour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNgayDi = new JLabel("Ng\u00E0y \u0111i:");
		lblNgayDi.setBounds(36, 167, 98, 29);
		lblNgayDi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblDiaDiem = new JLabel("Địa Điểm Khởi Hành:");
		lblDiaDiem.setBounds(888, 140, 157, 32);
		lblDiaDiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblGia = new JLabel("Gi\u00E1:");
		lblGia.setBounds(888, 254, 127, 32);
		lblGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		 dateChooserdi = new JDateChooser();
		 dateChooserdi.setBounds(177, 167, 412, 41);
		 dateChooserdi.setDateFormatString("dd-MM-yyyy");
		
		
		 dateChooserKetThuc = new JDateChooser();
		 dateChooserKetThuc.setBounds(1089, 81, 401, 39);
		 dateChooserKetThuc.setDateFormatString("dd-MM-yyyy");
		
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.setBounds(67, 678, 127, 50);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				random();
				Date date1 = new Date(dateChooserdi.getDate().getTime());
				Date date2 = new Date(dateChooserKetThuc.getDate().getTime());
				dstuor.SaveTuor(Integer.parseInt(txtma.getText()), txtTen.getText(),Integer.parseInt(txtsotuor.getText()), date1, date2,txtThoiGian.getText(), txtTuyenDuong.getText(),Float.parseFloat(txtGia.getText()),Integer.parseInt(cbmaKS.getSelectedItem().toString()));
				Object[] rowData = {Integer.parseInt(txtma.getText()), txtTen.getText(),Integer.parseInt(txtsotuor.getText()),date1,date2,txtThoiGian.getText(),
						txtTuyenDuong.getText(),Float.parseFloat(txtGia.getText()),Integer.parseInt(cbmaKS.getSelectedItem().toString())+""};
				model.addRow(rowData);
				
			}
		});
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.setBounds(296, 678, 140, 50);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int ma = (int) table.getValueAt(row, 0);
				dstuor.delete(ma);
				model.removeRow(row);
			}
		});
		
		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(555, 678, 140, 50);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date date1 = new Date(dateChooserdi.getDate().getTime());
				Date date2 = new Date(dateChooserKetThuc.getDate().getTime());
				SuaTuor();
				int row = table.getSelectedRow();
				table.setValueAt(txtTen.getText(), row, 1);
				table.setValueAt(txtsotuor.getText(), row, 2);
				table.setValueAt(date1, row, 3);
				table.setValueAt(date2, row,4);
				table.setValueAt(txtThoiGian.getText(), row, 5);
				table.setValueAt(txtTuyenDuong.getText(), row, 6);
				table.setValueAt(txtGia.getText(), row, 7);
				table.setValueAt(cbmaKS.getSelectedItem(), row, 8);
			}
		});
		
		JButton btnDong = new JButton("Tho\u00E1t");
		btnDong.setBounds(1318, 678, 140, 50);
		btnDong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		
		txtsotuor = new JTextField();
		txtsotuor.setBounds(177, 216, 412, 41);
		txtsotuor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtsotuor.setColumns(10);
		
		JLabel lblSoLuong = new JLabel("Số Lượng :");
		lblSoLuong.setBounds(36, 221, 106, 30);
		lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.setLayout(null);
		contentPane.add(btnThem);
		contentPane.add(btnXoa);
		contentPane.add(btnSua);
		contentPane.add(btnDong);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 387, 1467, 261);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int srow =table.getSelectedRow();
					
					int row = table.getSelectedRow();
					Date date1 =  (Date) table.getValueAt(row, 3);
					Date date2 =  (Date) table.getValueAt(row, 4);
					txtma.setText(table.getValueAt(row,0).toString());
					txtTen.setText(table.getValueAt(row, 1).toString());
					txtsotuor.setText(table.getValueAt(row, 2).toString());
					dateChooserdi.setDate(date1);
					dateChooserKetThuc.setDate(date2);
					txtThoiGian.setText(table.getValueAt(row, 5).toString());
					txtTuyenDuong.setText(table.getValueAt(row, 6).toString());
					txtGia.setText(table.getValueAt(row, 7).toString());
					cbmaKS.setSelectedItem(table.getValueAt(row, 7).toString());
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e);
				}
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 Tuor", "T\u00EAn tuor", "Ng\u00E0y Kh\u1EDFi H\u00E0nh", "Ng\u00E0y K\u1EBFt Th\u00FAc", "Tuy\u1EBFn \u0110\u01B0\u1EDDng", "Gi\u00E1"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Object.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		contentPane.add(lblMaTour);
		contentPane.add(lblTenTour);
		contentPane.add(lblNgayDi);
		contentPane.add(lblSoLuong);
		contentPane.add(txtsotuor);
		contentPane.add(dateChooserdi);
		contentPane.add(txtTen);
		contentPane.add(txtma);
		contentPane.add(lblNgayKetThuc);
		contentPane.add(lblDiaDiem);
		contentPane.add(lblGia);
		contentPane.add(txtGia);
		contentPane.add(dateChooserKetThuc);
		contentPane.add(txtTuyenDuong);
		contentPane.add(lblQLT);
		
		JLabel lblKhoiHanh = new JLabel("Thời Gian Khởi Hành:");
		lblKhoiHanh.setBounds(888, 194, 169, 32);
		lblKhoiHanh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblKhoiHanh);
		
		txtThoiGian = new JTextField();
		txtThoiGian.setBounds(1090, 190, 400, 39);
		txtThoiGian.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(txtThoiGian);
		txtThoiGian.setColumns(10);
		
		JButton btnLichTrinh = new JButton("Thêm lịch Trình");
		btnLichTrinh.setBounds(783, 678, 169, 50);
		btnLichTrinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLichTrinh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LichTrinhTour(txtma.getText()).setVisible(true);
			}
		});
		contentPane.add(btnLichTrinh);
		
		JLabel lblMaKS = new JLabel("Mã Khách Sạn:");
		lblMaKS.setBounds(29, 280, 127, 24);
		lblMaKS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblMaKS);
		
		cbmaKS = new JComboBox();
		cbmaKS.setBounds(177, 275, 414, 39);
		cbmaKS.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(cbmaKS);
		
		JButton btnThe = new JButton("Thêm khách sạn");
		btnThe.setBounds(1046, 678, 163, 50);
		btnThe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QLKhachSan().setVisible(true);
			}
		});
		btnThe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnThe);
		
		JLabel lblDanhSachTour = new JLabel("Danh sách tour");
		lblDanhSachTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDanhSachTour.setBounds(45, 364, 149, 13);
		contentPane.add(lblDanhSachTour);
		random();
		showjcombobox();
	}
}

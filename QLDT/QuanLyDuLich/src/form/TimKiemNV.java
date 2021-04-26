package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.DanhSachNhanVien;
import database.database;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimKiemNV extends JFrame {

	private JPanel contentPane;
	private JTextField txtTen;
	private JTextField txtSDT;
	private JTable table;
	private DefaultTableModel model;
	private DanhSachNhanVien dsnv = new DanhSachNhanVien();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemNV frame = new TimKiemNV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	

	private void showdatabase() {
		Connection con = database.Con();
		model = new DefaultTableModel();
		model.addColumn("Mã Nhân viên");
		model.addColumn("Tên nhân viên");
		model.addColumn("CMND");
		model.addColumn("SDT");
		model.addColumn("Ngày sinh");
		model.addColumn("Địa chỉ");
		model.addColumn("Email");
		try {
			List<NhanVien> list = dsnv.show();
			for (NhanVien nv : list) {
				model.addRow(new Object[] {nv.getMaNV(), nv.getTenNV(), nv.getCmnd(),nv.getSDT(), nv.getNgaySinh(), nv.getDiaChi(), nv.getEmail()});
			}
			con.close();
		    table.setModel(model);
		  
		    table.getColumnModel().getColumn(0).setPreferredWidth(78);
		    table.getColumnModel().getColumn(1).setPreferredWidth(150);
		    table.getColumnModel().getColumn(2).setPreferredWidth(100);
		    table.getColumnModel().getColumn(3).setPreferredWidth(100);
		    table.getColumnModel().getColumn(4).setPreferredWidth(100);
		    table.getColumnModel().getColumn(5).setPreferredWidth(100);
		    table.getColumnModel().getColumn(6).setPreferredWidth(134);
		    
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	}
	/**
	 * Create the frame.
	 */
	public TimKiemNV() {
		setTitle("Tìm kiếm nhân viên");
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
		contentPane.setLayout(null);
		
		JLabel lblTim = new JLabel("Tìm kiếm nhân viên");
		lblTim.setBounds(322, 10, 766, 62);
		lblTim.setHorizontalAlignment(SwingConstants.CENTER);
		lblTim.setForeground(Color.BLUE);
		lblTim.setFont(new Font("Times New Roman", Font.PLAIN, 31));
		contentPane.add(lblTim);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên nhân viên:");
		lblNewLabel_1_1.setBounds(68, 96, 127, 35);
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1_1);
		
		txtTen = new JTextField();
		txtTen.setBounds(300, 96, 640, 35);
		txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTen.setColumns(10);
		txtTen.setBackground(Color.WHITE);
		contentPane.add(txtTen);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("SDT Nhân Viên");
		lblNewLabel_1_1_1.setBounds(68, 141, 127, 35);
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblNewLabel_1_1_1);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(300, 141, 640, 35);
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBackground(Color.WHITE);
		contentPane.add(txtSDT);
		
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBounds(1041, 141, 113, 35);
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				if (txtSDT.getText().trim().length()==0) {
					dsnv.TimKiemTen(txtTen.getText(), model);
				}else {
					dsnv.TimKiemsdt(Integer.parseInt(txtSDT.getText()), model);
				}
			}
		});
		btnTimKiem.setForeground(Color.BLACK);
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(btnTimKiem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(88, 238, 1404, 453);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
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
		btnDong.setBounds(754, 724, 113, 35);
		contentPane.add(btnDong);
	}
}

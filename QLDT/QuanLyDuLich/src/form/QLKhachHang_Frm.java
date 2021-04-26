package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import control.DanhSachKhachHang;
import database.database;
import entity.KhachHang;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class QLKhachHang_Frm extends JFrame {

	private JPanel contentPane;
	private JTextField txtMaKH;
	private JTextField txtTen;
	private JLabel lblCMND;
	private JTextField txtCMND;
	private JLabel lblSDT;
	private JTextField txtSDT;
	private JLabel lblDiaChi;
	private JTextField txtDiaChi;
	private JLabel lblNgaySinh;
	private JTextField txtEmail;
	private JTable table;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThoat;
	private DefaultTableModel model;
	DanhSachKhachHang ds = new DanhSachKhachHang();
	JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLKhachHang_Frm frame = new QLKhachHang_Frm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void random() {
		Random ran = new Random();
		int n = ran.nextInt(1000000)+1;
		String val = String.valueOf(n);
		txtMaKH.setText(val);
	}
	private void showdatabase() {
		Connection con = database.Con();
		model = new DefaultTableModel();
		model.addColumn("Mã Khách Hàng");
		model.addColumn("Tên Khách Hàng");
		model.addColumn("CMND");
		model.addColumn("SDT");
		model.addColumn("Địa Chỉ");
		model.addColumn("Ngày sinh");
		model.addColumn("Email");
		try {
			List<KhachHang> list = ds.show();
			for (KhachHang nv : list) {
				model.addRow(new Object[] {nv.getMaKH(), nv.getTenKH(), nv.getCmnd(),nv.getSDT(),nv.getDiaChi() ,nv.getNgaySinh(), nv.getEmail()});
			}
			con.close();
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(100);
		    table.getColumnModel().getColumn(1).setPreferredWidth(150);
		    table.getColumnModel().getColumn(2).setPreferredWidth(120);
		    table.getColumnModel().getColumn(3).setPreferredWidth(120);
		    table.getColumnModel().getColumn(4).setPreferredWidth(120);
		    table.getColumnModel().getColumn(5).setPreferredWidth(160);
		    table.getColumnModel().getColumn(6).setPreferredWidth(163);
		    
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	}
	private void SuaKH() {
		
		Date date= new Date(dateChooser.getDate().getTime());
		ds.SuaDSKH(txtTen.getText(), Integer.parseInt(txtMaKH.getText()), txtDiaChi.getText(), date, txtEmail.getText(), txtCMND.getText(), Integer.parseInt(txtSDT.getText()));
		
	}
	/**
	 * Create the frame.
	 */
	public QLKhachHang_Frm() {
		setTitle("Quản lý khách hàng");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
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
		
		JLabel lblQunLyKhach = new JLabel("Qu\u1EA3n L\u00FD Kh\u00E1ch H\u00E0ng");
		lblQunLyKhach.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLyKhach.setForeground(Color.BLUE);
		lblQunLyKhach.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblQunLyKhach.setBounds(333, 0, 869, 55);
		contentPane.add(lblQunLyKhach);
		
		JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
		lblMaKH.setForeground(Color.BLACK);
		lblMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaKH.setBounds(23, 100, 146, 35);
		contentPane.add(lblMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtMaKH.setColumns(10);
		txtMaKH.setBackground(Color.LIGHT_GRAY);
		txtMaKH.setBounds(179, 100, 411, 35);
		contentPane.add(txtMaKH);
		
		JLabel lblTenKH = new JLabel("T\u00EAn KH:");
		lblTenKH.setForeground(Color.BLACK);
		lblTenKH.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenKH.setBounds(23, 164, 127, 35);
		contentPane.add(lblTenKH);
		
		txtTen = new JTextField();
		txtTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTen.setColumns(10);
		txtTen.setBackground(Color.WHITE);
		txtTen.setBounds(179, 164, 411, 35);
		contentPane.add(txtTen);
		
		lblCMND = new JLabel("CMND:");
		lblCMND.setForeground(Color.BLACK);
		lblCMND.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCMND.setBounds(23, 221, 118, 35);
		contentPane.add(lblCMND);
		
		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCMND.setColumns(10);
		txtCMND.setBackground(Color.WHITE);
		txtCMND.setBounds(179, 221, 411, 35);
		contentPane.add(txtCMND);
		
		lblSDT = new JLabel("SDT:");
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSDT.setBounds(732, 100, 94, 35);
		contentPane.add(lblSDT);
		
		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBackground(Color.WHITE);
		txtSDT.setBounds(889, 100, 405, 35);
		contentPane.add(txtSDT);
		
		lblDiaChi = new JLabel("\u0110\u1ECBa Ch\u1EC9:");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDiaChi.setBounds(732, 152, 118, 35);
		contentPane.add(lblDiaChi);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtDiaChi.setColumns(10);
		txtDiaChi.setBackground(Color.WHITE);
		txtDiaChi.setBounds(889, 152, 405, 35);
		contentPane.add(txtDiaChi);
		
		lblNgaySinh = new JLabel("Ng\u00E0y Sinh:");
		lblNgaySinh.setForeground(Color.BLACK);
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgaySinh.setBounds(732, 207, 100, 35);
		contentPane.add(lblNgaySinh);
		
		 dateChooser = new JDateChooser();
		dateChooser.setBounds(889, 207, 405, 35);
		contentPane.add(dateChooser);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setBounds(732, 264, 127, 35);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(889, 264, 405, 35);
		contentPane.add(txtEmail);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(78, 362, 1354, 282);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {	
					int srow =table.getSelectedRow();
					
					int row = table.getSelectedRow();
					Date b =  (Date) table.getValueAt(row, 5);
					String a = new SimpleDateFormat("yyyy-MM-dd").format(b);
					txtMaKH.setText(table.getValueAt(row,0).toString());
					txtTen.setText(table.getValueAt(row, 1).toString());		
					txtCMND.setText(table.getValueAt(row, 2).toString());
					txtSDT.setText(table.getValueAt(row, 3).toString());
					
					dateChooser.setDate(b );
					txtDiaChi.setText(table.getValueAt(row, 4).toString());
					txtEmail.setText(table.getValueAt(row, 6).toString());
				} catch (Exception e1) {
					JOptionPane.showConfirmDialog(null, e1);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random();
				Date date = new Date(dateChooser.getDate().getTime());
				 ds.SaveDSKH(Integer.parseInt(txtMaKH.getText()),txtTen.getText(), txtCMND.getText(),Integer.parseInt(txtSDT.getText()),date,txtDiaChi.getText(), txtEmail.getText());
				 Object[] rowData = {Integer.parseInt(txtMaKH.getText()), txtTen.getText(),
							txtCMND.getText(),txtSDT.getText(),txtDiaChi.getText(),date,txtEmail.getText()+""};
					model.addRow(rowData);
			}
		});
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThem.setBounds(133, 675, 186, 48);
		contentPane.add(btnThem);
		
		btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date1 = new Date(dateChooser.getDate().getTime());
				SuaKH();
				int row = table.getSelectedRow();
				table.setValueAt(txtTen.getText(), row, 1);
				table.setValueAt(txtCMND.getText(), row, 2);
				table.setValueAt(txtSDT.getText(), row,3);
				table.setValueAt(date1, row, 5);
				table.setValueAt(txtDiaChi.getText(), row, 4);
				table.setValueAt(txtEmail.getText(), row, 6);
			}
		});
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSua.setBounds(491, 675, 186, 48);
		contentPane.add(btnSua);
		
		btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int ma = (int) table.getValueAt(row, 0);
				ds.delete(ma);
				model.removeRow(row);
			}
		});
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnXoa.setBounds(853, 679, 186, 48);
		contentPane.add(btnXoa);
		
		btnThoat = new JButton("\u0110\u00F3ng");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		btnThoat.setForeground(Color.BLACK);
		btnThoat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnThoat.setBounds(1191, 675, 186, 48);
		contentPane.add(btnThoat);
		
		JLabel lblDSKH = new JLabel("Danh sách khách hàng");
		lblDSKH.setForeground(Color.BLACK);
		lblDSKH.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDSKH.setBackground(Color.MAGENTA);
		lblDSKH.setBounds(78, 317, 157, 35);
		contentPane.add(lblDSKH);
		random();
	}
}

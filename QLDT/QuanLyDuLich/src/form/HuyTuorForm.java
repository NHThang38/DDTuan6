package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.DanhSachDatTuor;
import database.database;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;

public class HuyTuorForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenKH;
	private JTextField txtSDt;
	private JTable table;
	private DefaultTableModel model;
	static String huytuor;
	DanhSachDatTuor ds = new DanhSachDatTuor();
	private JTextField txtMaKH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HuyTuorForm frame = new HuyTuorForm(huytuor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void settextMaKH() {
		Connection con = database.Con();
		try {
			String sql = "select * from KhachHang Where SDT Like '"
					+ txtSDt.getText() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtMaKH.setText(rs.getString(1));
				txtTenKH.setText(rs.getString(2));
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}
	private void indanhsach() {
//		maDat,maTuor,tenTuor,maKH,tenKH,sDT,soLuong,thanhTien
		model = new DefaultTableModel();
		model.addColumn("Mã Đặt Tour");
		model.addColumn("Mã Tour");
		model.addColumn("Tên Tour");
		model.addColumn("Số Lượng Người Lớn");
		model.addColumn("Số Lượng Trẻ Em");
		
		Connection con = database.Con();
		try {
			String sql = "select Dt.maDT,Dt.maTour,Tu.tenTour,Dt.soLuongNguoiLon,Dt.soLuongTreEm from DatTour as Dt INNER JOIN Tour as Tu ON Dt.maTour=Tu.maTour Where Dt.maKH Like '" + txtMaKH.getText() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {

				model.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)
						});
			}
			;
		} catch (Exception e) {
			System.out.println("error" + e);
		}

		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		
	}
//	private void indanhsacha() {
////		maDat,maTuor,tenTuor,maKH,tenKH,sDT,soLuong,thanhTien
//		model = new DefaultTableModel();
//		model.addColumn("Mã Đặt");
//		model.addColumn("Tên Tuor");
//		model.addColumn("Mã Khách Hàng");
//		model.addColumn("Tên Khách Hàng");
//		model.addColumn("Số Điện Thoại");
//		model.addColumn("Số Lượng");
//		model.addColumn("Thành Tiền");
//		model.addColumn("Thành Tiền");
//		Connection con = database.Con();
//		try {
//			String sql = "select Dt.maTuor,Dt.tenTuor,Dt.soLuong,Dt.maKH,Dt.tenKH,Hd.tenNV,Hd.maNV,Hd.tongTien from DatTuor as Dt INNER JOIN HoaDon as Hd ON Dt.maKH =Hd.maKH";
//			Statement statement = con.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//			while (rs.next()) {
//
//				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
//						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
//			}
//			;
//		} catch (Exception e) {
//			System.out.println("error" + e);
//		}
//
//		table.setModel(model);
//		
//		table.getColumnModel().getColumn(0).setPreferredWidth(90);
//		table.getColumnModel().getColumn(1).setPreferredWidth(100);
//		table.getColumnModel().getColumn(2).setPreferredWidth(200);
//		table.getColumnModel().getColumn(3).setPreferredWidth(100);
//		table.getColumnModel().getColumn(4).setPreferredWidth(150);
//		table.getColumnModel().getColumn(5).setPreferredWidth(100);
//		table.getColumnModel().getColumn(6).setPreferredWidth(150);
//		table.getColumnModel().getColumn(7).setPreferredWidth(150);
//	}
	/**
	 * Create the frame.
	 * @param sdt 
	 */
	public HuyTuorForm(String sdt) {
		setTitle("Hủy Tour");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

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
		
		JLabel lblNewLabel = new JLabel("H\u1EE7y Tuor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(486, 10, 342, 52);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		JLabel lblTenKH = new JLabel("Tên Khách Hàng:");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenKH.setBounds(245, 146, 152, 29);
		
		txtTenKH = new JTextField();
		txtTenKH.setBounds(536, 144, 449, 37);
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);
		
		JLabel lblSDT = new JLabel("Số Điện Thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDT.setBounds(245, 210, 123, 37);
		
		txtSDt = new JTextField();
		txtSDt.setBounds(536, 207, 449, 48);
		txtSDt.setText(sdt);
		txtSDt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settextMaKH();
			}
		});
		txtSDt.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 303, 1368, 329);
		
		JButton btnNewButton = new JButton("H\u1EE7y Tuor");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(233, 676, 176, 48);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int ma = (int) table.getValueAt(row, 0);
				ds.delete(ma);
				model.removeRow(row);
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u0110\u00F3ng");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(1026, 676, 165, 48);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		
		JButton btn = new JButton("Tìm Kiếm");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn.setBounds(1067, 204, 165, 48);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				indanhsach();
//				settextMaKH();
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(78, 78, 0, 0);
		contentPane.setLayout(null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblTenKH);
		contentPane.add(lblSDT);
		contentPane.add(txtSDt);
		contentPane.add(txtTenKH);
		contentPane.add(btn);
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		contentPane.add(lblNewLabel);
		
		JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaKH.setBounds(245, 78, 128, 29);
		contentPane.add(lblMaKH);
		
		txtMaKH = new JTextField();
		txtMaKH.setEditable(false);
		txtMaKH.setBounds(536, 73, 449, 43);
		contentPane.add(txtMaKH);
		txtMaKH.setColumns(10);
		
		JLabel lblDSTour = new JLabel("Danh sách tour");
		lblDSTour.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblDSTour.setBounds(55, 270, 112, 13);
		contentPane.add(lblDSTour);
		indanhsach();
		settextMaKH();
	}
}

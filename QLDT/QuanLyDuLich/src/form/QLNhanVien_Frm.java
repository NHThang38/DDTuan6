package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

import java.awt.SystemColor;
import java.awt.Checkbox;
import javax.swing.JRadioButton;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;
import com.toedter.calendar.JDateChooser;

import control.DanhSachNhanVien;
import database.database;
import entity.NhanVien;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class QLNhanVien_Frm extends JFrame {

	private JPanel contentPane;
	private JTextField txtmaNV;
	private JTextField txtTenNV;
	private JTextField txtCMND;
	private JTextField txtdiaChi;
	private DefaultTableModel model;
	private DanhSachNhanVien dsnv = new DanhSachNhanVien();
	private JTextField txtSDT;
	private JTable table;
	private JDateChooser dateChooser;
	private JTextField txtemail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLNhanVien_Frm frame = new QLNhanVien_Frm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void random() {
		Random ran = new Random();
		int n = ran.nextInt(1000000) + 1;
		String val = String.valueOf(n);
		txtmaNV.setText(val);
	}

	private void SuaNV() {

		Date date = new Date(dateChooser.getDate().getTime());
		dsnv.SuaDSNV(txtTenNV.getText(), Integer.parseInt(txtmaNV.getText()), txtdiaChi.getText(), date,
				txtemail.getText(), txtCMND.getText(), Integer.parseInt(txtSDT.getText()));

	}

	private void showdatabase() {
		Connection con = database.Con();
		model = new DefaultTableModel();
		model.addColumn("Mã Nhân viên");
		model.addColumn("Tên nhân viên");
		model.addColumn("CMND");
		model.addColumn("SDT");
		model.addColumn("Địa chỉ");
		model.addColumn("Ngày sinh");

		model.addColumn("Email");
		try {
			List<NhanVien> list = dsnv.show();
			for (NhanVien nv : list) {
				model.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getCmnd(), nv.getSDT(), nv.getDiaChi(),
						nv.getNgaySinh(), nv.getEmail() });
			}
			con.close();
			table.setModel(model);
	
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(155);
			table.getColumnModel().getColumn(3).setPreferredWidth(130);
			table.getColumnModel().getColumn(4).setPreferredWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(150);
			table.getColumnModel().getColumn(6).setPreferredWidth(180);

		} catch (Exception e) {
			System.out.println("error" + e);
		}
	}

	/**
	 * Create the frame.
	 */
	public QLNhanVien_Frm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				showdatabase();
			}
		});

		setTitle("Quản lý nhân viên");
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

		JLabel lblQLNV = new JLabel("Qu\u1EA3n L\u00FD Nh\u00E2n Vi\u00EAn");
		lblQLNV.setForeground(Color.BLUE);
		lblQLNV.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblQLNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblQLNV.setBounds(163, 10, 1040, 62);
		contentPane.add(lblQLNV);

		JLabel lblMaNhanVien = new JLabel("M\u00E3 Nh\u00E2n Vi\u00EAn:");
		lblMaNhanVien.setForeground(Color.BLACK);
		lblMaNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMaNhanVien.setBounds(10, 112, 127, 35);
		contentPane.add(lblMaNhanVien);

		txtmaNV = new JTextField();
		txtmaNV.setEditable(false);
		txtmaNV.setBackground(Color.LIGHT_GRAY);
		txtmaNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtmaNV.setBounds(147, 112, 456, 35);
		contentPane.add(txtmaNV);
		txtmaNV.setColumns(10);

		JLabel lblTen = new JLabel("T\u00EAn Nh\u00E2n Vi\u00EAn:");
		lblTen.setForeground(Color.BLACK);
		lblTen.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTen.setBounds(10, 175, 127, 35);
		contentPane.add(lblTen);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtTenNV.setColumns(10);
		txtTenNV.setBounds(147, 175, 456, 35);
		contentPane.add(txtTenNV);

		txtCMND = new JTextField();
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtCMND.setColumns(10);
		txtCMND.setBounds(147, 227, 456, 35);
		contentPane.add(txtCMND);

		JLabel lblSDT = new JLabel("S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i");
		lblSDT.setForeground(Color.BLACK);
		lblSDT.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSDT.setBounds(869, 93, 127, 35);
		contentPane.add(lblSDT);

		JLabel lblNgaySinh = new JLabel("Ng\u00E0y sinh:");
		lblNgaySinh.setForeground(Color.BLACK);
		lblNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNgaySinh.setBounds(869, 188, 127, 35);
		contentPane.add(lblNgaySinh);

		JLabel lblDiaChi = new JLabel("\u0110\u1ECBa Ch\u1EC9:");
		lblDiaChi.setForeground(Color.BLACK);
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDiaChi.setBounds(869, 138, 127, 35);
		contentPane.add(lblDiaChi);

		JButton btnthem = new JButton("Th\u00EAm");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random();
				Date date = new Date(dateChooser.getDate().getTime());

				dsnv.SaveDSNV(txtTenNV.getText(), Integer.parseInt(txtmaNV.getText()), txtdiaChi.getText(), date,
						txtemail.getText(), txtCMND.getText(), Integer.parseInt(txtSDT.getText()));

				Object[] rowData = { Integer.parseInt(txtmaNV.getText()), txtTenNV.getText(), txtCMND.getText(),
						txtSDT.getText(), txtdiaChi.getText(), date, txtemail.getText() + "" };
				model.addRow(rowData);
			}
		});
		btnthem.setForeground(Color.BLACK);
		btnthem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnthem.setBounds(155, 662, 168, 54);
		contentPane.add(btnthem);

		JButton btnxoa = new JButton("X\u00F3a");
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int ma = (int) table.getValueAt(row, 0);
				dsnv.delete(ma);
				model.removeRow(row);
			}
		});
		btnxoa.setForeground(Color.BLACK);
		btnxoa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnxoa.setBounds(569, 665, 168, 48);
		contentPane.add(btnxoa);

		JButton btnsua = new JButton("S\u1EEDa");
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date1 = new Date(dateChooser.getDate().getTime());
				SuaNV();
				int row = table.getSelectedRow();
				table.setValueAt(txtTenNV.getText(), row, 1);
				table.setValueAt(txtCMND.getText(), row, 2);
				table.setValueAt(txtSDT.getText(), row, 3);
				table.setValueAt(date1, row, 5);
				table.setValueAt(txtdiaChi.getText(), row, 4);
				table.setValueAt(txtemail.getText(), row, 6);
			}

		});
		btnsua.setForeground(Color.BLACK);
		btnsua.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnsua.setBounds(905, 665, 162, 48);
		contentPane.add(btnsua);

		JButton btnthoat = new JButton("\u0110\u00F3ng");
		btnthoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		btnthoat.setForeground(Color.BLACK);
		btnthoat.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnthoat.setBounds(1244, 665, 157, 48);
		contentPane.add(btnthoat);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(1045, 188, 386, 35);
		contentPane.add(dateChooser);

		txtdiaChi = new JTextField();
		txtdiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtdiaChi.setColumns(10);
		txtdiaChi.setBounds(1042, 138, 389, 35);
		contentPane.add(txtdiaChi);

		JLabel lblCmnd = new JLabel("CMND");
		lblCmnd.setForeground(Color.BLACK);
		lblCmnd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblCmnd.setBounds(10, 233, 127, 35);
		contentPane.add(lblCmnd);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtSDT.setColumns(10);
		txtSDT.setBounds(1036, 93, 395, 35);
		contentPane.add(txtSDT);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(75, 340, 1423, 294);
		contentPane.add(scrollPane_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					int srow = table.getSelectedRow();

					int row = table.getSelectedRow();
					Date b = (Date) table.getValueAt(row, 5);
					txtmaNV.setText(table.getValueAt(row, 0).toString());
					txtTenNV.setText(table.getValueAt(row, 1).toString());
					txtCMND.setText(table.getValueAt(row, 2).toString());
					txtSDT.setText(table.getValueAt(row, 3).toString());
					dateChooser.setDate(b);
					txtdiaChi.setText(table.getValueAt(row, 4).toString());
					txtemail.setText(table.getValueAt(row, 6).toString());
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e);
				}

			}
		});
		scrollPane.setViewportView(table);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEmail.setBounds(869, 233, 127, 35);
		contentPane.add(lblEmail);

		txtemail = new JTextField();
		txtemail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		txtemail.setColumns(10);
		txtemail.setBounds(1045, 233, 386, 35);
		contentPane.add(txtemail);
		
		JLabel lblDSNV = new JLabel("Danh sách nhân viên");
		lblDSNV.setForeground(Color.BLACK);
		lblDSNV.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDSNV.setBackground(Color.MAGENTA);
		lblDSNV.setBounds(76, 297, 157, 35);
		contentPane.add(lblDSNV);
		random();
	}
}

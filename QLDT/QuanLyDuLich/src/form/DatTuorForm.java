package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.DanhSachDatTuor;
import control.DanhSachKhachHang;
import control.DanhSachTuor;
import database.database;
import entity.KhachHang;
import entity.Tuor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class DatTuorForm extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JTextField txtMaKH;
	String a, tt;
	private JTextField txtSDT;
	private JTextField txtMaDT;
	private JTextField txtMaTuor;
	private JTextField txtSoLuong;
	private JTextField txtTenTuor;
	private DanhSachTuor dstuor = new DanhSachTuor();
	private DefaultTableModel model, model1;

	int b = 0,c;
	private JTextField txtTT;
	private JTextField txtTenKH;
	DanhSachKhachHang ds = new DanhSachKhachHang();
	private JTextField txtSoLuongTreEm;
	float qq =0;
	private JLabel lblMaKH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatTuorForm frame = new DatTuorForm(" ");
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
					+ txtSDT.getText() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtTenKH.setText(rs.getString(2));
				txtMaKH.setText(rs.getString(1));
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}
	private void setMaTuor(String MaTuor) {
		Connection con = database.Con();
		try {
			String sql = "select * from Tour Where maTour ="
					+ MaTuor + "";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtMaTuor.setText(rs.getString(1));
				txtTenTuor.setText(rs.getString(2));
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}
	}


	private void showdatabase() {

		model = new DefaultTableModel();
		model.addColumn("Mã Tour");
		model.addColumn("Tên Tour");
		model.addColumn("Số chỗ chống");
		model.addColumn("Ngày khởi hành ");
		model.addColumn("Ngày kết thúc");
		model.addColumn("Địa Điểm Khởi Hành");
		model.addColumn("Giá");
		try {
			List<Tuor> list = dstuor.show();
			for (Tuor tuor : list) {
				model.addRow(new Object[] { tuor.getMaTuor(), tuor.getTenTuor(), tuor.getSochoNhan(), tuor.getNgayDi(),
						tuor.getNgayKT(), tuor.getTuyenDuong(), tuor.getGia() });
			}

			table_1.setModel(model);
			
			table_1.getColumnModel().getColumn(0).setPreferredWidth(70);
			;
			table_1.getColumnModel().getColumn(1).setPreferredWidth(153);
			table_1.getColumnModel().getColumn(2).setPreferredWidth(100);
			table_1.getColumnModel().getColumn(3).setPreferredWidth(120);
			table_1.getColumnModel().getColumn(4).setPreferredWidth(120);
			table_1.getColumnModel().getColumn(5).setPreferredWidth(120);
			table_1.getColumnModel().getColumn(6).setPreferredWidth(108);

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}

	public void TimKiemTen() {
		Connection con = database.Con();

		try {
			String sql = "Select * from Tour Where tenTour Like'%" + txtTenTuor.getText() + "%'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				model.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7)

				});
			}
		} catch (SQLException e) {

			System.out.println("error" + e);
		}
	}
	
	public void SuaTuorne() {
		
		Connection con = database.Con();
		PreparedStatement stmt = null;
			try {
				System.out.println(b);
				String sql = "Select * from Tour where maTour ="+txtMaTuor.getText()+"";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				stmt = con.prepareStatement("update Tour "
						+ "set tenTour = ?,"
						+ "soLuong = ?,"
						+ "ngayKH = ? ,"
						+ "ngayKT = ? ,"
						+ "ThoiGianKhoiHanh = ? ,"
						+ "diemKH = ? ,"
						+ "donGia = ? "
						+ "where maTour = ?");

				rs.next();

					stmt.setString(1,rs.getString(2));
					stmt.setInt(2, b);
					stmt.setDate(3,rs.getDate(4));
					stmt.setDate(4,rs.getDate(5));
					stmt.setString(5,rs.getString(6));
					stmt.setString(6, rs.getString(7));
					stmt.setFloat(7, rs.getFloat(8));
					stmt.setInt(8, Integer.parseInt(txtMaTuor.getText().toString()));

				

				stmt.execute();

			} catch (Exception e) {
				System.out.println("DatTuor" + e);
			}

		

	}
	public void random() {
		Random ran = new Random();
		int n = ran.nextInt(1000000) + 1;
		String val = String.valueOf(n);
		txtMaDT.setText(val);
	}

	/**
	 * Create the frame.
	 * @param maTuor 
	 */
	public DatTuorForm(String maTuor) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			//	showdata();
				showdatabase();
			}
		});
		setTitle("Đặt Tour");
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

		JLabel lblDatTour = new JLabel("Đặt Tour");
		lblDatTour.setBounds(552, 38, 419, 34);
		lblDatTour.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatTour.setForeground(Color.BLUE);
		lblDatTour.setFont(new Font("Times New Roman", Font.BOLD, 30));

		lblMaKH = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng:");
		lblMaKH.setBounds(15, 129, 123, 38);
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblTenKH = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng:");
		lblTenKH.setBounds(10, 194, 123, 32);
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtMaKH = new JTextField();
		txtMaKH.setBounds(162, 129, 401, 48);
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(162, 265, 401, 41);
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settextMaKH();
				//indanhsach();
			}
		});
		txtSDT.setColumns(10);

		JLabel lblSDT = new JLabel("S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i:");
		lblSDT.setBounds(15, 265, 111, 37);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblMaDatTour = new JLabel("Mã Đặt Tour:");
		lblMaDatTour.setBounds(25, 79, 108, 35);
		lblMaDatTour.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtMaDT = new JTextField();
		txtMaDT.setBounds(162, 78, 401, 41);
		txtMaDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaDT.setEditable(false);
		txtMaDT.setColumns(10);

		txtMaTuor = new JTextField();
		txtMaTuor.setBounds(983, 79, 480, 47);
		txtMaTuor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaTuor.setEditable(false);
		txtMaTuor.setColumns(10);

		JLabel lblMaTour = new JLabel("Mã Tour:");
		lblMaTour.setBounds(798, 85, 90, 29);
		lblMaTour.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblTenTour = new JLabel("Tên Tour:");
		lblTenTour.setBounds(798, 151, 105, 29);
		lblTenTour.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblSoLuongNguoiLon = new JLabel("Số Lượng Người Lớn:");
		lblSoLuongNguoiLon.setBounds(802, 218, 169, 30);
		lblSoLuongNguoiLon.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(983, 209, 480, 48);
		txtSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				qq = Float.parseFloat(tt) * Integer.parseInt(txtSoLuong.getText().toString());
				txtTT.setText(String.valueOf(qq));
			}
		});
		txtSoLuong.setColumns(10);
		txtTenTuor = new JTextField();
		txtTenTuor.setBounds(983, 141, 480, 48);
		txtTenTuor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenTuor.setEditable(false);
		txtTenTuor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				TimKiemTen();
			}
		});
		txtTenTuor.setColumns(10);

		JButton btnDatTour = new JButton("Đặt Tour");
		btnDatTour.setBounds(271, 719, 165, 48);
		btnDatTour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDatTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			if (Integer.parseInt(txtSoLuong.getText())>0) {
				int row = table_1.getSelectedRow();
				a = table_1.getValueAt(row, 2).toString();
				b = Integer.parseInt(a) - Integer.parseInt(txtSoLuong.getText().toString()) - Integer.parseInt(txtSoLuongTreEm.getText().toString());
				a = String.valueOf(b);
				if (b < 0) {
					
					JOptionPane.showConfirmDialog(null, "Hết chỗ vui lòng đặt chỗ khác!!","",JOptionPane.YES_NO_OPTION);
					txtSoLuong.setText("0");
				} else {
					SuaTuorne();
					table_1.setValueAt(b, row, 2);
				
					DanhSachDatTuor ds = new DanhSachDatTuor();
					ds.SaveDT(Integer.parseInt(txtMaDT.getText()), Integer.parseInt(txtMaTuor.getText()),
							Integer.parseInt(txtMaKH.getText()), Integer.parseInt(txtSoLuong.getText()),
							Integer.parseInt(txtSoLuongTreEm.getText()));
					JOptionPane.showConfirmDialog(null, "Đặt tuor thành công!!","",JOptionPane.YES_NO_OPTION);
					random();
				}
			}else {
				JOptionPane.showMessageDialog(null, "số lượng người lớn >0");
			}
				

			}
		});

		JButton btnHuyTour = new JButton("Hủy Tour");
		btnHuyTour.setBounds(616, 719, 141, 48);
		btnHuyTour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuyTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new HuyTuorForm(txtSDT.getText()).setVisible(true);
				
			}
		});

		JButton btnDong = new JButton("\u0110\u00F3ng");
		btnDong.setBounds(1032, 719, 150, 48);
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		btnDong.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblTongTien = new JLabel("Tổng Tiền:");
		lblTongTien.setBounds(20, 332, 106, 41);
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtTT = new JTextField();
		txtTT.setBounds(162, 336, 401, 37);
		txtTT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTT.setEditable(false);
		txtTT.setColumns(10);
		txtTT.setText("0");

		txtTenKH = new JTextField();
		txtTenKH.setBounds(162, 194, 401, 38);
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKH.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(lblMaDatTour);
		contentPane.add(lblTenKH);
		contentPane.add(lblMaKH);
		contentPane.add(lblSDT);
		contentPane.add(txtSDT);
		contentPane.add(txtTenKH);
		contentPane.add(txtMaKH);
		contentPane.add(txtMaDT);
		contentPane.add(lblTongTien);
		contentPane.add(lblSoLuongNguoiLon);
		contentPane.add(lblTenTour);
		contentPane.add(lblMaTour);
		contentPane.add(txtTT);
		contentPane.add(txtSoLuong);
		contentPane.add(txtTenTuor);
		contentPane.add(txtMaTuor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 403, 1396, 306);
		contentPane.add(scrollPane);
		
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane.setColumnHeaderView(scrollPane_1);
				
						table_1 = new JTable();
						scrollPane_1.setViewportView(table_1);
						table_1.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								try {
									int srow = table_1.getSelectedRow();

									int row = table_1.getSelectedRow();
									txtMaTuor.setText(table_1.getValueAt(row, 0).toString());
									txtTenTuor.setText(table_1.getValueAt(row, 1).toString());
									a = table_1.getValueAt(row, 2).toString();
									tt = table_1.getValueAt(row, 6).toString();
								} catch (Exception e) {
									JOptionPane.showConfirmDialog(null, e);
								}

							}
						});
		contentPane.add(btnDatTour);
		contentPane.add(btnHuyTour);
		contentPane.add(btnDong);
		contentPane.add(lblDatTour);
		
		JLabel lblSoLuongTreEm = new JLabel("Số Lượng trẻ em:");
		lblSoLuongTreEm.setBounds(798, 290, 150, 27);
		lblSoLuongTreEm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblSoLuongTreEm);
		
		txtSoLuongTreEm = new JTextField();
		txtSoLuongTreEm.setBounds(983, 279, 480, 48);
		txtSoLuongTreEm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSoLuongTreEm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				
			}
		});
		contentPane.add(txtSoLuongTreEm);
		txtSoLuongTreEm.setColumns(10);
		
		JButton btnTongTien = new JButton("Tổng tiền");
		btnTongTien.setBounds(697, 335, 150, 35);
		btnTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTongTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				qq = (Float.parseFloat(tt) * Integer.parseInt(txtSoLuong.getText().toString()))+(Float.parseFloat(tt)
						*Integer.parseInt(txtSoLuongTreEm.getText().toString())*75/100);
				txtTT.setText(String.valueOf(qq));
			}
		});
		contentPane.add(btnTongTien);
		
		JLabel lblDanhSanhTour = new JLabel("Danh sách tour");
		lblDanhSanhTour.setBounds(72, 371, 200, 35);
		lblDanhSanhTour.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		contentPane.add(lblDanhSanhTour);
		random();
		setMaTuor(maTuor);
		
	}
}

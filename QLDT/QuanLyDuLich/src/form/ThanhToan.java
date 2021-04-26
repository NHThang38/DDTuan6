package form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import control.DanhSachDatTuor;
import control.DanhSachHD;
import database.database;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThanhToan extends JFrame {

	private JPanel contentPane;
	private JTextField txtHD;
	private JTextField txtTenNV;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JTable table;
	private JTextField txtGia;
	JComboBox comboBoxNV;
	private DefaultTableModel model;
	private DanhSachHD dshd = new DanhSachHD();
	private JTextField txtMaKH;
	
	JDateChooser dateChooser;
	/**
	 * Launch the application.
	 */
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThanhToan frame = new ThanhToan();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	

	private void settextKH() {
		Connection con = database.Con();
		try {
			String sql = "select * from KhachHang Where SDT Like '" + txtSDT.getText() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtMaKH.setText(rs.getString(1));
				txtDiaChi.setText(rs.getString(5));
				txtTenKH.setText(rs.getString(2));
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}
	public void delete() {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
		try {
			String sql = "Select maDT from DatTour where maKH="+txtMaKH.getText()+"";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				stmt = con.prepareStatement("delete from DatTour where maDT = ?");
				stmt.setInt(1, rs.getInt(1));
				stmt.executeUpdate();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	private void settextNV() {
		Connection con = database.Con();
		try {
			String sql = "select * from NhanVien Where maNV Like '" + comboBoxNV.getSelectedItem() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtTenNV.setText(rs.getString(2));

			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}

	private void showjcombobox() {
		Connection con = database.Con();
		try {
			String sql = "select * from NhanVien";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				comboBoxNV.addItem(rs.getString(1));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void showmaHD() {
		Connection con = database.Con();
		try {
			String sql = "select * from DatTour";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtHD.setText(rs.getString(1));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void tongTien() {
		Connection con = database.Con();
		try {
			String sql = "select sum((dt.soLuongNguoiLon*tu.donGia)+(dt.soLuongTreEm*tu.donGia*75/100)) as sumprice from DatTour as dt INNER JOIN Tour as tu ON dt.maTour=tu.maTour where dt.maKH ='"
					+ txtMaKH.getText() + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String sum = rs.getString("sumprice");
				System.out.println(sum);
				txtGia.setText(sum);

			}
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

	}
	int a=0;
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
				a++;
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
	int b;
	public void ThemCTHD() {
		Date date = new java.sql.Date(dateChooser.getDate().getTime());
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
			try {
					
				
				String sql = "Select Dt.maDT,Tu.maTour,Dt.soLuongNguoiLon,Dt.soLuongTreEm,Tu.donGia  from DatTour as Dt INNER JOIN Tour as Tu ON Dt.maTour =Tu.maTour where Dt.maKH Like'"+txtMaKH.getText()+"'";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				PreparedStatement stmt2 = null;
				
				while (rs.next()) {
					Random ran = new Random();
					int n = ran.nextInt(1000000) + 1;
					String val= String.valueOf(n);
					dshd.SaveTuor(Integer.parseInt(val), date,Integer.parseInt(comboBoxNV.getSelectedItem().toString()),Integer.parseInt(txtMaKH.getText()));
					stmt2 = con.prepareStatement("insert into ChiTietHoaDon values(? ,? ,?, ? ,? ,?)");
					stmt2.setInt(1,Integer.parseInt(val));
					stmt2.setInt(2,rs.getInt(2));
					stmt2.setInt(3,rs.getInt(1));
					stmt2.setInt(4,rs.getInt(3));
					stmt2.setInt(5,rs.getInt(4));
					stmt2.setInt(6,rs.getInt(5));
					stmt2.executeUpdate();
					
					b++;
//					tongtien = tongtien+rs.getInt(5)*rs.getInt(3)+rs.getInt(5)*rs.getInt(4)*75/100;
					
				} 
				
					
					
					
				
			
			} catch (Exception e) {
				System.out.println("error" + e);
			}
			
		}
			
		
	

	

	public ThanhToan() {
		setTitle("Thanh Toán");
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

		JLabel lblThanhToan = new JLabel("Thanh To\u00E1n");
		lblThanhToan.setBounds(652, 10, 247, 33);
		lblThanhToan.setForeground(Color.BLUE);
		lblThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 28));

		JLabel lblMaHoaDon = new JLabel("M\u00E3 H\u00F3a \u0110\u01A1n:");
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaHoaDon.setBounds(21, 73, 95, 32);

		JLabel lblNgayLapHD = new JLabel("Ng\u00E0y L\u1EADp H\u00F3a \u0110\u01A1n:");
		lblNgayLapHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgayLapHD.setBounds(15, 136, 116, 27);

		txtHD = new JTextField();
		txtHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtHD.setBounds(153, 69, 448, 46);
		txtHD.setEditable(false);
		txtHD.setColumns(10);
		dateChooser = new JDateChooser();	
		dateChooser.setBounds(153, 124, 448, 46);
		dateChooser.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
		dateChooser.setEnabled(false);
		
		JLabel lblMaNV = new JLabel("M\u00E3 Nh\u00E2n Vi\u00EAn:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaNV.setBounds(15, 185, 116, 33);

		JLabel lblKH = new JLabel("M\u00E3 Kh\u00E1ch H\u00E0ng:");
		lblKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKH.setBounds(694, 79, 138, 33);

		JLabel tenKH = new JLabel("T\u00EAn Kh\u00E1ch H\u00E0ng:");
		tenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tenKH.setBounds(694, 136, 148, 40);

		JLabel lblSDTKH = new JLabel("S\u0110T Kh\u00E1ch H\u00E0ng:");
		lblSDTKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDTKH.setBounds(694, 185, 156, 47);

		JLabel lblTenNV = new JLabel("T\u00EAn Nh\u00E2n Vi\u00EAn");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTenNV.setBounds(15, 236, 116, 33);

		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDiaChi.setBounds(694, 242, 116, 40);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenNV.setBounds(153, 236, 448, 46);
		txtTenNV.setEditable(false);
		txtTenNV.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTenKH.setBounds(883, 142, 600, 40);
		txtTenKH.setEditable(false);
		txtTenKH.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setBounds(882, 187, 600, 47);
		txtSDT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			settextKH();	
			indanhsach();
			tongTien();
			}
		});
		txtSDT.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDiaChi.setBounds(882, 245, 600, 40);
		txtDiaChi.setEditable(false);
		txtDiaChi.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 353, 1471, 255);

		JLabel lblTongTien = new JLabel("T\u1ED5ng Ti\u1EC1n:");
		lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTongTien.setBounds(1134, 619, 116, 33);

		txtGia = new JTextField();
		txtGia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtGia.setBounds(1264, 615, 228, 40);
		txtGia.setEditable(false);
		txtGia.setColumns(10);

		JButton btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThanhToan.setBounds(168, 698, 183, 47);
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date1 = new Date(dateChooser.getDate().getTime()); 
				
				if (a>0) {
					if (a>b) {
						ThemCTHD();
						JOptionPane.showMessageDialog(null, "Thanh Toán Thành Công");
						model.setRowCount(0);
					}else {
						JOptionPane.showMessageDialog(null, "Đã Thanh Toán rồi");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Không có tuor để thanh toán");
				}
			}
		});

		JButton btnInHoaDon = new JButton("In Hóa Đơn");
		btnInHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInHoaDon.setBounds(549, 698, 169, 47);
		btnInHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
		
				
					Connection con = database.Con();
					
					float tongtien=0;
						try {
								
							
							String sql = "Select Dt.maDT,Tu.maTour,Dt.soLuongNguoiLon,Dt.soLuongTreEm,Tu.donGia  from DatTour as Dt INNER JOIN Tour as Tu ON Dt.maTour =Tu.maTour where Dt.maKH Like'"+txtMaKH.getText()+"'";
							Statement statement = con.createStatement();
							ResultSet rs = statement.executeQuery(sql);
							
							
							while (rs.next()) {
								
								tongtien = tongtien+rs.getInt(5)*rs.getInt(3)+rs.getInt(5)*rs.getInt(4)*75/100;
								
							} 
						
						} catch (Exception e) {
							System.out.println("error" + e);
						}
						
					
					
				
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String date1 = df.format(dateChooser.getDate());
				
				try {
					JasperDesign jasd = JRXmlLoader.load("src\\Relatorio\\report2 (1).jrxml");
					String sql = "select Dt.maTour,Tu.tenTour,Tu.donGia,Dt.soLuongNguoiLon,Dt.soLuongTreEm from DatTour as Dt INNER JOIN Tour as Tu ON Dt.maTour=Tu.maTour Where Dt.maKH Like'"+txtMaKH.getText()+"'";
	                
					JRDesignQuery newquery =new JRDesignQuery();
					newquery.setText(sql);
					jasd.setQuery(newquery);
					HashMap<String ,Object> para= new HashMap<>();
					para.put("maKH", txtMaKH.getText());
					para.put("tenKH",txtTenKH.getText());
					para.put("tenNV", txtTenNV.getText());
					para.put("tongTien", tongtien);
					para.put("ngayLapHoaDon", date1);
					JasperReport js =JasperCompileManager.compileReport(jasd);
					JasperPrint jp= JasperFillManager.fillReport(js, para,con);
					JasperViewer.viewReport(jp);
				} catch (JRException ex) {
	               ex.printStackTrace();
	            }
				delete();
			
			
			}
			

			
		});

		JButton btnDong = new JButton("\u0110\u00F3ng");
		btnDong.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDong.setBounds(1309, 698, 156, 47);
		btnDong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
					System.exit(0);
			}
		});

		comboBoxNV = new JComboBox();
		comboBoxNV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxNV.setBounds(153, 180, 448, 46);
		comboBoxNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				settextNV();
			}
		});
		
		txtMaKH = new JTextField();
		txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMaKH.setBounds(883, 78, 600, 40);
		txtMaKH.setEditable(false);
		txtMaKH.setColumns(10);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		contentPane.add(lblMaHoaDon);
		contentPane.add(lblMaNV);
		contentPane.add(lblTenNV);
		contentPane.add(lblNgayLapHD);
		contentPane.add(comboBoxNV);
		contentPane.add(txtTenNV);
		contentPane.add(dateChooser);
		contentPane.add(txtHD);
		contentPane.add(tenKH);
		contentPane.add(lblKH);
		contentPane.add(lblSDTKH);
		contentPane.add(lblDiaChi);
		contentPane.add(txtMaKH);
		contentPane.add(txtDiaChi);
		contentPane.add(txtSDT);
		contentPane.add(txtTenKH);
		contentPane.add(scrollPane);
		contentPane.add(lblTongTien);
		contentPane.add(txtGia);
		contentPane.add(btnThanhToan);
		contentPane.add(btnInHoaDon);
		contentPane.add(btnDong);
		contentPane.add(lblThanhToan);
		
		JButton btnHuyTour = new JButton("Hủy Tour");
		btnHuyTour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DanhSachDatTuor ds = new DanhSachDatTuor();
				int row = table.getSelectedRow();
				int ma = (int) table.getValueAt(row, 0);
				ds.delete(ma);
				model.removeRow(row);
			}
		});
		btnHuyTour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHuyTour.setBounds(923, 698, 169, 47);
		contentPane.add(btnHuyTour);
		
		JLabel lblDanhSacht = new JLabel("Danh sách đặt tour");
		lblDanhSacht.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblDanhSacht.setBounds(42, 319, 169, 13);
		contentPane.add(lblDanhSacht);
		
	
		showjcombobox();
	}
}

package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.database;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DangNhapForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenDangNhap;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhapForm frame = new DangNhapForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public DangNhapForm() {
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDangnhap = new JLabel("\u0110\u0103ng nh\u1EADp");
		lblDangnhap.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblDangnhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangnhap.setBounds(204, 13, 216, 64);
		contentPane.add(lblDangnhap);
		
		JLabel lblTenDangNhap = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp");
		lblTenDangNhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTenDangNhap.setBounds(29, 137, 125, 41);
		contentPane.add(lblTenDangNhap);
		
		JLabel lblMatKhau = new JLabel("M\u1EADt Kh\u1EA9u");
		lblMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatKhau.setBounds(29, 222, 104, 41);
		contentPane.add(lblMatKhau);
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setBounds(183, 136, 349, 42);
		contentPane.add(txtTenDangNhap);
		txtTenDangNhap.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 224, 349, 42);
		contentPane.add(passwordField);
		
		JButton btnDangnhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btnDangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtTenDangNhap.getText();
				@SuppressWarnings("deprecation")
				String password =passwordField.getText();
				if(username.equals("")||password.equals("")) {
					JOptionPane.showMessageDialog(null, "Tên đăng nhập và mật khẩu không được để trống");
				}else {
				try {
					Connection connection = database.Con();
					
					String sql1 = "Select * from TaiKhoan where TenDangNhap=? and MatKhau =?";
					PreparedStatement statement1 = connection.prepareStatement(sql1);
					statement1.setString(1, username);
					statement1.setString(2, password);
					ResultSet rs1 =statement1.executeQuery();
					if(rs1.next()) {
						String type = rs1.getString("ChucVu");
					if(type.equals("KhachHang")) {
						JOptionPane.showMessageDialog(null, "Khách hàng");
					}
					else if(type.equals("NhanVien")) {
						JOptionPane.showMessageDialog(null, "Nhân Viên");
					}
					else if(type.equals("Admin")) {
						TrangChuForm trangtru = new TrangChuForm();
						trangtru.setVisible(true);
						setVisible(false);
					}
					}
					else {
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
					}
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			}
		});
		btnDangnhap.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDangnhap.setBounds(398, 322, 134, 41);
		contentPane.add(btnDangnhap);
		
		JButton btnNewButton = new JButton("Tho\u00E1t");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret=JOptionPane.showConfirmDialog(null, "Bạn muốn thoát", "Đóng", JOptionPane.YES_NO_OPTION);
	            if(ret==JOptionPane.YES_OPTION)
	               setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(93, 322, 134, 41);
		contentPane.add(btnNewButton);
	}
}

package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.database;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class formLichTrinh extends JFrame {

	private JPanel contentPane;
	
	JTextArea textLichTrinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formLichTrinh frame = new formLichTrinh("","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void inKhachSan(String makS) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from KhachSan Where maKhachSan = "+makS+"";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		    String tenKS = null; String diaChi = null; String tieuchuan = null; String sDT = null;
		    while (rs.next()) {
		    	tenKS=rs.getString(2);
		    	diaChi=rs.getString(3);
		    	tieuchuan=rs.getString(4);
		    	sDT = rs.getString(5);
		    	
			}
		    textLichTrinh.append("\n\n   Sẽ ở khách Sạn\n");
		    textLichTrinh.append("   Tên Khách Sạn:"	+	tenKS+"\n");
		    textLichTrinh.append("   Địa chỉ Khách Sạn:"	+	diaChi+"\n");
		    textLichTrinh.append("   Tiêu Chuẩn:"	+	tieuchuan+"\n");
		    textLichTrinh.append("   Số Điện Thoại Khách Sạn:"	+	sDT+"\n");
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	private void InlichTrinh(String mTour) {
		Connection con = database.Con();
		
		try {
			String sql = "Select * from LichTrinhTour Where maTour = "+mTour+"";
			Statement statement = con.createStatement();
		    ResultSet rs = statement.executeQuery(sql);
		   
		    while (rs.next()) {
		    	textLichTrinh.append("\n"+rs.getString(3)+"\n");
		    	textLichTrinh.append("\n"+rs.getString(4)+"\n");
		    	
			}
		} catch (SQLException e) {
			
			System.out.println("error"+e);
		}
	}
	
	
	/**
	 * Create the frame.
	 * @param maTuor 
	 * @param makS 
	 */
	public formLichTrinh(String maTuor, String makS) {
		setTitle("Lịch trình");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 765, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 82, 741, 450);
		contentPane.add(scrollPane_1);
		
		textLichTrinh = new JTextArea();
		textLichTrinh.setBackground(SystemColor.menu);
//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        textLichTrinh.setLineWrap(true);
		textLichTrinh.setColumns(20);
		textLichTrinh.setEditable(false);
		textLichTrinh.append("Mã Tuor: "+maTuor);
		textLichTrinh.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane_1.setViewportView(textLichTrinh);
		
		JLabel lblChuongTrinhTour = new JLabel("Chương Trình Tour");
		lblChuongTrinhTour.setForeground(Color.BLUE);
		lblChuongTrinhTour.setFont(new Font("Times New Roman", Font.BOLD, 29));
		lblChuongTrinhTour.setBounds(239, 10, 288, 43);
		contentPane.add(lblChuongTrinhTour);
		
		JButton btnNewButton = new JButton("\u0110\u00F3ng");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(284, 542, 115, 45);
		contentPane.add(btnNewButton);
		inKhachSan(makS);
		InlichTrinh(maTuor);
		
	}
}

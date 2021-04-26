package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.database;
import entity.Tuor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class LichTrinhTour extends JFrame {

	private JPanel contentPane;
	private JTextField txtMatuor;
	private JTextField txtTenTour;
	private JTextField txtTieuDe;
	private DefaultTableModel model;
	private JTable table;
	static String MaTuor;
	JTextArea textArea ;
	private JTextField txtMaLichtrinh;
	 
	

	/**
	 * Launch the application.
	 */
	public void random() {
		Random ran = new Random();
		int n = ran.nextInt(1000000) + 1;
		String val = String.valueOf(n);
		txtMaLichtrinh.setText(val);
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LichTrinhTour frame = new LichTrinhTour(MaTuor);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void Them() {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			
			 stmt = con.prepareStatement("insert into LichTrinhTour values(? ,? ,? ,?)");
			 stmt.setString(1,txtMaLichtrinh.getText());
			stmt.setInt(2,Integer.parseInt(txtMatuor.getText()));
			stmt.setString(3,txtTieuDe.getText());
			stmt.setString(4, textArea.getText());
			;
			
			stmt.executeUpdate();
			con.close();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	
	}
	public void SuaLichTrinh() {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		
		try {
			
			 stmt = con.prepareStatement("update LichTrinhTour "
						+ "set maTour = ?,"
						+ "ChuongTrinhTour = ?,"
						+ "Mota = ? "
						+ "where maLichTrinh = ?");
			
			stmt.setInt(1,Integer.parseInt(txtMatuor.getText()));
			stmt.setString(2, txtTieuDe.getText());
			stmt.setString(3,textArea.getText());
			stmt.setInt(4, Integer.parseInt(txtMaLichtrinh.getText()));
			stmt.execute();
			
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
	public void delete() {
		Connection con = database.Con();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from LichTrinhTour where maLichTrinh = ?");
			stmt.setInt(1,Integer.parseInt( txtMaLichtrinh.getText()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Create the frame.
	 * @param MaTuor 
	 */
	public LichTrinhTour(String MaTuor) {
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblQLLTT = new JLabel("Quản Lý Lịch Trình Tour");
		lblQLLTT.setForeground(Color.BLUE);
		lblQLLTT.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblQLLTT.setBounds(374, 13, 420, 64);
		contentPane.add(lblQLLTT);
		
		txtMatuor = new JTextField();
		txtMatuor.setEditable(false);
		txtMatuor.setBounds(185, 105, 339, 22);
		contentPane.add(txtMatuor);
		txtMatuor.setColumns(10);
		txtMatuor.setText(MaTuor);
		
		JLabel lblMaTour = new JLabel("M\u00E3 Tour:");
		lblMaTour.setBounds(42, 105, 56, 16);
		contentPane.add(lblMaTour);
		
		JLabel lblTenTour = new JLabel("T\u00EAn Tour:");
		lblTenTour.setBounds(42, 143, 84, 16);
		contentPane.add(lblTenTour);
		
		txtTenTour = new JTextField();
		txtTenTour.setEditable(false);
		txtTenTour.setBounds(185, 140, 339, 22);
		contentPane.add(txtTenTour);
		txtTenTour.setColumns(10);
		
		txtTieuDe = new JTextField();
		txtTieuDe.setBounds(185, 206, 339, 22);
		contentPane.add(txtTieuDe);
		txtTieuDe.setColumns(10);
		
		JLabel lblLichTrinhTrongNgay = new JLabel("L\u1ECBch Tr\u00ECnh trong Ng\u00E0y:");
		lblLichTrinhTrongNgay.setBounds(42, 209, 130, 16);
		contentPane.add(lblLichTrinhTrongNgay);
		
		JLabel lblMoTa = new JLabel("M\u00F4 T\u1EA3:");
		lblMoTa.setBounds(42, 248, 56, 16);
		contentPane.add(lblMoTa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(557, 116, 602, 418);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					
					
					int row = table.getSelectedRow();
					txtMaLichtrinh.setText(table.getValueAt(row, 0).toString());
					txtTieuDe.setText(table.getValueAt(row,3).toString());
					textArea.setText(table.getValueAt(row, 4).toString());
					
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null, e);
				}
				
			}
		});
		
		JButton btnSua = new JButton("S\u1EEDa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuaLichTrinh();
				int row = table.getSelectedRow();
				table.setValueAt(txtTieuDe.getText(), row, 3);
				table.setValueAt(textArea.getText(), row, 4);
			}
		});
		btnSua.setBounds(225, 558, 117, 37);
		contentPane.add(btnSua);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				random();
				Them();
				Object[] rowData = {Integer.parseInt(txtMaLichtrinh.getText()),txtMatuor.getText(),txtTenTour.getText(),txtTieuDe.getText(),textArea.getText()};
				model.addRow(rowData);
			}
			
		});
		btnThem.setBounds(42, 558, 117, 37);
		contentPane.add(btnThem);
		
		JLabel lblLichTrinhTour = new JLabel("L\u1ECBch tr\u00ECnh Tour");
		lblLichTrinhTour.setBounds(552, 87, 117, 16);
		contentPane.add(lblLichTrinhTour);
		
		JButton btndong = new JButton("\u0110\u00F3ng");
		btndong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btndong.setBounds(598, 558, 117, 37);
		contentPane.add(btndong);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(185, 258, 333, 276);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        textArea.setLineWrap(true);
		textArea.setColumns(20);
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblMaLichTrinh = new JLabel("Mã Lịch Trình:");
		lblMaLichTrinh.setBounds(42, 180, 95, 16);
		contentPane.add(lblMaLichTrinh);
		
		txtMaLichtrinh = new JTextField();
		txtMaLichtrinh.setEditable(false);
		txtMaLichtrinh.setBounds(184, 177, 334, 22);
		contentPane.add(txtMaLichtrinh);
		txtMaLichtrinh.setColumns(10);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				delete();
				model.removeRow(row);
			}
		});
		btnXoa.setBounds(401, 558, 117, 37);
		contentPane.add(btnXoa);
		 settextMaKH();
		 random();
		 
		 
	}
	private void settextMaKH() {
		Connection con = database.Con();
		try {
			String sql = "select * from Tour Where maTour Like '"
					+ txtMatuor.getText() + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				txtTenTour.setText(rs.getString(2));
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}
		showdatabase();

	}
private void showdatabase() {
		
		model = new DefaultTableModel();
		model.addColumn("Mã Lịch trình");
		model.addColumn("Mã Tuor");
		model.addColumn("Tên Tuor");
		model.addColumn("Lịch trình trong ngày");
		model.addColumn("Mô tả");
		
		Connection con = database.Con();
		
		
		try {
			String sql = "Select Lt.maLichTrinh,Lt.maTour,t.tenTour,Lt.ChuongTrinhTour,Lt.MoTa from LichTrinhTour as Lt INNER JOIN Tour as t ON Lt.maTour=t.maTour where Lt.maTour="+txtMatuor.getText()+"";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				model.addRow(new Object[] { rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)

						 });
			}
			
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
		    table.getColumnModel().getColumn(1).setPreferredWidth(153);
		    table.getColumnModel().getColumn(2).setPreferredWidth(70);
		    table.getColumnModel().getColumn(3).setPreferredWidth(70);
		   
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
}

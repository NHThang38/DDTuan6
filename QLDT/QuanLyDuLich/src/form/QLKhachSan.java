package form;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import control.control_KS;
import entity.KhachSan;
import entity.Tuor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class QLKhachSan extends JFrame {

	private JPanel contentPane;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtTieuChuan;
	control_KS ds = new control_KS();
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLKhachSan frame = new QLKhachSan();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void SuaTuor() {
		ds.SuaKS(Integer.parseInt(txtMa.getText()), txtTen.getText(),txtDiaChi.getText(),Integer.parseInt(txtTieuChuan.getText()),Integer.parseInt(txtSDT.getText()));
		
	}
	
	public void random() {
		Random ran = new Random();
		int n = ran.nextInt(1000000)+1;
		String val = String.valueOf(n);
		txtMa.setText(val);
	}
private void showdatabase() {
		
		model = new DefaultTableModel();
		model.addColumn("Mã Khách Sạn");
		model.addColumn("Tên Khách Sạn");
		model.addColumn("Địa Chỉ");
		model.addColumn("SĐT");
		model.addColumn("Tiêu Chuẩn");
	
		try {
			List<KhachSan> list = ds.show();
			for (KhachSan ks : list) {
				model.addRow(new Object[] {ks.getMaKS(),ks.getTenKS(),ks.getDiaChi(),ks.getSDT(),ks.getTieuchuan()});
			}
			
		    table.setModel(model);
		    
		    table.getColumnModel().getColumn(0).setPreferredWidth(70);;
		    table.getColumnModel().getColumn(1).setPreferredWidth(153);
		    table.getColumnModel().getColumn(2).setPreferredWidth(150);
		    table.getColumnModel().getColumn(3).setPreferredWidth(100);
		    table.getColumnModel().getColumn(4).setPreferredWidth(100);
		    
		    
		} catch (Exception e) {
			System.out.println("error"+e);
		}
		
	}
	/**
	 * Create the frame.
	 */
	public QLKhachSan() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				showdatabase();
			}
		});
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 641);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Qu\u1EA3n L\u00FD Kh\u00E1ch S\u1EA1n");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(303, 32, 236, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblMaKS = new JLabel("M\u00E3 Kh\u00E1ch S\u1EA1n:");
		lblMaKS.setBounds(43, 113, 95, 16);
		contentPane.add(lblMaKS);
		
		JLabel lblTenKS = new JLabel("T\u00EAn Kh\u00E1ch S\u1EA1n:");
		lblTenKS.setBounds(43, 140, 95, 16);
		contentPane.add(lblTenKS);
		
		JLabel lblDiaChi = new JLabel("\u0110\u1ECBa Ch\u1EC9:");
		lblDiaChi.setBounds(43, 172, 84, 16);
		contentPane.add(lblDiaChi);
		
		JLabel lblSoDienThoai = new JLabel("S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i:");
		lblSoDienThoai.setBounds(407, 105, 84, 16);
		contentPane.add(lblSoDienThoai);
		
		JLabel lblTieuChuan = new JLabel("");
		lblTieuChuan.setBounds(407, 140, 84, 16);
		contentPane.add(lblTieuChuan);
		
		txtMa = new JTextField();
		txtMa.setEditable(false);
		txtMa.setBounds(148, 105, 209, 22);
		contentPane.add(txtMa);
		txtMa.setColumns(10);
		
		txtTen = new JTextField();
		txtTen.setBounds(148, 137, 209, 22);
		contentPane.add(txtTen);
		txtTen.setColumns(10);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(148, 169, 209, 22);
		contentPane.add(txtDiaChi);
		txtDiaChi.setColumns(10);
		
		txtSDT = new JTextField();
		txtSDT.setBounds(516, 102, 215, 22);
		contentPane.add(txtSDT);
		txtSDT.setColumns(10);
		
		txtTieuChuan = new JTextField();
		txtTieuChuan.setBounds(516, 138, 215, 22);
		contentPane.add(txtTieuChuan);
		txtTieuChuan.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ti\u00EAu Chu\u1EA9n:");
		lblNewLabel_4.setBounds(417, 140, 74, 16);
		contentPane.add(lblNewLabel_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(43, 256, 781, 271);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row =table.getSelectedRow();
				
			
				txtMa.setText(table.getValueAt(row,0).toString());
				txtTen.setText(table.getValueAt(row, 1).toString());
				txtDiaChi.setText(table.getValueAt(row, 2).toString());
				txtSDT.setText(table.getValueAt(row, 3).toString());
				txtTieuChuan.setText(table.getValueAt(row, 4).toString());
			
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				random();
				ds.SaveKS(Integer.parseInt(txtMa.getText()),txtTen.getText(), txtDiaChi.getText(),Integer.parseInt(txtTieuChuan.getText()), Integer.parseInt(txtSDT.getText()));
				Object[] rowData = {Integer.parseInt(txtMa.getText()),txtTen.getText(), txtDiaChi.getText(), Integer.parseInt(txtSDT.getText()),Integer.parseInt(txtTieuChuan.getText())+""};
				model.addRow(rowData);
			}
		});
		btnNewButton.setBounds(88, 540, 117, 41);
		contentPane.add(btnNewButton);
		
		JButton btnSa = new JButton("S\u1EEDa ");
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SuaTuor();
				int row = table.getSelectedRow();
				table.setValueAt(txtTen.getText(), row, 1);
				table.setValueAt(txtDiaChi.getText(), row, 2);
				table.setValueAt(txtSDT.getText(), row, 3);
				table.setValueAt(txtTieuChuan.getText(), row,4);
				
			}
		});
		btnSa.setBounds(253, 540, 117, 41);
		contentPane.add(btnSa);
		
		JButton btnXa = new JButton("X\u00F3a");
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int ma = (int) table.getValueAt(row, 0);
				ds.deleteKS(ma);
				model.removeRow(row);
			}
		});
		btnXa.setBounds(422, 540, 117, 41);
		contentPane.add(btnXa);
		
		JButton btnng = new JButton("\u0110\u00F3ng");
		btnng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnng.setBounds(601, 540, 117, 41);
		contentPane.add(btnng);
		
		JLabel lblDanhSachKhach = new JLabel("Danh sách khách sạn");
		lblDanhSachKhach.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDanhSachKhach.setBounds(42, 233, 125, 13);
		contentPane.add(lblDanhSachKhach);
		random();
	}
}

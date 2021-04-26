package entity;

import java.sql.Date;

public class NhanVien {
	private String tenNV, diaChi, email, cmnd;
	Date ngaySinh;
	int maNV;
	private int SDT;
	public NhanVien() {
		// TODO Auto-generated constructor stub
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public int getSDT() {
		return SDT;
	}
	public void setSDT(int sDT) {
		SDT = sDT;
	}
	public NhanVien(String tenNV, int maNV, String diaChi, Date ngaySinh, String email, String cmnd, int sDT) {
		super();
		this.tenNV = tenNV;
		this.maNV = maNV;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.cmnd = cmnd;
		SDT = sDT;
	}
	
	
	

}

package entity;

import java.sql.Date;

public class KhachHang {

	private String tenKH,diaChi, email, cmnd;
	Date ngaySinh;
	 int maKH;
	 private int SDT;
	 
	 public KhachHang() {
		// TODO Auto-generated constructor stub
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}

	public int getSDT() {
		return SDT;
	}

	public void setSDT(int sDT) {
		SDT = sDT;
	}

	public KhachHang(String tenKH, String diaChi, Date ngaySinh, String email, String cmnd, int maKH, int sDT) {
		super();
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.cmnd = cmnd;
		this.maKH = maKH;
		SDT = sDT;
	}


}

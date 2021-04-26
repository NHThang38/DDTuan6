package entity;

import java.sql.Date;

public class HoaDon {
	private int maHoaDon;
	private Date ngayLapHD;
	private int maNV;
	private int maKH;
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public Date getNgayLapHD() {
		return ngayLapHD;
	}
	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}
	public int getMaNV() {
		return maNV;
	}
	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public HoaDon(int maHoaDon, Date ngayLapHD, int maNV, int maKH) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLapHD = ngayLapHD;
		this.maNV = maNV;
		this.maKH = maKH;
	}
}

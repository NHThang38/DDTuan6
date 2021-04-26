package entity;

import java.sql.Date;

import com.toedter.calendar.JDateChooser;

public class Tuor {

	private int maTuor;
	private String tenTuor;
	private int SochoNhan;
	private Date ngayDi;
	private Date ngayKT;
	private String Gio;
	private String tuyenDuong;
	private float gia;
	private int maKS;
	public int getMaTuor() {
		return maTuor;
	}
	public void setMaTuor(int maTuor) {
		this.maTuor = maTuor;
	}
	public String getTenTuor() {
		return tenTuor;
	}
	public void setTenTuor(String tenTuor) {
		this.tenTuor = tenTuor;
	}
	public Date getNgayDi() {
		return ngayDi;
	}
	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}
	public Date getNgayKT() {
		return ngayKT;
	}
	public void setNgayKT(Date ngayKT) {
		this.ngayKT = ngayKT;
	}
	public String getTuyenDuong() {
		return tuyenDuong;
	}
	public void setTuyenDuong(String tuyenDuong) {
		this.tuyenDuong = tuyenDuong;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		this.gia = gia;
	}
	
	public int getSochoNhan() {
		return SochoNhan;
	}
	public void setSochoNhan(int sochoNhan) {
		SochoNhan = sochoNhan;
	}
	

	public String getGio() {
		return Gio;
	}
	public void setGio(String gio) {
		Gio = gio;
	}
	
	
	public int getMaKS() {
		return maKS;
	}
	public void setMaKS(int maKS) {
		this.maKS = maKS;
	}
	
	public Tuor(int maTuor, String tenTuor, int sochoNhan, Date ngayDi, Date ngayKT, String gio, String tuyenDuong,
			float gia, int maKS) {
		super();
		this.maTuor = maTuor;
		this.tenTuor = tenTuor;
		SochoNhan = sochoNhan;
		this.ngayDi = ngayDi;
		this.ngayKT = ngayKT;
		Gio = gio;
		this.tuyenDuong = tuyenDuong;
		this.gia = gia;
		this.maKS = maKS;
	}
	@Override
	public String toString() {
		return "Tuor [maTuor=" + maTuor + ", tenTuor=" + tenTuor + ", ngayDi=" + ngayDi + ", ngayKT=" + ngayKT
				+ ", tuyenDuong=" + tuyenDuong + ", gia=" + gia + "]";
	}
	
	
	
}

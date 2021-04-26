package entity;

public class ChiTietDatTour {
	private int maDatTuor,maTuor;
	private int maKH;
	private int soLuongNguoiLon;
	private int soLuongTreEm;
	private int doTuoi;
	public int getMaDatTuor() {
		return maDatTuor;
	}
	public void setMaDatTuor(int maDatTuor) {
		this.maDatTuor = maDatTuor;
	}
	public int getMaTuor() {
		return maTuor;
	}
	public void setMaTuor(int maTuor) {
		this.maTuor = maTuor;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public int getSoLuongNguoiLon() {
		return soLuongNguoiLon;
	}
	public void setSoLuongNguoiLon(int soLuongNguoiLon) {
		this.soLuongNguoiLon = soLuongNguoiLon;
	}
	public int getSoLuongTreEm() {
		return soLuongTreEm;
	}
	public void setSoLuongTreEm(int soLuongTreEm) {
		this.soLuongTreEm = soLuongTreEm;
	}
	
	public ChiTietDatTour(int maDatTuor, int maTuor, int maKH, int soLuongNguoiLon, int soLuongTreEm) {
		super();
		this.maDatTuor = maDatTuor;
		this.maTuor = maTuor;
		this.maKH = maKH;
		this.soLuongNguoiLon = soLuongNguoiLon;
		this.soLuongTreEm = soLuongTreEm;
		this.doTuoi = doTuoi;
	}

	
	
}

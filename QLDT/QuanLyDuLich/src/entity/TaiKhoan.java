package entity;

public class TaiKhoan {
	String tenDangNhap,matKhau,chucVu;

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public TaiKhoan(String tenDangNhap, String matKhau, String chucVu) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.chucVu = chucVu;
	}

	public TaiKhoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + ", chucVu=" + chucVu + "]";
	}
	
}

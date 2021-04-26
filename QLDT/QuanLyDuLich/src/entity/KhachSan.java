package entity;

public class KhachSan {
	private int maKS;
	private String tenKS,diaChi;
	private int tieuchuan,SDT;
	public int getMaKS() {
		return maKS;
	}
	public void setMaKS(int maKS) {
		this.maKS = maKS;
	}
	public String getTenKS() {
		return tenKS;
	}
	public void setTenKS(String tenKS) {
		this.tenKS = tenKS;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public int getTieuchuan() {
		return tieuchuan;
	}
	public void setTieuchuan(int tieuchuan) {
		this.tieuchuan = tieuchuan;
	}
	public int getSDT() {
		return SDT;
	}
	public void setSDT(int sDT) {
		SDT = sDT;
	}
	public KhachSan(int maKS, String tenKS, String diaChi, int tieuchuan, int sDT) {
		super();
		this.maKS = maKS;
		this.tenKS = tenKS;
		this.diaChi = diaChi;
		this.tieuchuan = tieuchuan;
		SDT = sDT;
	}
	
}

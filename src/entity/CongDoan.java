package entity;

public class CongDoan {
	private String maCD;
	private String tenCD;
	private double luongTheoSanPham;
	private SanPham sp;
	private int thuTu;
	public CongDoan() {
		super();
	}
	public CongDoan(String maCD) {
		super();
		this.maCD = maCD;
	}
	public CongDoan(String maCD, String tenCD, double luongTheoSanPham, SanPham sp, int thuTu) {
		super();
		this.maCD = maCD;
		this.tenCD = tenCD;
		this.luongTheoSanPham = luongTheoSanPham;
		this.sp = sp;
		this.thuTu = thuTu;
	}
	public String getMaCD() {
		return maCD;
	}
	public void setMaCD(String maCD) {
		this.maCD = maCD;
	}
	public String getTenCD() {
		return tenCD;
	}
	public void setTenCD(String tenCD) {
		this.tenCD = tenCD;
	}
	public double getLuongTheoSanPham() {
		return luongTheoSanPham;
	}
	public void setLuongTheoSanPham(double luongTheoSanPham) {
		this.luongTheoSanPham = luongTheoSanPham;
	}
	public SanPham getSp() {
		return sp;
	}
	public void setSp(SanPham sp) {
		this.sp = sp;
	}
	public int getThuTu() {
		return thuTu;
	}
	public void setThuTu(int thuTu) {
		this.thuTu = thuTu;
	}
	@Override
	public String toString() {
		return "CongDoan [maCD=" + maCD + ", tenCD=" + tenCD + ", luongTheoSanPham=" + luongTheoSanPham + ", sp=" + sp
				+ ", thuTu=" + thuTu + "]";
	}
	
}

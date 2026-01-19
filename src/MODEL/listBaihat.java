package MODEL;

import java.sql.Date;

public class listBaihat {
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	private String ten;
	private String imgUrl;
	private Date ngaytao;
	private String idUser;
	public listBaihat(String idList, String ten, String imgUrl, Date ngaytao, String idUser) {
		this.idList = idList;
		this.ten = ten;
		this.imgUrl = imgUrl;
		this.ngaytao = ngaytao;
		this.idUser = idUser;
	}
	public listBaihat()
	{
		
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Date getNgaytao() {
		return ngaytao;
	}
	public void setNgaytao(Date ngaytao) {
		this.ngaytao = ngaytao;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
}

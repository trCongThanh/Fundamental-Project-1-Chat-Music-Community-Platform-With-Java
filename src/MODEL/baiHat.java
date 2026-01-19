package MODEL;

import java.util.ArrayList;

import javafx.scene.image.ImageView;

public class baiHat {
	public static ArrayList<baiHat> beforeSong = new ArrayList<baiHat>();
	private String idBaihat;
	private String tenBaihat;
	private String theLoai;
	private int luotThich;
	private ArrayList<String> casi;
	private String songUrl;
	private String imgUrl;
	
	public baiHat()
	{
		
	}
	public baiHat(String idBaihat, String tenBaihat, String theLoai, int luotThich, ArrayList<String> casi, String songUrl,String imgUrl) {
		this.idBaihat = idBaihat;
		this.tenBaihat = tenBaihat;
		this.theLoai = theLoai;
		this.luotThich = luotThich;
		this.casi = casi;
		this.songUrl = songUrl;
		this.imgUrl = imgUrl;
	}
	public ArrayList<String> getCasi() {
		return casi;
	}
	public void setCasi(ArrayList<String> casi) {
		this.casi = casi;
	}
	public String getSongUrl() {
		return songUrl;
	}
	public void setSongUrl(String songUrl) {
		this.songUrl = songUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getIdBaihat() {
		return idBaihat;
	}
	public void setIdBaihat(String idBaihat) {
		this.idBaihat = idBaihat;
	}
	public String getTenBaihat() {
		return tenBaihat;
	}
	public void setTenBaihat(String tenBaihat) {
		this.tenBaihat = tenBaihat;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public int getLuotThich() {
		return luotThich;
	}
	public void setLuotThich(int luotThich) {
		this.luotThich = luotThich;
	}
}

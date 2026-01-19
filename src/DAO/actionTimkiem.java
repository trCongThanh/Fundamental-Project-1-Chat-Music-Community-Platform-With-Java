package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MODEL.baiHat;
import MODEL.caSi;

public class actionTimkiem {
	private ketnoiSql kn;
	public actionTimkiem() {
		kn.getConnection();
	}
	public ArrayList<baiHat> timkiem(String tenBaihat,String option) 
	{
		ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
		
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		String command;
		if(option.equals("tim")) {command= "select * from baiHat where tenBaihat LIKE N'%"+tenBaihat+"%'";}
		else {command= "select * from baiHat where theLoai LIKE N'%"+tenBaihat+"%'";}
		ResultSet rs = st.executeQuery(command);
		
		while(rs.next())
		{
			baiHat modelBaihat = new baiHat();
			modelBaihat.setIdBaihat(rs.getString(1));
			modelBaihat.setTenBaihat(rs.getNString(2));
			modelBaihat.setTheLoai(rs.getString(3));
			modelBaihat.setLuotThich(rs.getInt(4));
			modelBaihat.setSongUrl(rs.getString(5));
			modelBaihat.setImgUrl(rs.getString(6));
			arrBaihat.add(modelBaihat);
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return arrBaihat;
	}
	public ArrayList<caSi> timkiemCasi(String tencaSi) 
	{
		ArrayList<caSi> arrCasi = new ArrayList<caSi>();
		
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("select * from casi where tencasi LIKE N'%"+tencaSi+"%'");
		
		while(rs.next())
		{
			caSi modelCasi = new caSi();
			modelCasi.setIdCasi(rs.getString(1));
			modelCasi.setTenCasi(rs.getString(2));
			modelCasi.setTieuSu(rs.getString(3));
			modelCasi.setImgA(rs.getString(4));
			modelCasi.setImgBG(rs.getString(5));
			arrCasi.add(modelCasi);
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return arrCasi;
	}
	public caSi timkiemCasiinSong(String tencaSi) 
	{
		
		caSi casi = new caSi();
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("select * from casi where tencasi ='"+tencaSi+"'");
		
		while(rs.next())
		{
			caSi modelCasi = new caSi();
			modelCasi.setIdCasi(rs.getString(1));
			modelCasi.setTenCasi(rs.getString(2));
			modelCasi.setTieuSu(rs.getString(3));
			modelCasi.setImgA(rs.getString(4));
			modelCasi.setImgBG(rs.getString(5));
			casi=modelCasi;
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return casi;
	}
	public ArrayList<baiHat> timkiembaihatTheocasi(String tenCasi) 
	{
		ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
		
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT bh.*\r\n"
				+ "FROM baihat AS bh\r\n"
				+ "INNER JOIN bieudien AS bd ON bh.idbaihat = bd.idbaihat\r\n"
				+ "INNER JOIN casi AS cs ON bd.idcasi = cs.idcasi\r\n"
				+ "WHERE cs.Tencasi LIKE N'%"+tenCasi+"%';");
		
		while(rs.next())
		{
			baiHat modelBaihat = new baiHat();
			modelBaihat.setIdBaihat(rs.getString(1));
			modelBaihat.setTenBaihat(rs.getNString(2));
			modelBaihat.setTheLoai(rs.getString(3));
			modelBaihat.setLuotThich(rs.getInt(4));
			modelBaihat.setSongUrl(rs.getString(5));
			modelBaihat.setImgUrl(rs.getString(6));
			arrBaihat.add(modelBaihat);
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return arrBaihat;
	}
	public ArrayList<caSi> timCasitheoIDbaihat(String id)
	{
		ArrayList<caSi> casi = new ArrayList<caSi>();
		try {
		Connection c = kn.getConnection();
		
		Statement st1 = c.createStatement();
		ResultSet rs1 = st1.executeQuery("SELECT *\r\n"
				+ "FROM casi\r\n"
				+ "INNER JOIN bieudien ON casi.idcasi = bieudien.idcasi\r\n"
				+ "INNER JOIN baihat ON bieudien.idbaihat = baihat.idbaihat\r\n"
				+ "WHERE baihat.idbaihat =" +id+";");
		while(rs1.next())
		{
			caSi casiModel = new caSi();
			casiModel.setIdCasi(rs1.getString(1));
			casiModel.setTenCasi(rs1.getString(2));
			casiModel.setTieuSu(rs1.getString(3));
			casiModel.setImgA(rs1.getString(4));
			casiModel.setImgBG(rs1.getString(5));
			casi.add(casiModel);
		}
	}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return casi;
	}
	public ArrayList<baiHat> minitimkiem(String tenBaihat,String iddanhsach) 
	{
		ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
		
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT *  FROM baihat WHERE LTRIM(RTRIM(tenbaihat)) LIKE N'%"+tenBaihat+"%'  and idbaihat NOT IN ( SELECT idbaihat FROM thembaihat WHERE iddanhsach='"+iddanhsach+"');");
		
		while(rs.next())
		{
			baiHat modelBaihat = new baiHat();
			modelBaihat.setIdBaihat(rs.getString(1));
			modelBaihat.setTenBaihat(rs.getNString(2));
			modelBaihat.setTheLoai(rs.getString(3));
			modelBaihat.setLuotThich(rs.getInt(4));
			modelBaihat.setSongUrl(rs.getString(5));
			modelBaihat.setImgUrl(rs.getString(6));
			arrBaihat.add(modelBaihat);
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return arrBaihat;
	}
}

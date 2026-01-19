package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import MODEL.baiHat;
import MODEL.caSi;

public class actionFavourite {
	private ketnoiSql kn;
	public actionFavourite() {
		kn.getConnection();
	}
	public ArrayList<baiHat> yeuthich(String idUser) 
	{
		int i=1;
		ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
		actionTimkiem actk = new actionTimkiem();
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT bh.*\r\n"
				+ "FROM baihat AS bh\r\n"
				+ "INNER JOIN bieudien AS bd ON bh.idbaihat = bd.idbaihat\r\n"
				+ "INNER JOIN casi AS cs ON bd.idcasi = cs.idcasi\r\n"
				+ "WHERE cs.Tencasi IN (\r\n"
				+ "    SELECT casi.Tencasi \r\n"
				+ "    FROM casi \r\n"
				+ "    INNER JOIN follow ON casi.idcasi = follow.idcasi\r\n"
				+ "    INNER JOIN nguoidung ON follow.iduser = nguoidung.iduser\r\n"
				+ "    WHERE nguoidung.iduser = "+idUser+");");
		
		while(rs.next())
		{
			if(i==4) break;
			baiHat modelBaihat = new baiHat();
			modelBaihat.setIdBaihat(rs.getString(1));
			modelBaihat.setTenBaihat(rs.getNString(2));
			modelBaihat.setTheLoai(rs.getString(3));
			modelBaihat.setLuotThich(rs.getInt(4));
			modelBaihat.setSongUrl(rs.getString(5));
			modelBaihat.setImgUrl(rs.getString(6));
			arrBaihat.add(modelBaihat);
			i++;
		}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		Collections.shuffle(arrBaihat);
		return arrBaihat;
	}
	
}

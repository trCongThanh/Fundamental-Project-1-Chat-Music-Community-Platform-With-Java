package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import MODEL.caSi;

public class actionCasi {
	private ketnoiSql kn;
	public actionCasi() {
		kn.getConnection();
	}
	public void follow(String idCasi, String idUser)
	{
		try {	
			Connection c = kn.getConnection();
			PreparedStatement pre = c.prepareStatement("INSERT INTO follow (idfollow,idCasi,idUser) VALUES (?,?,?);");
			pre.setString(1, null);
			pre.setString(2, idCasi);
			pre.setString(3, idUser);
			int result = pre.executeUpdate();
			kn.close_connect(c);
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
	}
	public void unfollow(String idCasi, String idUser)
	{
		try {	
			Connection c = kn.getConnection();
			 PreparedStatement pre = c.prepareStatement("DELETE FROM follow WHERE idCasi = ? and idUser=?");
			pre.setString(1, idCasi);
			pre.setString(2, idUser);
			int result = pre.executeUpdate();
			kn.close_connect(c);
			}
			catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
	}
	public boolean checkiffollow(String idcasi,String idUser)
	{
		try {
			Connection c = kn.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM follow WHERE idcasi='"+idcasi+"' and iduser='"+idUser+"';");
			if(rs.next()) return true;
			else return false;
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return false;
		}
	}
	public ArrayList<caSi> getrandSinger()
	{
		ArrayList<caSi> arrcs = new ArrayList<caSi>();
		try {
			Connection c = kn.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM casi ORDER BY RAND() LIMIT 4");
			while(rs.next())
			{
				caSi casiModel = new caSi();
				casiModel.setIdCasi(rs.getString(1));
				casiModel.setTenCasi(rs.getString(2));
				casiModel.setTieuSu(rs.getString(3));
				casiModel.setImgA(rs.getString(4));
				casiModel.setImgBG(rs.getString(5));
				arrcs.add(casiModel);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return arrcs;
	}
}

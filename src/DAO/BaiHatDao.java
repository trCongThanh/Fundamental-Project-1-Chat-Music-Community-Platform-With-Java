package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import MODEL.baiHat;
import MODEL.caSi;

public class BaiHatDao {
	public static ArrayList<baiHat> getVpopSongs(){
		//
		ArrayList<String> arrtenCasi = new ArrayList<String>();
		//
		ArrayList<baiHat> list = new ArrayList<baiHat>();
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT baihat.idbaihat,baihat.Tenbaihat, casi.Tencasi, baihat.Theloai,baihat.Luotthich,baihat.songUrl,baihat.imgUrl\r\n"
					+ "FROM bieudien\r\n"
					+ "JOIN baihat ON bieudien.IDbaihat = baihat.IDbaihat\r\n"
					+ "JOIN casi ON bieudien.IDcasi = casi.IDcasi\r\n"
					+ "WHERE baihat.Theloai Like ?");
			preparedStatement.setString(1, "%VPOP%");
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String idBaiHat = result.getString("idbaihat");
				String tenBaiHat = result.getString("Tenbaihat");
				String Tencasi = result.getString("Tencasi");
				String Theloai = result.getString("Theloai");
				int Luotthich = result.getInt("Luotthich");
				String songUrl = result.getString("songUrl");
				String imgUrl = result.getString("imgUrl");
				baiHat baiHat = new baiHat(idBaiHat, tenBaiHat, Theloai, Luotthich, arrtenCasi, songUrl, imgUrl);
				list.add(baiHat);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<baiHat> getKpopSongs(){
		//
		ArrayList<String> arrtenCasi = new ArrayList<String>();
		//
		ArrayList<baiHat> list = new ArrayList<baiHat>();
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT baihat.idbaihat,baihat.Tenbaihat, casi.Tencasi, baihat.Theloai,baihat.Luotthich,baihat.songUrl,baihat.imgUrl\r\n"
					+ "FROM bieudien\r\n"
					+ "JOIN baihat ON bieudien.IDbaihat = baihat.IDbaihat\r\n"
					+ "JOIN casi ON bieudien.IDcasi = casi.IDcasi\r\n"
					+ "WHERE baihat.Theloai like ?");
			preparedStatement.setString(1, "%KPOP%");
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String idBaiHat = result.getString("idbaihat");
				String tenBaiHat = result.getString("Tenbaihat");
				String Tencasi = result.getString("Tencasi");
				String Theloai = result.getString("Theloai");
				int Luotthich = result.getInt("Luotthich");
				String songUrl = result.getString("songUrl");
				String imgUrl = result.getString("imgUrl");
				baiHat baiHat = new baiHat(idBaiHat, tenBaiHat, Theloai, Luotthich, arrtenCasi, songUrl, imgUrl);
				list.add(baiHat);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<baiHat> getUSUKSongs(){
		//
		//
		ArrayList<String> arrtenCasi = new ArrayList<String>();
		//
		
		ArrayList<baiHat> list = new ArrayList<baiHat>();
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT baihat.idbaihat, baihat.Tenbaihat, casi.Tencasi, baihat.Theloai,baihat.Luotthich,baihat.songUrl,baihat.imgUrl\r\n"
					+ "FROM bieudien\r\n"
					+ "JOIN baihat ON bieudien.IDbaihat = baihat.IDbaihat\r\n"
					+ "JOIN casi ON bieudien.IDcasi = casi.IDcasi\r\n"
					+ "WHERE baihat.Theloai like ?");
			preparedStatement.setString(1, "%US-UK%");
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String idBaiHat = result.getString("idbaihat");
				String tenBaiHat = result.getString("Tenbaihat");
				String Tencasi = result.getString("Tencasi");
				String Theloai = result.getString("Theloai");
				int Luotthich = result.getInt("Luotthich");
				String songUrl = result.getString("songUrl");
				String imgUrl = result.getString("imgUrl");
				baiHat baiHat = new baiHat(idBaiHat, tenBaiHat, Theloai, Luotthich, arrtenCasi, songUrl, imgUrl);
				list.add(baiHat);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static ArrayList<baiHat> getJpopSongs(){
		//
		ArrayList<String> arrtenCasi = new ArrayList<String>();
		//
		ArrayList<baiHat> list = new ArrayList<baiHat>();
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT baihat.idbaihat, baihat.Tenbaihat, casi.Tencasi, baihat.Theloai,baihat.Luotthich,baihat.songUrl,baihat.imgUrl\r\n"
					+ "FROM bieudien\r\n"
					+ "JOIN baihat ON bieudien.IDbaihat = baihat.IDbaihat\r\n"
					+ "JOIN casi ON bieudien.IDcasi = casi.IDcasi\r\n"
					+ "WHERE baihat.Theloai like ?");
			preparedStatement.setString(1, "%JPOP%");
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String idBaiHat = result.getString("idbaihat");
				String tenBaiHat = result.getString("Tenbaihat");
				String Tencasi = result.getString("Tencasi");
				String Theloai = result.getString("Theloai");
				int Luotthich = result.getInt("Luotthich");
				String songUrl = result.getString("songUrl");
				String imgUrl = result.getString("imgUrl");
				baiHat baiHat = new baiHat(idBaiHat, tenBaiHat, Theloai, Luotthich, arrtenCasi, songUrl, imgUrl);
				list.add(baiHat);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	public static ArrayList<baiHat> getallmusic()
	{
		ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
		try {
			Connection c = ketnoiSql.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM baihat\r\n"
					+ "WHERE theloai LIKE '%VPop%'\r\n"
					+ "   OR theloai LIKE '%KPop%'\r\n"
					+ "   OR theloai LIKE '%JPop%'\r\n"
					+ "   OR theloai LIKE '%US-UK%';");
			
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
		Collections.shuffle(arrBaihat);
		return arrBaihat;
	}
}

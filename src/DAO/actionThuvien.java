package DAO;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import MODEL.baiHat;
import MODEL.listBaihat;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class actionThuvien {
	private ketnoiSql kn;
	public actionThuvien() {
		kn.getConnection();
	}
	public boolean checkPlaylist(String idUser)
	{
		try {
			Connection c = kn.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM listbaihat WHERE iduser="+idUser+";");
			if(rs.next()) return true;
			else return false;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public void createPlaylist(String iddanhsach,String ten,String imgUrl,Date ngaytao,String iduser)
	{
		try {	
		Connection c = kn.getConnection();
		PreparedStatement pre = c.prepareStatement("INSERT INTO listbaihat (iddanhsach,tendanhsach, imgUrl,ngaytao,iduser) VALUES (?,?,?,?,?);");
		pre.setString(1, iddanhsach);
		pre.setString(2, ten);
		pre.setString(3, null);
		pre.setDate(4, ngaytao);
		pre.setString(5, iduser);
		int result = pre.executeUpdate();
		kn.close_connect(c);
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	public void deletePlaylist(String iddanhsach) {
	    try {    
	        Connection c = kn.getConnection();
	        
	        // Xóa khỏi bảng listbaihat
	        PreparedStatement pre1 = c.prepareStatement("DELETE FROM listbaihat WHERE iddanhsach = ?");
	        pre1.setString(1, iddanhsach);
	        int result1 = pre1.executeUpdate();
	        pre1.close(); // Đóng PreparedStatement sau khi sử dụng
	        
	        // Xóa khỏi bảng thembaihat
	        PreparedStatement pre2 = c.prepareStatement("DELETE FROM thembaihat WHERE iddanhsach = ?");
	        pre2.setString(1, iddanhsach);
	        int result2 = pre2.executeUpdate();
	        pre2.close(); // Đóng PreparedStatement sau khi sử dụng
	        
	        kn.close_connect(c); // Đóng kết nối
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Xử lý ngoại lệ nếu có
	    }
	}
	public ArrayList<listBaihat> layDanhsach(String idUser)
	{
		ArrayList<listBaihat> arrlistBaihat = new ArrayList<listBaihat>();
		
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("select * from listbaihat where iduser="+idUser+";");
		while(rs.next())
		{
			listBaihat modellist = new listBaihat();
			modellist.setIdList(rs.getString(1));
			modellist.setTen(rs.getString(2));
			modellist.setImgUrl(rs.getString(3));
			modellist.setNgaytao(rs.getDate(4));
			modellist.setIdUser(rs.getString(5));
			arrlistBaihat.add(modellist);
		}
		
			}
		catch (Exception e) {
			// TODO: handle exception
		}
		return arrlistBaihat;
	}
	public void addtoList(String iddanhsach,String idbaihat)
	{
		try {	
		Connection c = kn.getConnection();
		PreparedStatement pre = c.prepareStatement("INSERT INTO thembaihat (idthem, iddanhsach, idbaihat) VALUES (?,?,?);");
		pre.setString(1, null);
		pre.setString(2, iddanhsach);
		pre.setString(3, idbaihat);
		int result = pre.executeUpdate();
		kn.close_connect(c);
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
		public ArrayList<baiHat> Musicinlist(String idlist) 
		{
			ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
			
			try {
			Connection c = kn.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT b.* FROM thembaihat t INNER JOIN baihat b ON t.IDbaihat=b.IDbaihat WHERE iddanhsach='"+idlist+"'");
			
			while(rs.next())
			{
				ArrayList<String> tencasi = new ArrayList<String>();
				Statement st1 = c.createStatement();
				ResultSet rs1 = st1.executeQuery("SELECT casi.Tencasi\r\n"
						+ "FROM casi\r\n"
						+ "INNER JOIN bieudien ON casi.idcasi = bieudien.idcasi\r\n"
						+ "INNER JOIN baihat ON bieudien.idbaihat = baihat.idbaihat\r\n"
						+ "WHERE baihat.idbaihat =" +rs.getString(1)+";");
				while(rs1.next())
				{
					tencasi.add(rs1.getString(1));
				}
				st1.close();
				rs1.close();
				baiHat modelBaihat = new baiHat();
				modelBaihat.setIdBaihat(rs.getString(1));
				modelBaihat.setTenBaihat(rs.getString(2));
				modelBaihat.setTheLoai(rs.getString(3));
				modelBaihat.setLuotThich(rs.getInt(4));
				modelBaihat.setCasi(tencasi);
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
		public boolean isPlaylisthavethisSong(String idplaylist,String idBaihat)
		{
			boolean check = false;
			try {
				Connection c = kn.getConnection();
				Statement st = c.createStatement();
				ResultSet rs = st.executeQuery("select idBaihat from thembaihat where iddanhsach='"+idplaylist+"';");
				while(rs.next() && idBaihat.equals(rs.getString(1)))
				{
					check=true;
				}
				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return check;
		}
		public void updateImglist(String imglist,String idlist)
		{
			try {
			Connection c = kn.getConnection();
			System.out.println("ok dang doi");
			PreparedStatement pre = c.prepareStatement("UPDATE listbaihat set imgurl = ? where iddanhsach=?");
			pre.setString(1, imglist);
			pre.setString(2,idlist);
			int result = pre.executeUpdate();
			System.out.println("ok da doi"+ result);
			kn.close_connect(c);}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}

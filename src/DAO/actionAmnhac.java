package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import MODEL.baiHat;

public class actionAmnhac {
	private ketnoiSql kn;
	public actionAmnhac() {
		kn.getConnection();
	}
	public ArrayList<baiHat> getsongLike(String idBaihat) 
	{
		ArrayList<baiHat> arrBaihat = new ArrayList<baiHat>();
		
		try {
		Connection c = kn.getConnection();
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("select * from baiHat where idbaihat!='"+idBaihat+"' ORDER BY RAND() LIMIT 4;");
		
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
	public void removesongFromlist(String iddanhsach, String idbaihat)
	{
		 try {    
		        Connection c = kn.getConnection();
		        
		        // Xóa khỏi bảng listbaihat
		        PreparedStatement pre = c.prepareStatement("DELETE FROM thembaihat WHERE iddanhsach = ? and idbaihat=?");
		        pre.setString(1, iddanhsach);
		        pre.setString(2, idbaihat);
		        int result1 = pre.executeUpdate();
		        pre.close(); // Đóng PreparedStatement sau khi sử dụng
		        
		        
		        kn.close_connect(c); // Đóng kết nối
		    } catch (Exception e) {
		        e.printStackTrace();
		        // Xử lý ngoại lệ nếu có
		    }
	}
}

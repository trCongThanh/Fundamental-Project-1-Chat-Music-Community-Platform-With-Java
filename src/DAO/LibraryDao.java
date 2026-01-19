package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import MODEL.listBaihat;

public class LibraryDao {
	public static ArrayList<listBaihat> ListUser(int idUser){
		ArrayList<listBaihat> list = new ArrayList<listBaihat>();
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT listbaihat.IDdanhsach,listbaihat.Tendanhsach,listbaihat.imgUrl,listbaihat.Ngaytao,listbaihat.IDuser\r\n"
					+ "FROM listbaihat\r\n"
					+ "JOIN nguoidung ON listbaihat.IDuser = nguoidung.IDuser\r\n"
					+ "WHERE nguoidung.IDuser = ?");
			preparedStatement.setInt(1, idUser);
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String IDdanhsach = result.getString("IDdanhsach");
				String Tendanhsach = result.getString("Tendanhsach");
				String imgUrl = result.getString("imgUrl");
				Date Ngaytao = result.getDate("Ngaytao");
				int IDuser = result.getInt("IDuser");
				listBaihat listBaihat = new listBaihat(IDdanhsach, Tendanhsach, imgUrl, Ngaytao, IDuser+"");
				list.add(listBaihat);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			
		}
		return list;
	}
}

package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import MODEL.baiHat;
import MODEL.caSi;
import MODEL.nguoiDung;

public class UserDao {
	public static nguoiDung login(String Username, String Password) {
		nguoiDung nguoiDung = null;
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT Username,Password,Avatar FROM nguoidung WHERE Username LIKE ? AND Password LIKE ?");
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, Password);
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String userName = result.getString("Username");
				String passWord = result.getString("Password");
				String path = result.getString("Avatar");
				nguoiDung = new nguoiDung(0, userName, Password, null, null, path);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nguoiDung;
	}
	
	public static void SignUp(String UserName, String Password,LocalDate BirthDay,String Gender) {
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO nguoidung(Username,Password,BirthDay,Gender) VALUES(?,?,?,?)");
			preparedStatement.setString(1, UserName);
			preparedStatement.setString(2, Password);
			preparedStatement.setObject(3, BirthDay);
			preparedStatement.setString(4, Gender);
			// 3
			int result = preparedStatement.executeUpdate();
			// 4
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static int CheckUserName(String UserName) {
		int check = 0;
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM nguoidung WHERE Username LIKE ?");
			preparedStatement.setString(1, UserName);
			// 3
			ResultSet resultSet = preparedStatement.executeQuery();
			// 4
			while(resultSet.next()) {
				check = resultSet.getInt("COUNT(*)");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	public static nguoiDung GetDataUser(String userName) {
		nguoiDung nguoiDung = null;
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT Username,Password,BirthDay,Gender,IDuser FROM nguoidung WHERE Username LIKE ?");
			preparedStatement.setString(1, userName);
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				String UserName = result.getString("Username");
				String Password = result.getString("Password");
				Date BirthDay = result.getDate("BirthDay");
				String Gender = result.getString("Gender");
				int IDuser = result.getInt("IDuser");
				nguoiDung = new nguoiDung(IDuser, UserName, Password, BirthDay, Gender, null);
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
		return nguoiDung;
	}
	
	public static void UpdateInformation(String username, String password, LocalDate birthday, String Gender,String oldUsername) {
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE nguoidung SET Username = ?, Password = ?, BirthDay = ?, Gender = ? WHERE Username LIKE ?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setObject(3, birthday);
			preparedStatement.setString(4, Gender);
			preparedStatement.setString(5, oldUsername);
			// 4
			int result = preparedStatement.executeUpdate();
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void UpdateAvatar(String path,String Username) {
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE nguoidung SET Avatar = ? WHERE Username LIKE ?");
			preparedStatement.setString(1, path);
			preparedStatement.setString(2, Username);
			// 3
			int result = preparedStatement.executeUpdate();
			// 4
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void refreshDataUser() {
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE from datauser WHERE Username LIKE (SELECT Username FROM datauser) or ImageUrl LIKE (SELECT ImageUrl FROM datauser)");
			// 3
			int result = preparedStatement.executeUpdate();
			// 4
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void AddDataUser(String Username,String path) {
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO datauser(Username,ImageUrl) VALUES (?,?)");
			preparedStatement.setString(1, Username);
			preparedStatement.setString(2, path);
			// 3
			int result = preparedStatement.executeUpdate();
			// 4
			ketnoiSql.close_connect(connection);;
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void AddURLUser(String path) {
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO datauser(ImageUrl) VALUES(?)");
			preparedStatement.setString(1, path);
			// 3
			int result = preparedStatement.executeUpdate();
			// 4
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getUserNameTemp() {
		String Username = "";
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT Username FROM datauser");
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				Username = result.getString("Username");
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return Username;
	}
	
	public static String getURLUserTemp() {
		String path = "";
		try {
			// 1
			Connection connection = ketnoiSql.getConnection();
			// 2
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT ImageUrl FROM datauser");
			// 3
			ResultSet result = preparedStatement.executeQuery();
			// 4
			while(result.next()) {
				 path = result.getString("ImageUrl");
			}
			// 5
			ketnoiSql.close_connect(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	public String getIduser()
	{
		String id="";
		try {
			Connection c = ketnoiSql.getConnection();
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select idUser from nguoidung where username=(select username from datauser)");
			
			if(rs.next())
			{
				id=rs.getString(1);
			}
				}
			catch (Exception e) {
				e.printStackTrace();
			}
		return id;
	}
	
}

package MODEL;

import java.sql.Date;
import java.util.ArrayList;

public class nguoiDung {
	private int IDuser;
	private String Username;
	private String Password;
	private Date BirthDay;
	private String Gender;
	private String Avatar;
	public nguoiDung() {
	
	}
	public nguoiDung(int iDuser, String username, String password, Date birthDay, String gender, String avatar) {
		IDuser = iDuser;
		Username = username;
		Password = password;
		BirthDay = birthDay;
		Gender = gender;
		Avatar = avatar;
	}
	public int getIDuser() {
		return IDuser;
	}
	public void setIDuser(int iDuser) {
		IDuser = iDuser;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Date getBirthDay() {
		return BirthDay;
	}
	public void setBirthDay(Date birthDay) {
		BirthDay = birthDay;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getAvatar() {
		return Avatar;
	}
	public void setAvatar(String avatar) {
		Avatar = avatar;
	}
	@Override
	public String toString() {
		return "nguoiDung [IDuser=" + IDuser + ", Username=" + Username + ", Password=" + Password + ", BirthDay="
				+ BirthDay + ", Gender=" + Gender + ", Avatar=" + Avatar + "]";
	}
	
}

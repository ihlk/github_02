package entity;
//�û���
public class Users {
	
	private int uid;//�û�ID
	
	private String username;//�û���
	private String password;//�û�����
	
	public Users() {
		
	}
	public Users(int uid, String username, String password) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
	}
	
	public int getUid() {
		return uid;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


	
}

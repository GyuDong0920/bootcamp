package chTest;

public class Login {

	private final int id;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;

	public Login(int id, String username, String password, String phoneNumber, String address) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public String toString() {
		return "회원정보 [ 회원번호: " + id + ", 아이디: " + username + ", 전화번호: " + phoneNumber + ", 주소: " + address + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Login) {
			Login target = (Login) obj;
			return this.id == target.getId() ? true : false;
		}
		return false;
	}
	
	public boolean checkUserInfo(String username, String password) {
		return this.username.equals(username) && this.password.equals(password) ? true : false;
	}

}
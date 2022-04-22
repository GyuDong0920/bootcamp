package ch05;

public class UserInfomysqlDao implements UserInfoDao {

	@Override
	public void insertUserInfo(UserInfo info) {
		System.out.println("MYSQL : 저장하기 " + info.getUserId());
		System.out.println("MYSQL : 저장하기 " + info.getPassword());
		System.out.println("MYSQL : 저장하기 " + info.getUserName());
		
	}

	@Override
	public void updateUserInfo(UserInfo info) {
		System.out.println("MYSQL : 수정하기 id " + info.getUserId());
		System.out.println("MYSQL : 수정하기 pw " + info.getPassword());
		System.out.println("MYSQL : 수정하기 name " + info.getUserName());
	}

	@Override
	public void deleteUserInfo(String userId) {
		System.out.println("delete from dbName userId = ' " + userId + "'");
		
	}

	
}

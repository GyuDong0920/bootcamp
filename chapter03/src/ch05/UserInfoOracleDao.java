package ch05;

public class UserInfoOracleDao implements UserInfoDao {

	@Override
	public void insertUserInfo(UserInfo info) {
		System.out.println("Oracle : 저장하기 " + info.getUserId());
		System.out.println("Oracle : 저장하기 " + info.getPassword());
		System.out.println("Oracle : 저장하기 " + info.getUserName());
		
	}

	@Override
	public void updateUserInfo(UserInfo info) {
		System.out.println("Oracle : 수정하기 " + info.getUserId());
		System.out.println("Oracle : 수정하기 " + info.getPassword());
		System.out.println("Oracle : 수정하기 " + info.getUserName());
	}

	@Override
	public void deleteUserInfo(String userId) {
		System.out.println("oracle : 동작");
		System.out.println("delete from dbname userId = '" + userId);
	}

}

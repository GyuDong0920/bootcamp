package ch01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {
	private Connection conn; // db 커넥션 연결 객체
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "asd123";
	private static final String URL = "jdbc:mysql://localhost:3306/showdb?serverTimezone=Asia/Seoul&characterEncoding=UTF-8";

	private Statement stmt; // String --> 쿼리구문으로 변경해주는 녀석
	private ResultSet rs; // 결과값을 받아주는 친구

	public MainTest() {
		// 첫번쨰
		try {
			// reflect 기법 : 컴파일 시점 문자열 --> 런타입 시점에 실제 클래스가 존재하는 지 확인
			// 메모리( heap ) 영역에 올라 간다.

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				stmt = conn.createStatement();

				String sql1 = "select * from membertbl";
				rs = stmt.executeQuery(sql1);

				while (rs.next()) {
					String memberId = rs.getString("memberId");
					String memberName = rs.getString("memberName");
					System.out.println("id: " + memberId + " , " + "name: " + memberName);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		
		
		
	}

	// 코드의 시작점
	public static void main(String[] args) {
		new MainTest();
	} // end of main

} // end of class
package ch03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.ToString;
@ToString
public class showdbDao implements IShowdbDao{

	
	private DBClient client;
	private Connection connection;
	ResultSet resultSet = null;
	
	PreparedStatement preparedStatement;
	
	
	public showdbDao() {
		client = DBClient.getInstance();
		connection = client.getConnection();
		//innerjoin1(null);
	}
	
	@Override
	public ArrayList<BuyDto> innerjoin1(String userName1, String userName2) {
		
		ArrayList<BuyDto> buylist = new ArrayList<>();
		
		
		try {
			String innerjoin = "SELECT u.userName, u.addr, b.prodName, b.price FROM usertbl as U  INNER JOIN buytbl as B ON u.userName = b.userName WHERE u.userName = ? or ?";
			preparedStatement = connection.prepareStatement(innerjoin);
			preparedStatement.setString(1, userName1);
			preparedStatement.setString(2, userName2);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				BuyDto buyDto = new BuyDto();
				buyDto.setUserName(resultSet.getString("userName"));
				buyDto.setAddr(resultSet.getString("addr"));
				buyDto.setProdName(resultSet.getString("prodNAme"));
				buyDto.setPrice(resultSet.getInt("price"));
				
				buylist.add(buyDto);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return buylist;

	
	}
	@Override
	public BuyDto buyInfoByUserName(String userName) {
		 
		
		try {
			String buytbl = "SELECT u.userName, b.prodName, b.price FROM buytbl as B LEFT JOIN usertbl as U ON b.userName = u.userName WHERE b.price = (SELECT MAX(price) FROM buytbl WHERE userName = ?)";
			preparedStatement = connection.prepareStatement(buytbl);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			
			BuyDto bdto = new BuyDto();
			resultSet.next();
			bdto.setUserName(resultSet.getString("userName"));
			bdto.setProdName(resultSet.getString("prodName"));
			bdto.setPrice(resultSet.getInt("price"));
			
			//System.out.println(bdto);
			
			return bdto;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
		
		
	}
	
	@Override
	public void leftjoin1(userDto dto) {
		// TODO Auto-generated method stub
		
	}
		public static void main(String[] args) {
		 showdbDao dao = new showdbDao();
		 System.out.println(dao.buyInfoByUserName("이순신"));
		 // 스캐너 
		 // swing 
		 // 웹 브라우저 
		 // 모바일 
			
			 ArrayList<BuyDto> buylist = dao.innerjoin1("이순신", "김유신");
			 System.out.println("buylist :" + buylist);
			 
		 
		 
			
		}

	

		
	

	
} // end of class 

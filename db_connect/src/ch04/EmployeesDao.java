package ch04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ch03.DBClient;

public class EmployeesDao implements Iemployees {

	private DBClient client;
	private Connection connection;
	ResultSet resultset = null;
	
	PreparedStatement preparableStatement;
	
	public EmployeesDao() {
		client = DBClient.getInstance();
		connection = client.getConnection();
	}
	@Override
	public ArrayList<EmployeesDto> leftjoin1(String to_date) {
		
		ArrayList<EmployeesDto> leftjoin1 = new ArrayList<>();
		
		
		try {
			String left1 = "SELECT a.emp_no, b.dept_name, a.to_date FROM dept_emp as a LEFT JOIN departments as b ON a.dept_no = b.dept_no WHRER a.to_date = ? limit 20";
			preparableStatement = connection.prepareStatement(left1);
			preparableStatement.setString(1, to_date);
			resultset = preparableStatement.executeQuery();
			
			while(resultset.next()) {
				
				EmployeesDto dto = new EmployeesDto();
				dto.setEmp_no(resultset.getInt("emp_no"));
				dto.setDept_name(resultset.getString("dept_name"));
				dto.setTo_date(resultset.getString("to_date"));
				
				leftjoin1.add(dto);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		return leftjoin1;
	}

	@Override
	public ArrayList<EmployeesDto> innerjoin2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> innerjoin3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> leftjoin4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> innerjoin5() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> where1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> from2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> select3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> where4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EmployeesDto> from5() {
		// TODO Auto-generated method stub
		return null;
	}
public static void main(String[] args) {
	EmployeesDao dao = new EmployeesDao();
	ArrayList<EmployeesDto> leftjoin1 = dao.leftjoin1("9999-01-01");
	System.out.println("leftjoin1 :" + leftjoin1);
}
}

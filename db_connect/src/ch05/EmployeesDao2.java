package ch05;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Client;

import lombok.NonNull;

public class EmployeesDao2 implements Iemployees2 {


	private DBClient2 client2;
	private Connection connection;
	ResultSet resultSet = null;

	PreparedStatement preparedStatement;

	public EmployeesDao2() {
		client2 = DBClient2.getInstance("employees");
		connection = client2.getConnection();
	}

	@Override
	public EmployeesDto2 maxSalary() {

		try {
			String maxsalary = "SELECT * FROM employees as e WHERE e.emp_no IN (SELECT emp_no FROM salaries as s WHERE salary = (SELECT MAX(salary) FROM salaries))";
			preparedStatement = connection.prepareStatement(maxsalary);
			resultSet = preparedStatement.executeQuery();

			EmployeesDto2 dto2 = new EmployeesDto2();
			resultSet.next();
			dto2.setEmp_no(resultSet.getInt("emp_no"));
			dto2.setBirth_date(resultSet.getString("birth_date"));
			dto2.setFirst_name(resultSet.getString("first_name"));
			dto2.setLast_name(resultSet.getString("last_name"));
			dto2.setGender(resultSet.getString("gender"));
			dto2.setHire_date(resultSet.getString("hire_date"));

			return dto2;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public EmployeesDto2 maxSalaryInline() {

		try {
			String maxsalaryinline = "SELECT * FROM employees as e, (SELECT * FROM salaries WHERE salary = (SELECT MAX(salary) FROM salaries)) as s WHERE e.emp_no = s.emp_no";
			preparedStatement = connection.prepareStatement(maxsalaryinline);
			resultSet = preparedStatement.executeQuery();

			EmployeesDto2 dto2 = new EmployeesDto2();
			resultSet.next();
			dto2.setEmp_no(resultSet.getInt("emp_no"));
			dto2.setBirth_date(resultSet.getString("birth_date"));
			dto2.setFirst_name(resultSet.getString("first_name"));
			dto2.setLast_name(resultSet.getString("last_name"));
			dto2.setGender(resultSet.getString("gender"));
			dto2.setHire_date(resultSet.getString("hire_date"));
			dto2.setSalary(resultSet.getInt("salary"));

			return dto2;

		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override
	public ArrayList<EmployeesDto2> firstSalary() {

		ArrayList<EmployeesDto2> dto2s = new ArrayList<>();

		try {
			String firstsalary = "SELECT *,(SELECT s.salary FROM salaries as s WHERE e.emp_no = s.emp_no GROUP BY emp_no) as salary FROM employees as e LIMIT 20";
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				EmployeesDto2 dto2 = new EmployeesDto2();
				dto2.setEmp_no(resultSet.getInt("emp_no"));
				dto2.setBirth_date(resultSet.getString("birth_date"));
				dto2.setFirst_name(resultSet.getString("first_name"));
				dto2.setLast_name(resultSet.getString("last_name"));
				dto2.setGender(resultSet.getString("gender"));
				dto2.setHire_date(resultSet.getString("hire_date"));
				dto2.setSalary(resultSet.getInt("salary"));

				dto2s.add(dto2);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto2s;

	}

	@Override
	public ArrayList<EmployeesDto2> seniorEngineer(String title) {
	
		ArrayList<EmployeesDto2> dto2s = new ArrayList<>();
		
		String seniorEngineer = "SELECT * FROM employees WHERE emp_no IN (SELECT emp_no FROM titles WHERE title = ?) LIMIT 20";
				
//				select *
//				from employees
//				where emp_no in ( select emp_no
//									from titles
//										where title = 'Senior Engineer')
//				limit 20;
		return dto2s;
	}

	@Override
	public void salary80000() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		EmployeesDao2 dao2 = new EmployeesDao2();
		System.out.println(dao2.maxSalary());
		System.out.println("========================================");
		System.out.println(dao2.maxSalaryInline());
		System.out.println("========================================");
		System.out.println(dao2.firstSalary());

	}

}

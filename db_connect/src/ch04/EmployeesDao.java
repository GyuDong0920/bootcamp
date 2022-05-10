package ch04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeesDao implements Iemployees {

	private DBClient2 client;
	private Connection connection;
	ResultSet resultset = null;

	PreparedStatement preparableStatement;

	public EmployeesDao() {
		client = DBClient2.getInstance("employees");
		connection = client.getConnection();
	}

	@Override
	public ArrayList<EmployeesDto> workingDept(String to_date) {

		// 1. 자료구조 정의
		ArrayList<EmployeesDto> workingdept = new ArrayList<>();

		try {
			String workdept = "SELECT a.emp_no, b.dept_name, a.to_date FROM dept_emp as a LEFT JOIN departments as b ON a.dept_no = b.dept_no WHERE a.to_date = ? limit 20";
			preparableStatement = connection.prepareStatement(workdept);
			preparableStatement.setString(1, to_date);
			resultset = preparableStatement.executeQuery();

			while (resultset.next()) {

				EmployeesDto dto = new EmployeesDto();
				dto.setEmp_no(resultset.getInt("emp_no"));
				dto.setDept_name(resultset.getString("dept_name"));
				dto.setTo_date(resultset.getString("to_date"));

				workingdept.add(dto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workingdept;
	}

	@Override
	public ArrayList<EmployeesDto> salary80000(int salary, String to_date) {

		ArrayList<EmployeesDto> salary80000 = new ArrayList<>();

		try {
			String salarys = "SELECT e.emp_no, e.first_name, s.salary FROM employees as e INNER JOIN salaries as s ON e.emp_no = s.emp_no WHERE s.salary >= ? AND s.to_date = ? limit 20";
			preparableStatement = connection.prepareStatement(salarys);
			preparableStatement.setInt(1, salary);
			preparableStatement.setString(2, to_date);
			resultset = preparableStatement.executeQuery();

			while (resultset.next()) {

				EmployeesDto dto = new EmployeesDto();
				dto.setEmp_no(resultset.getInt("emp_no"));
				dto.setFirst_name(resultset.getString("first_name"));
				dto.setSalary(resultset.getInt("salary"));

				salary80000.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return salary80000;
	}

	@Override
	public ArrayList<EmployeesDto> deptManager(String to_date) {

		ArrayList<EmployeesDto> deptmanager = new ArrayList<>();

		try {
			String dmanager = "SELECT e.emp_no, e.first_name, d.dept_no FROM employees as e INNER JOIN dept_manager as d ON e.emp_no = d.emp_no WHERE d.to_date = ? LIMIT 20";
			preparableStatement = connection.prepareStatement(dmanager);
			preparableStatement.setString(1, to_date);
			resultset = preparableStatement.executeQuery();

			while (resultset.next()) {

				EmployeesDto dto = new EmployeesDto();
				dto.setEmp_no(resultset.getInt("emp_no"));
				dto.setFirst_name(resultset.getString("first_name"));
				dto.setDept_no(resultset.getString("dept_no"));

				deptmanager.add(dto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return deptmanager;
	}

	@Override
	public ArrayList<EmployeesDto> titleSalary(String to_date) {

		ArrayList<EmployeesDto> titlesalary = new ArrayList<>();

		try {
			String titlesalarys = "SELECT t.emp_no, t.title, s.salary, s.to_date FROM titles as t LEFT JOIN salaries as s ON t.emp_no = s.emp_no WHERE t.to_date = ? AND s.to_date = ? LIMIT 20";
			preparableStatement = connection.prepareStatement(titlesalarys);
			preparableStatement.setString(1, to_date);
			preparableStatement.setString(2, to_date);
			resultset = preparableStatement.executeQuery();

			while (resultset.next()) {

				EmployeesDto dto = new EmployeesDto();
				dto.setEmp_no(resultset.getInt("emp_no"));
				dto.setTitle(resultset.getString("title"));
				dto.setSalary(resultset.getInt("salary"));
				dto.setTo_date(resultset.getString("to_date"));

				titlesalary.add(dto);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return titlesalary;
	}

	@Override
	public ArrayList<EmployeesDto> genderManager(String to_date) {

		ArrayList<EmployeesDto> genderManager = new ArrayList<>();

		try {
			String gendermanager = "SELECT e.emp_no, e.first_name, e.gender, d.to_date FROM	employees as e INNER JOIN dept_manager as d ON e.emp_no = d.emp_no WHERE d.to_date = ?";
			preparableStatement = connection.prepareStatement(gendermanager);
			preparableStatement.setString(1, to_date);
			resultset = preparableStatement.executeQuery();

			while (resultset.next()) {

				EmployeesDto dto = new EmployeesDto();
				dto.setEmp_no(resultset.getInt("emp_no"));
				dto.setFirst_name(resultset.getString("first_name"));
				dto.setGender(resultset.getString("gender"));
				dto.setTo_date(resultset.getString("to_date"));

				genderManager.add(dto);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return genderManager;
	}

	public static void main(String[] args) {

		EmployeesDao dao = new EmployeesDao();
		ArrayList<EmployeesDto> workingdept = dao.workingDept("9999-01-01");
		System.out.println("workingdept : " + workingdept);
		System.out.println("==================================================================");
		ArrayList<EmployeesDto> salary80000 = dao.salary80000(80000, "9999-01-01");
		System.out.println("salary>=80000 : " + salary80000);
		System.out.println("==================================================================");
		ArrayList<EmployeesDto> deptmanager = dao.deptManager("9999-01-01");
		System.out.println("deptmanager : " + deptmanager);
		System.out.println("==================================================================");
		ArrayList<EmployeesDto> titlesalary = dao.titleSalary("9999-01-01");
		System.out.println("titlesalary : " + titlesalary);
		System.out.println("==================================================================");
		ArrayList<EmployeesDto> genderManager = dao.genderManager("9999-01-01");
		System.out.println("genderManager : " + genderManager);
	}

}

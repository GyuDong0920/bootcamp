package ch04;

import java.util.ArrayList;

public interface Iemployees {

	ArrayList<EmployeesDto> workingDept(String to_date);
	ArrayList<EmployeesDto> salary80000(int salary, String to_date);
	ArrayList<EmployeesDto> deptManager(String to_date);
	ArrayList<EmployeesDto> titleSalary(String to_date);
	ArrayList<EmployeesDto> genderManager(String to_date);
	
	
	
}

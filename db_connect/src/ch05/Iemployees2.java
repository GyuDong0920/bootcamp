package ch05;

import java.util.ArrayList;

public interface Iemployees2 {
	EmployeesDto2 maxSalary();
	EmployeesDto2 maxSalaryInline();
	ArrayList<EmployeesDto2> firstSalary();
	ArrayList<EmployeesDto2> seniorEngineer(String title);
	void salary80000();
}

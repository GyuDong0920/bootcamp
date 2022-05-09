package ch04;

import java.util.ArrayList;

public interface Iemployees {

	ArrayList<EmployeesDto> leftjoin1(String to_date);
	ArrayList<EmployeesDto> innerjoin2();
	ArrayList<EmployeesDto> innerjoin3();
	ArrayList<EmployeesDto> leftjoin4();
	ArrayList<EmployeesDto> innerjoin5();
	
	ArrayList<EmployeesDto> where1();
	ArrayList<EmployeesDto> from2();
	ArrayList<EmployeesDto> select3();
	ArrayList<EmployeesDto> where4();
	ArrayList<EmployeesDto> from5();
	
	
}

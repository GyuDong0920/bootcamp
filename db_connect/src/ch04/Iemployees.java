package ch04;

import java.util.ArrayList;
 
public interface Iemployees {
	// 현재 근무하고있는 사람들의 부서명까지 출력
	ArrayList<EmployeesDto> workingDept(String to_date);
	
	// 연봉을 80000 이상 받는 사람의 이름과 최근 연봉을 출력
	ArrayList<EmployeesDto> salary80000(int salary, String to_date);
	
	// 현재 각 부서의 매니저이름을 출력
	ArrayList<EmployeesDto> deptManager(String to_date);
	
	// title 별 연봉 출력
	ArrayList<EmployeesDto> titleSalary(String to_date);
	
	// 현재 매니저들의 성별을 출력
	ArrayList<EmployeesDto> genderManager(String to_date);
	
	
	
}

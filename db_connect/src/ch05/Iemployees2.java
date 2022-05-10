package ch05;

import java.util.ArrayList;
 
public interface Iemployees2 {
	// 최고연봉을 받는 사람의 정보를 출력해주세요
	EmployeesDto2 maxSalary();
	
	// 위의 문제를 인라인뷰로 만들기
	EmployeesDto2 maxSalaryInline();
	
	// 각 사원의 정보에 초봉정보까지 포함
	ArrayList<EmployeesDto2> firstSalary();
	
	// 직급이 senior engineer 인 직원의 정보만 출력하기
	ArrayList<EmployeesDto2> seniorEngineer(String title);
	
	// 연봉이 80_000 이상인 사람의 정보만 출력
	void salary80000();
}

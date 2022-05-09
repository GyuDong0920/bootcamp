package ch04;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeesDto {

 @NonNull
 private int emp_no;
 private String to_date;
 private String dept_name;
 private String first_name;
 private int salary;
 private String dept_no;
 private String title;
 private char gender;
 private String birth_date;
 private String last_name;
 private String hire_date;
 

 
}

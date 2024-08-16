package com.employee.employeeRepository;


import com.employee.employeeException.NameNotFoundException;
import com.employee.employeeentity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Integer> {
 // @Query("select e.gender,COUNT(e) from EmployeeEntity e where e.gender IN('Male','Female') GROUP BY e.gender")
  @Query(value="select gender,count(*) from employee_details where gender in('Male','Female') group by gender",nativeQuery = true)
  public Map<String, Long> getMaleAndFemaleEmployees();

  @Query("select DISTINCT e.department from EmployeeEntity e")
  public List<String> getNameOfAllDepartments();

  @Query("select e.gender,AVG(e.age) from EmployeeEntity e where e.gender IN('Male','Female')GROUP BY e.gender")
  public Map<String,Double> averageAgeOfMaleAndFemaleEmployees();

  @Query(value="select e from EmployeeEntity e ORDER BY e.salary DESC")
  public EmployeeEntity getHighestPaidEmployee();

  @Query(value="select e.name from EmployeeEntity e where e.yearOfJoining>2015")
  public List<String> getNamesAfter2015();

  @Query(value="select e.department,COUNT(e.name) from EmployeeEntity e GROUP BY e.department" )
  public Map<String,Long> numberOfEmployeesInEachDepartment();

  @Query(value="select e.department,AVG(e.salary) from EmployeeEntity e GROUP BY e.department")
  public Map<String, Double> averageSalaryOfEachDepartment();

  @Query(value="select e from EmployeeEntity e where e.gender='Male' AND e.department='Product Development' ORDER BY yearOfJoining DESC limit 1")
  public EmployeeEntity youngestMaleInProductDepartment();

  @Query(value="select e from EmployeeEntity e ORDER BY e.yearOfJoining ASC LIMIT 1")
  public EmployeeEntity mostExperiencedEmployee();

  @Query(value="select e.gender,COUNT(e) from EmployeeEntity e where e.department='Sales And Marketing' AND e.gender IN('Male','Female') GROUP BY e.gender")
  public Map<String, Long> maleAndFemaleSalesTeam();

  @Query(value="select e.gender,AVG(e.salary) from EmployeeEntity e where e.gender IN('Male','Female') GROUP BY e.gender")
  public Map<String, Double> averageSalaryOfMaleAndFemale();

  @Query(value="select e.name,e.department from EmployeeEntity e GROUP BY e.department")
  public Map<String, List<String>> nameOfEmployeesInEachDepartment();

  @Query(value="select e from EmployeeEntity e ORDER BY e.yearOfJoining ASC LIMIT 1")
  public EmployeeEntity getOldestEmployee();

  @Procedure(procedureName = "getAllEmployees")
  public List<EmployeeEntity> getAllEmployee();






    }
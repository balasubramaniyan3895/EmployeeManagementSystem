package com.employee.employeeController;

import com.employee.employeeException.*;
import com.employee.employeeService.EmployeeService;
import com.employee.employeeentity.EmployeeEntity;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @PostMapping("/save")
    public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity e) throws InvalidAgeException{
        service.addEmployee(e);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(e);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable int id) throws IdNotFoundException {
        EmployeeEntity e=service.getEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body(e);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable int id, @RequestBody EmployeeEntity e) throws IdNotFoundException{
        EmployeeEntity em=service.updateEmployee(id,e);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @PatchMapping("/patchUpdate/{id}")
    public ResponseEntity<EmployeeEntity> patchEmployee(@PathVariable int id,@RequestBody EmployeeEntity e) throws IdNotFoundException{
        EmployeeEntity em=service.patchEmployee(id,e);
        return ResponseEntity.status(HttpStatus.OK).body(em);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) throws IdNotFoundException{
         service.deleteEmployee(id);
         return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted successfully");
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeEntity>> getAllEmployee(){
        List<EmployeeEntity> getAllEmployees= service.getAllEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(getAllEmployees);
    }
    @GetMapping("/getName/{name}")
    public ResponseEntity<List<EmployeeEntity>> getByName(@PathVariable String name) throws NameNotFoundException {
        List<EmployeeEntity> n=service.getByName(name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(n);
    }
    @GetMapping("/getGender/{gender}")
    public ResponseEntity<List<EmployeeEntity>> getByGender(@PathVariable String gender) throws GenderException {
        List<EmployeeEntity> g=service.getByGender(gender);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(g);
    }
    @GetMapping("/getDepartment/{department}")
    public ResponseEntity<List<EmployeeEntity>> getByDepartment(@PathVariable String department) throws DepartmentNotFoundException {
        List<EmployeeEntity> d=service.getByDepartment(department);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(d);
    }
    @GetMapping("/getMaleAndFemaleEmployees")
    public ResponseEntity<Map<String,Long>> getMaleAndFemaleEmployees(){
        Map<String,Long> maleandfemale=service.getMaleAndFemaleEmployees();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(maleandfemale);
    }
    @GetMapping("/getNameOfAllDepartments")
    public ResponseEntity<List<String>> getNameOfAllDepartments(){
        List<String> departmentNames=service.getNameOfAllDepartments();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmentNames);
    }
    @GetMapping("/averageAgeOfMaleAndFemaleEmployees")
    public ResponseEntity<Map<String,Double>> averageAgeOfMaleAndFemaleEmployees(){
        Map<String,Double> averageAgeOfEmployees=service.averageAgeOfMaleAndFemaleEmployees();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(averageAgeOfEmployees);
    }
    @GetMapping("/getHighestPaidEmployee")
    public ResponseEntity<EmployeeEntity> getHighestPaidEmployee(){
        EmployeeEntity highestPaidEmployee=service.getHighestPaidEmployee();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(highestPaidEmployee);
    }
    @GetMapping("/getNamesAfter2015")
    public ResponseEntity<List<String>> getNamesAfter2015(){
        List<String> namesAfter2015=service.getNamesAfter2015();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(namesAfter2015);
    }
    @GetMapping("/numberOfEmployeesInEachDepartment")
    public ResponseEntity<Map<String,Long>> numberOfEmployeesInEachDepartment(){
        Map<String,Long> numberOfEmployees=service.numberOfEmployeesInEachDepartment();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(numberOfEmployees);
    }
    @GetMapping("/averageSalaryOfEachDepartment")
    public ResponseEntity<Map<String,Double>> averageSalaryOfEachDepartment(){
        Map<String,Double> averageSalary=service.averageSalaryOfEachDepartment();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(averageSalary);
    }
    @GetMapping("/youngestMaleInProductDevelopment")
    public ResponseEntity<EmployeeEntity> youngestMaleInProductDepartment(){
        EmployeeEntity youngestMaleProductDepartment=service.youngestMaleInProductDepartment();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(youngestMaleProductDepartment);
    }
    @GetMapping("/mostExperiencedEmployee")
    public ResponseEntity<EmployeeEntity> mostExperiencedEmployee(){
        EmployeeEntity mostExperienceEmployee=service.mostExperiencedEmployee();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mostExperienceEmployee);
    }
    @GetMapping("/maleAndFemaleSalesTeam")
    public ResponseEntity<Map<String,Long>> maleAndFemaleSalesTeam(){
        Map<String,Long> maleFemaleSalesTeam=service.maleAndFemaleSalesTeam();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(maleFemaleSalesTeam);
    }
    @GetMapping("/averageSalaryOfMaleAndFemale")
    public ResponseEntity<Map<String,Double>> averageSalaryOfMaleAndFemale(){
        Map<String,Double> averageSalaryMaleAndFemale=service.averageSalaryOfMaleAndFemale();
        return ResponseEntity.status(HttpStatus.OK).body(averageSalaryMaleAndFemale);
    }
    @GetMapping("/nameOfEmployeesInEachDepartment")
    public ResponseEntity<Map<String,List<String>>> nameOfEmployeesInEachDepartment(){
        Map<String,List<String>> nameOfEmployeesEachDepartment=service.nameOfEmployeesInEachDepartment();
        return ResponseEntity.status(HttpStatus.CREATED).body(nameOfEmployeesEachDepartment);
    }
    @GetMapping("/averageAndTotalSalary")
    public ResponseEntity<String> averageAndTotalSalary(){
        String s=service.averageAndTotalSalary();
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }
    @GetMapping("/seperateEmployees")
    public ResponseEntity<String> seperateEmployees(){
        String s=service.seperateEmployees();
        return ResponseEntity.status(HttpStatus.CREATED).body(s);
    }
    @GetMapping("/getOldestEmployee")
    public ResponseEntity<EmployeeEntity> getOldestEmployee(){
        EmployeeEntity oldestEmployee=service.getOldestEmployee();
        return ResponseEntity.status(HttpStatus.OK).body(oldestEmployee);
    }



}

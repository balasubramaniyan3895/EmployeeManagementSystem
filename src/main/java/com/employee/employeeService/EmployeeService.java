package com.employee.employeeService;

import com.employee.EmployeeApplication;
import com.employee.employeeException.*;
import com.employee.employeeRepository.EmployeeRepository;
import com.employee.employeeentity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public EmployeeEntity addEmployee(EmployeeEntity e) throws InvalidAgeException {
        if(e.getAge()>=18){
           return  repository.save(e);
        }else{
            throw new InvalidAgeException("Age should be greater than 18");
        }
    }

    public EmployeeEntity getEmployee(int id) throws IdNotFoundException {
        return repository.findById(id).orElseThrow(()->new IdNotFoundException("Invalid Id"));

    }

    public EmployeeEntity updateEmployee(int id, EmployeeEntity e) throws IdNotFoundException {
        Optional<EmployeeEntity> em=repository.findById(id);
        if(em.isPresent()){
            EmployeeEntity emp=em.get();
            emp.setAge(e.getAge());
            emp.setName(e.getName());
            emp.setGender(e.getGender());
            emp.setDepartment(e.getDepartment());
            emp.setSalary(e.getSalary());
            emp.setYearOfJoining(e.getYearOfJoining());
            return repository.save(emp);
        }
        else{
            throw new IdNotFoundException("Id not found in database");
        }
    }

    public EmployeeEntity patchEmployee(int id, EmployeeEntity e) throws IdNotFoundException{
        Optional<EmployeeEntity> emp=repository.findById(id);
        if(emp.isPresent()){
           EmployeeEntity empl=emp.get();
           empl.setName(e.getName());
           return repository.save(empl);
        }
        else{
            throw new IdNotFoundException("id not found in database");
        }
    }

    public String deleteEmployee(int id) throws IdNotFoundException {
        Optional<EmployeeEntity> e = repository.findById(id);
        if(e.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IdNotFoundException("id not in database");
        }
        return "deleted Successfully";
    }

    public List<EmployeeEntity> getByName(String name) throws NameNotFoundException {
        List<EmployeeEntity> list=repository.findAll();
        List<EmployeeEntity> n=list.stream().filter(x->x.getName().equals(name)).collect(Collectors.toList());
        if(n.isEmpty()){
            throw new NameNotFoundException("Name not found in database");
        }
        else{
            return n;
        }
    }

    public List<EmployeeEntity> getByGender(String gender) throws GenderException {
        List<EmployeeEntity> list=repository.findAll();
        List<EmployeeEntity> gen=list.stream().filter(x->x.getGender().equals(gender)).collect(Collectors.toList());
        if(gen.isEmpty()){
            throw new GenderException("gender not found in database");
        }
        else{
            return gen;
        }
    }

    public List<EmployeeEntity> getByDepartment(String department) throws DepartmentNotFoundException {
        List<EmployeeEntity> list=repository.findAll();
        List<EmployeeEntity> dep=list.stream().filter(x->x.getDepartment().equals(department)).collect(Collectors.toList());
        if(dep.isEmpty()){
            throw new DepartmentNotFoundException("department not found in database");
        }
        else{
            return dep;
        }
    }

    public Map<String, Long> getMaleAndFemaleEmployees() {
        List<EmployeeEntity> list=repository.findAll();
        Map<String,Long> maleAndFemaleEmployees=list.stream().collect(Collectors.groupingBy(EmployeeEntity::getGender,Collectors.counting()));
        return maleAndFemaleEmployees;
    }

    public List<String> getNameOfAllDepartments() {
        List<EmployeeEntity> list=repository.findAll();
        List<String> namesofAlldepartment=list.stream().map(EmployeeEntity::getDepartment).collect(Collectors.toList());
        return namesofAlldepartment;
    }

    public Map<String,Double> averageAgeOfMaleAndFemaleEmployees() {
        List<EmployeeEntity> list=repository.findAll();
        Map<String,Double> averageAge=list.stream().collect(Collectors.groupingBy(EmployeeEntity::getGender,
                Collectors.averagingDouble(EmployeeEntity::getAge)));
        return averageAge;
    }

    public EmployeeEntity getHighestPaidEmployee() {
        List<EmployeeEntity> list=repository.findAll();
        EmployeeEntity getHighestPaidEmployee=list.stream().max(Comparator.comparingDouble(EmployeeEntity::getSalary)).get();
        return getHighestPaidEmployee;
    }

    public List<String> getNamesAfter2015() {
        List<EmployeeEntity> list=repository.findAll();
        List<String> namesofEmployeesafter2015=list.stream().filter(x->x.getYearOfJoining()>2015).map(EmployeeEntity::getName)
                .collect(Collectors.toList());
        return namesofEmployeesafter2015;
    }

    public Map<String,Long> numberOfEmployeesInEachDepartment() {

        List<EmployeeEntity> list=repository.findAll();
        Map<String,Long> numberOfEmployeesEachDepartment=list.stream().collect(Collectors.groupingBy(EmployeeEntity::getDepartment,
                Collectors.counting()));
        return numberOfEmployeesEachDepartment;
    }

    public Map<String, Double> averageSalaryOfEachDepartment() {
        List<EmployeeEntity> list=repository.findAll();
        Map<String,Double> averageSalaryEachDepartment=list.stream().collect(Collectors.groupingBy(EmployeeEntity::getDepartment,
                Collectors.averagingDouble(EmployeeEntity::getSalary)));

        return averageSalaryEachDepartment;
    }

    public EmployeeEntity youngestMaleInProductDepartment() {
        List<EmployeeEntity> list=repository.findAll();
        EmployeeEntity youngestMaleProductDepartment=list.stream().filter(x->x.getDepartment().equals("Product Development"))
                .min(Comparator.comparingInt(EmployeeEntity::getAge)).get();
        return youngestMaleProductDepartment;


    }

    public EmployeeEntity mostExperiencedEmployee() {
        List<EmployeeEntity> list=repository.findAll();
        EmployeeEntity mostExperienceEmployee=list.stream().min(Comparator.comparingInt(EmployeeEntity::getYearOfJoining)).get();
        return mostExperienceEmployee;
    }

    public Map<String, Long> maleAndFemaleSalesTeam() {
        List<EmployeeEntity> list=repository.findAll();
        Map<String,Long> maleFemaleSalesTeam=list.stream().filter(x->x.getDepartment().equals("Sales And Marketing"))
                .collect(Collectors.groupingBy(EmployeeEntity::getGender,Collectors.counting()));
        return maleFemaleSalesTeam;
    }

    public Map<String, Double> averageSalaryOfMaleAndFemale() {
        List<EmployeeEntity> list=repository.findAll();
       Map<String,Double> averageSalaryMaleAndFemale=list.stream().collect(Collectors.groupingBy(EmployeeEntity::getGender,
               Collectors.averagingDouble(EmployeeEntity::getSalary)));
        return averageSalaryMaleAndFemale;
    }

    public Map<String, List<String>> nameOfEmployeesInEachDepartment() {
        List<EmployeeEntity> list=repository.findAll();
        Map<String,List<String>> nameOfEmployeesEachDepartment=list.stream().collect(Collectors.groupingBy(EmployeeEntity::getDepartment,
                Collectors.mapping(EmployeeEntity::getName,Collectors.toList())));
        return nameOfEmployeesEachDepartment;
    }


    public String averageAndTotalSalary() {
        List<EmployeeEntity> list=repository.findAll();
        DoubleSummaryStatistics averageAndTotalSalary=list.stream().collect(Collectors.summarizingDouble(EmployeeEntity::getSalary));
        Double average=averageAndTotalSalary.getAverage();
        Double total=averageAndTotalSalary.getSum();
        return "total salary: "+total+" "+
                "average salary: "+average;


    }


    public String seperateEmployees() {
        List<EmployeeEntity> list=repository.findAll();
        Map<Boolean,List<EmployeeEntity>> seperateEmployees=list.stream().collect(Collectors.partitioningBy(x->x.getAge()<=25));
        List<EmployeeEntity> EmployeesAgelessThanOrEqualTo25=seperateEmployees.get(true);
        List<EmployeeEntity> EmployeesAgeGreaterThanOrEqualTo25=seperateEmployees.get(false);
        return "EmployeesAgelessThanOrEqualTo25: "+EmployeesAgelessThanOrEqualTo25+" "+
                "EmployeesAgeGreaterThanOrEqualTo25: "+EmployeesAgeGreaterThanOrEqualTo25;


    }

    public EmployeeEntity getOldestEmployee() {
        List<EmployeeEntity> list=repository.findAll();
        EmployeeEntity oldestEmployee=list.stream().min(Comparator.comparingInt(EmployeeEntity::getYearOfJoining)).get();
        return oldestEmployee;
    }

    public List<EmployeeEntity> getAllEmployee() {
        List<EmployeeEntity> getAllEmployees=repository.findAll();
        return getAllEmployees;

    }
}

package com.employee;


import com.employee.employeeException.IdNotFoundException;
import com.employee.employeeException.InvalidAgeException;
import com.employee.employeeException.NameNotFoundException;
import com.employee.employeeRepository.EmployeeRepository;
import com.employee.employeeService.EmployeeService;
import com.employee.employeeentity.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository repository;
    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddEmployeeSuccess() throws InvalidAgeException{
        EmployeeEntity employee=new EmployeeEntity(1,"John",25,"Male","IT",2018,50000.00);
        when(repository.save(employee)).thenReturn(employee);

        EmployeeEntity result=employeeService.addEmployee(employee);
        assertEquals(employee,result);
    }
    @Test
    void testAddEmployeeAgeException() {
        EmployeeEntity employee=new EmployeeEntity(1,"John",17,"Male","Information Technology",2017,43500.00);
       assertThrows(InvalidAgeException.class,()->employeeService.addEmployee(employee));
    }
    @Test
    void testGetEmployeeSuccess() throws IdNotFoundException{
        EmployeeEntity employee=new EmployeeEntity(1,"John",25,"Male","Information Technology",2017,45000.00);
        when(repository.findById(1)).thenReturn(Optional.of(employee));

        EmployeeEntity result=employeeService.getEmployee(1);
        assertEquals(employee,result);
    }

    @Test
    void testGetEmployeeNotFound(){
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class,()->employeeService.getEmployee(1));
    }

    @Test
    void testUpdateEmployeeSuccess() throws IdNotFoundException {
        EmployeeEntity existingEmployee=new EmployeeEntity(1,"John",24,"Male","IT",2016,34500.00);
        EmployeeEntity updatedEmployee=new EmployeeEntity(1,"Jake",25,"Female","Technical Support",2017,35000.00);
        when(repository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(repository.save(updatedEmployee)).thenReturn(updatedEmployee);
        EmployeeEntity result=employeeService.updateEmployee(1,updatedEmployee);
        assertEquals(updatedEmployee,result);
    }
    @Test
    void testUpdateEmployeeNotFound(){
        EmployeeEntity updatedEmployee=new EmployeeEntity(1,"Jake",25,"Female","Technical Support",2017,35000.00);
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(IdNotFoundException.class,()->employeeService.updateEmployee(1,updatedEmployee));
    }
    @Test
    void testPatchEmployeeSuccess() throws IdNotFoundException {
        EmployeeEntity existingEmployee=new EmployeeEntity(1,"John",25,"Male","IT",2018,50000.00);
        EmployeeEntity patchedEmployee=new EmployeeEntity(1,"John Doe",25,"Male","IT",2018,50000.00);
        when(repository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(repository.save(patchedEmployee)).thenReturn(patchedEmployee);

        EmployeeEntity result=employeeService.patchEmployee(1,patchedEmployee);
        assertEquals(patchedEmployee,result);
    }
    @Test
    void testPatchEmployeeNotFound() {
        EmployeeEntity patchedEmployee = new EmployeeEntity(1, "John Doe", 25, "Male", "IT", 2018, 50000.00);
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> employeeService.patchEmployee(1, patchedEmployee));
    }
    @Test
    void testDeleteEmployee() throws IdNotFoundException {
        when(repository.findById(1)).thenReturn(Optional.of(new EmployeeEntity()));

        String result=employeeService.deleteEmployee(1);
        assertEquals("deleted successfully",result);
    }
    @Test
    void testDeleteEmployeeNotFound(){
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,()->employeeService.deleteEmployee(1));
    }
    @Test
    void testGetByNameSuccess() throws NameNotFoundException {
        EmployeeEntity employee1=new EmployeeEntity(1,"John",25,"Male","IT",2017,45000.00);
        EmployeeEntity employee2=new EmployeeEntity(2,"John",25,"Male","Finance",2017,45500.00);
        List<EmployeeEntity> employees= Arrays.asList(employee1,employee2);
        when(repository.findAll()).thenReturn(employees);

        List<EmployeeEntity> result=employeeService.getByName("John");
        assertEquals(2,result.size());
        assertTrue(result.stream().allMatch(e->"John".equals(e.getName())));

    }
    @Test
    void testGetByNameNotFound() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(NameNotFoundException.class, () -> employeeService.getByName("John"));
    }



}



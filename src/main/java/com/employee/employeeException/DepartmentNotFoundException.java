package com.employee.employeeException;

public class DepartmentNotFoundException extends Exception {
    public DepartmentNotFoundException(String message){
        super(message);
    }
}

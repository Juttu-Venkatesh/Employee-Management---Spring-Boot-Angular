package com.employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.employee.entity.Employee;

public interface EmployeeService {

	ResponseEntity<String> createEmployee(Employee employee);

    List<Employee> getAllEmployees();
    
    ResponseEntity<String> updateEmployee(Long employeeId, Employee employee);
    
    ResponseEntity<String> deleteEmployee(Long employeeId);

	ResponseEntity<String> getEmployeeById(Long employeeId);

	long getEmployeeCount();
}

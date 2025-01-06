package com.employee.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.employee.entity.Employee;


@RequestMapping("/EmployeeManagement")
@CrossOrigin(origins = "http://localhost:4200")
public interface EmployeeRest {
	
    @PostMapping("/CreateEmployee")
    ResponseEntity<String> createEmployee(@RequestBody Employee employee);

    @GetMapping("/GetAllEmployees")
    ResponseEntity<List<Employee>> getAllEmployees();
    
    @GetMapping("/GetEmployee/{employeeId}")
    ResponseEntity<String> getEmployeeById(@PathVariable Long employeeId);
    
    @PutMapping("/UpdateEmployee/{employeeId}")
    ResponseEntity<String> updateEmployee(@PathVariable Long employeeId, 
    		                                                   @RequestBody Employee employee);
    
    @DeleteMapping("/DeleteEmployee/{employeeId}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId);
    
    
    @GetMapping("/EmployeeCount")
    public long getEmployeeCount();
    
}

package com.employee.restImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.Employee;
import com.employee.rest.EmployeeRest;
import com.employee.service.EmployeeService;

@RestController
public class EmployeeRestImpl implements EmployeeRest {

    @Autowired
    EmployeeService employeeService;

    @Override
    public ResponseEntity<String> createEmployee(Employee employee) {
        try {
            employeeService.createEmployee(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@Override
	public ResponseEntity<String> deleteEmployee(Long employeeId) {
		try {
			return employeeService.deleteEmployee(employeeId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> updateEmployee(Long employeeId, Employee employee) {
		try {
			return employeeService.updateEmployee(employeeId, employee);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> getEmployeeById(Long employeeId) {
		try {
			return employeeService.getEmployeeById(employeeId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public long getEmployeeCount() {
		return employeeService.getEmployeeCount();
	}
}

package com.employee.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.entity.Employee;
import com.employee.repo.EmployeeRepo;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

	@Override
	public ResponseEntity<String> createEmployee(Employee employee) {
		try {
			employeeRepo.save(employee);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		try {
			return employeeRepo.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		    return new ArrayList<>();  
		}
	}

	@Override
	public ResponseEntity<String> updateEmployee(Long employeeId, Employee employee) {
		try {
			Optional<Employee> existingEmployeeOpt = employeeRepo.findById(employeeId);
			if (existingEmployeeOpt.isPresent()) {
				Employee existingEmployee = existingEmployeeOpt.get();
				
				existingEmployee.setFirstName(employee.getFirstName());
				existingEmployee.setLastName(employee.getLastName());
			    existingEmployee.setContactNo(employee.getContactNo());
				existingEmployee.setEmailId(employee.getEmailId());
				existingEmployee.setPassword(employee.getPassword());
				existingEmployee.setDepartment(employee.getDepartment());
				existingEmployee.setGender(employee.getGender());
				existingEmployee.setRole(employee.getRole());
				
				employeeRepo.save(existingEmployee);
			    return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
				
			} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		   }
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> deleteEmployee(Long employeeId) {
		try {
			if (employeeRepo.existsById(employeeId)) {
				employeeRepo.deleteById(employeeId);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> getEmployeeById(Long employeeId) {
		try {
			if (employeeRepo.existsById(employeeId)) {
				employeeRepo.findById(employeeId);
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public long getEmployeeCount() {
		return employeeRepo.count();
	}

}


//
//@Override
//public ResponseDTO createEmployee(Employee employee) {
//  try {
//      if (employee.getDeptId() == null) {
//          throw new IllegalArgumentException("Department ID (deptId) is required");
//      }
//
//      Department dept = new Department();
//      dept.setDepartmentId(employee.getDeptId());
//      employee.setDepartment(dept); 
//
//      Employee savedEmployee = employeeRepo.save(employee);
//
//      return new ResponseDTO(
//          "Employee Created Successfully",
//          true,
//          savedEmployee
//      );
//  } catch (Exception ex) {
//      ex.printStackTrace();
//      return new ResponseDTO(
//          "Error Creating Employee",
//          false,
//          null
//      );
//  }
//}
//
//@Override
//public List<Employee> getAllEmployees() {
//  return employeeRepo.findAll().stream().map(employee -> {
//      employee.setDeptId(employee.getDepartment().getDepartmentId());
//      employee.setDepartment(null);
//      return employee;
//  }).collect(Collectors.toList());
//}
//
//@Override
//public ResponseEntity<String> updateEmployee(Long employeeId, Employee employee) {
//	try {
//		Optional<Employee> existingEmployeeOpt = employeeRepo.findById(employeeId);
//		if(existingEmployeeOpt.isPresent()) {
//			Employee existingEmployee = existingEmployeeOpt.get();
//			
//			existingEmployee.setEmployeeName(employee.getEmployeeName());
//			existingEmployee.setContactNo(employee.getContactNo());
//			existingEmployee.setEmailId(employee.getEmailId());
//			existingEmployee.setPassword(employee.getPassword());
//			existingEmployee.setRole(employee.getRole());
//			if (employee.getDepartment() != null) {
//              existingEmployee.setDepartment(employee.getDepartment());
//          } else if (employee.getDeptId() != null) {
//              Department department = departmentRepo.findById(employee.getDeptId()).orElse(null);
//              if (department != null) {
//                  existingEmployee.setDepartment(department);
//              } else {
//                  return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
//              }
//          }
//			
//			employeeRepo.save(existingEmployee);
//			return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
//			
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	} catch (Exception ex) {
//		ex.printStackTrace();
//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
//
//@Override
//public ResponseEntity<String> deleteEmployee(Long employeeId) {
//	try {
//		if (employeeRepo.existsById(employeeId)) {
//			employeeRepo.deleteById(employeeId);
//			return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>("Department not found", HttpStatus.NOT_FOUND);
//		}
//	} catch (Exception ex) {
//		ex.printStackTrace();
//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}

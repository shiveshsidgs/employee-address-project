package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import model.Employee;

@Service
public interface EmployeeService  {
	Employee saveEmployee(Employee employee);
	
	Employee updateEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
	
	Optional<Employee> getEmployeeById(Long id);
	
	List<Employee> getAllEmployee();
}

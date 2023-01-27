package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import error.ResourceNotFoundException;
import model.Address;
import model.Employee;
import service.AddressAPIClient;
import service.EmployeeService;


@RestController
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController {
	
	 @Autowired
	 private EmployeeService employeeService;
	 @Autowired
	 private AddressAPIClient addressApiClient;
	 private Logger logger=LoggerFactory.getLogger(EmployeeController.class);
	 
	 
	 @PostMapping("/saveEmployee")
	 public Employee saveEmployee(@RequestBody Employee employee) throws JsonProcessingException {  
		 logger.info("Employee-Service updateAddress request : {}",new ObjectMapper().writeValueAsString(employee));
		 Employee e=employeeService.saveEmployee(employee);
			Address adresss=e.getAddress();
			adresss.setId(e.getEmployeeId());
			addressApiClient.addUser(adresss);
			return e;		
		 
		}
	
	 @PutMapping("/updateEmployee/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long id,@RequestBody Employee employee) throws JsonProcessingException,ResourceNotFoundException {
		 logger.info("Address-Service updateAddress request : {}",new ObjectMapper().writeValueAsString(employee));
		 Employee newEmployee = employeeService.getEmployeeById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		 newEmployee.setFirstName(employee.getFirstName());
		 newEmployee.setLastName(employee.getLastName());
		 newEmployee.setPhoneNumber(employee.getPhoneNumber());
		 newEmployee.setSalary(employee.getSalary());
		 newEmployee.setEmail(employee.getEmail());
		 Address adresss=employee.getAddress();
		 final Address updatedAddress=addressApiClient.updateAddress(id, adresss);
		 newEmployee.setAddress(updatedAddress);
		 final Employee updatedEmployee = employeeService.updateEmployee(newEmployee);
		 logger.info("Employee-Service updateEmployee request after updated : {}",new ObjectMapper().writeValueAsString(updatedAddress));
	     return ResponseEntity.ok(updatedEmployee);			
		}
	 
	  @DeleteMapping("/deleteEmployee/{id}")
		public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) throws JsonProcessingException, ResourceNotFoundException {
		 logger.info("Employee-Service deleteEmployee request : {}",new ObjectMapper().writeValueAsString(id));
		 Employee employee = employeeService.getEmployeeById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		 employeeService.deleteEmployee(employee);
		 addressApiClient.deleteAddress(id);
		 Map<String, Boolean> response = new HashMap<>();
		 
	        response.put("deleted", Boolean.TRUE);
	        logger.info("Employee-Service deleteEmployee request after deleted: {}",new ObjectMapper().writeValueAsString(response));
	        return response;
			}
	  
	 @GetMapping("/getEmployeeById/{id}")
	    public ResponseEntity<Employee> getAddressById(@PathVariable(value = "id") Long id) throws JsonProcessingException,ResourceNotFoundException {
		 
		 Employee employee = employeeService.getEmployeeById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		 Address address=addressApiClient.getAddressById(id);
		 employee.setAddress(address);
		 logger.info("Employee-Service getEmployeeById request : {}",new ObjectMapper().writeValueAsString(employee));
		 return ResponseEntity.ok().body(employee);
	    }
	 
	     @GetMapping("/getAllEmployees")
	    public List <Employee> getAllEmployees() throws JsonProcessingException{
	     List<Employee> elist=employeeService.getAllEmployee();
	     for(Employee e:elist)
	     {
	    	 Address adress=addressApiClient.getAddressById(e.getEmployeeId());
	    	 e.setAddress(adress);
	     }
	    
	     logger.info("Employee-Service getAllEmployees request : {}",new ObjectMapper().writeValueAsString(elist));
	        return elist;
	    }
	    

}

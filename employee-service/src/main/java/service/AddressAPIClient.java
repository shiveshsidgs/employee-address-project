package service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import model.Address;



@FeignClient(value = "ADDRESS-SERVICE")
public interface AddressAPIClient {
	
	@PostMapping("/address/saveAddress")
	Address addUser(@RequestBody Address user);
	
	@PutMapping("address/updateAddress/{id}")
	Address updateAddress(@PathVariable(value = "id") Long id,@RequestBody Address address);
	
	 @DeleteMapping("address/deleteAddress/{id}")
	 Map<String, Boolean> deleteAddress(@PathVariable(value = "id") Long id);
	 
	 @GetMapping("address/getAddressById/{id}")
	 Address getAddressById(@PathVariable(value = "id") Long id);
	 
	// @GetMapping("address/getAllAddresses")
	 //public List < Address > getAllAddresses();
	 
	
}

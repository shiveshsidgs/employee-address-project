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
import service.AddressService;


@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
	
	 @Autowired
	 private AddressService addressService;
	 
	 private Logger logger=LoggerFactory.getLogger(AddressController.class);
	 	
	 
	 @PostMapping("/saveAddress")
		public Address saveAddress(@RequestBody Address address) throws JsonProcessingException {
		 logger.info("Address-Service  saveAddress request : {}",new ObjectMapper().writeValueAsString(address));
			return addressService.saveAddress(address);
		}
	 
	 @PutMapping("/updateAddress/{id}")
		public Address updateAddress(@PathVariable(value = "id") Long id,@RequestBody Address address) throws JsonProcessingException,ResourceNotFoundException {
		 logger.info("Address-Service updateAddress request : {}",new ObjectMapper().writeValueAsString(address));
		 Address newAddress = addressService.getAddressById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
		 newAddress.setCity(address.getCity());
		 newAddress.setState(address.getState());
		 newAddress.setStreetNo(address.getState());
		 final Address updatedAddress = addressService.updateAddress(address);
		 logger.info("Address-Service updateAddress request after updated : {}",new ObjectMapper().writeValueAsString(updatedAddress));
	        return updatedAddress;			
		}
	 
	 @DeleteMapping("/deleteAddress/{id}")
		public Map<String, Boolean> deleteAddress(@PathVariable(value = "id") Long id) throws JsonProcessingException, ResourceNotFoundException {
		 logger.info("Address-Service deleteAddress request : {}",new ObjectMapper().writeValueAsString(id));
		 Address address = addressService.getAddressById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
		 addressService.deleteAddress(address);
		 Map<String, Boolean> response = new HashMap<>();
		 logger.info("Address-Service deleteAddress request after deleted: {}",new ObjectMapper().writeValueAsString(response));
	        response.put("deleted", Boolean.TRUE);
	        return response;
			}
	  
	 @GetMapping("/getAddressById/{id}")
	    public Address getAddressById(@PathVariable(value = "id") Long id) throws JsonProcessingException,ResourceNotFoundException {
		 Address address = addressService.getAddressById(id)
			        .orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + id));
		 logger.info("Address-Service getAddressById request : {}",new ObjectMapper().writeValueAsString(address));
		 return address;
	    }
	 
	 @GetMapping("/getAllAddresses")
	    public List < Address > getAllAddresses() throws JsonProcessingException {
		 List<Address> list=addressService.getAllAddress();
		 logger.info("Address-Service getAllAddresses request : {}",new ObjectMapper().writeValueAsString(list));
	        return list;
	    }
	
}

package service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import model.Address;
@Service
public interface AddressService {
	
	Address saveAddress(Address address) ;
	
	Address updateAddress(Address address);
	
	void deleteAddress(Address address) throws JsonProcessingException;
	
	Optional<Address> getAddressById(Long id) throws JsonProcessingException;
	
	List<Address> getAllAddress() ;

}

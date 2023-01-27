package service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import error.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.Address;
import repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
    private AddressRepository addressRepository;
	
	
	@Override
	public Address saveAddress(Address address)  {
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void deleteAddress(Address address) {
		 addressRepository.delete(address);
	}

	@Override
	public Optional<Address> getAddressById(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}

}

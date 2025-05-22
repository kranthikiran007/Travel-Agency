package com.kk.service;

import java.util.List;

import com.kk.dtos.AddressDto;
import com.kk.dtos.CustomerDto;
import com.kk.dtos.TourDto;
import com.kk.entity.Tour;

public interface CustomerService {
	// Retrieval
	List<CustomerDto> getAll();

	CustomerDto getById(int id);

	List<CustomerDto> getByLastName(String lastName);

	List<CustomerDto> getByTour(String packageName);
	
	List<TourDto> getAllTours();
	
	TourDto getTourByName(String name);

	// Create
	CustomerDto addCustomer(CustomerDto customer);

	TourDto addTour(TourDto tourDto);

	// Update
	CustomerDto updateCustomer(int id, CustomerDto customerDto);


	TourDto updateTour(int id, TourDto tour);

	// Delete
	void deleteCustomer(int id);


}

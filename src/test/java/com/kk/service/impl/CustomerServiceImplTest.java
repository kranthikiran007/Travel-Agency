package com.kk.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kk.dtos.AddressDto;
import com.kk.dtos.CustomerDto;
import com.kk.dtos.TourDto;
import com.kk.entity.Address;
import com.kk.entity.Customer;
import com.kk.entity.Tour;
import com.kk.exception.CustomerNotFoundException;
import com.kk.exception.TourNotFoundException;
import com.kk.repository.AddressRepository;
import com.kk.repository.CustomerRepository;
import com.kk.repository.TourRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest{

	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private TourRepository tourRepository;
	@Mock
	private AddressRepository addressRepository;
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	Customer customer1;
	Customer customer2;
	Customer customer3;
	CustomerDto customerDto;
//	CustomerDto customerDto2;
	List<Customer> customers = new ArrayList<Customer>();
	Tour tour1;
	Tour tour2;
	Tour tour3;
	TourDto tourDto;
	List<Tour> tours = new ArrayList<Tour>();
	@BeforeEach
	void setUp() {
		// Customer 1
		Address permanentAddress1 = new Address(1,"123", "Main St", "Near Park", "Springfield", "IL", 62701);
		Address communicationAddress1 = new Address(2,"123", "Main St", "Near Park", "Springfield", "IL", 62701); 
		tour1 = new Tour(1, "Springfield", 1500.00, "Chicago",
				Arrays.asList("Lincoln Home", "Millennium Park"), "Weekend Getaway");
		customer1 = new Customer(1, "John", "Doe", "1234567890", "Prefers window seat", permanentAddress1, communicationAddress1,
				tour1);
		//Dto
		AddressDto permanentAddress = new AddressDto(1,"123", "Main St", "Near Park", "Springfield", "IL", 62701);
		AddressDto communicationAddress = new AddressDto(2,"123", "Main St", "Near Park", "Springfield", "IL", 62701); 
		tourDto = new TourDto(1, "Springfield", 1500.00, "Chicago",
				Arrays.asList("Lincoln Home", "Millennium Park"), "Weekend Getaway");
		customerDto = new CustomerDto(1, "John", "Doe", "1234567890", "Prefers window seat", permanentAddress, communicationAddress,
				tourDto);
//		customerDto2 = new CustomerDto(1,null, "Doe", "1234567890", null, null, communicationAddress,
//				null);
 
		// Customer 2
		Address permanentAddress2 = new Address(3,"456", "Oak Ave", "Beside Lake", "Shelbyville", "KY", 40065);
		Address communicationAddress2 = new Address(4,"789", "Maple Dr", "Downtown", "Shelbyville", "KY", 40065);
		tour2 = new Tour(2, "Shelbyville", 2500.00, "Miami",
				Arrays.asList("South Beach", "Art Deco District"), "Beach Fun");
		customer2 = new Customer(2, "Jane", "Smith", "0987654321", "Allergic to nuts", permanentAddress2, communicationAddress2,
				tour2);
 
		// Customer 3
		Address permanentAddress3 = new Address(5,"789", "Pine Ln", "Opposite Mall", "Capital City", "NY", 12201);
		Address communicationAddress3 = new Address(6,"101", "Elm Rd", "Near Library", "Capital City", "NY", 12201);
		tour3 = new Tour(3, "Capital City", 3500.00, "San Francisco",
				Arrays.asList("Golden Gate Bridge", "Alcatraz"), "City Explorer");
		customer3 = new Customer(3, "Peter", "Jones", "1122334455", "Needs wheelchair access", permanentAddress3, communicationAddress3,
				tour3);
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		tours.add(tour1);
		tours.add(tour2);
		tours.add(tour3);
	}
		
	@Test
	void testGetAll() {
		when(customerRepository.findAll()).thenReturn(customers);
		var res = customerServiceImpl.getAll();
		assertEquals(3, res.size());
		assertEquals("Peter", res.get(2).getFirstName());
		assertEquals("Springfield", res.get(0).getCommunicationAdd().getCity());
		assertEquals("Beach Fun", res.get(1).getTour().getPackageName());
		verify(customerRepository, times(1)).findAll();
		
	}
	
	@Test
	void testGetById() {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer2));
		var res = customerServiceImpl.getById(1);
		assertEquals("Shelbyville", res.getCommunicationAdd().getCity());
		assertEquals("Beach Fun", res.getTour().getPackageName());
		assertEquals("Art Deco District", res.getTour().getLocationsCovered().get(1));
		verify(customerRepository, times(1)).findById(1);
	}

	@Test
	void testNegativeGetById() {
		when(customerRepository.findById(anyInt())).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class, ()->customerServiceImpl.getById(1));		
		verify(customerRepository, times(1)).findById(1);
	}
	
	@Test
	void testGetByLastName() {
		when(customerRepository.findByLastName(anyString())).thenReturn(customers);
		var res = customerServiceImpl.getByLastName("kk");
		assertEquals("Peter", res.get(2).getFirstName());
		assertEquals("Springfield", res.get(0).getCommunicationAdd().getCity());
		assertEquals("Beach Fun", res.get(1).getTour().getPackageName());
		verify(customerRepository, times(1)).findByLastName(anyString());
	}

	@Test
	void testGetByTour() {
		when(tourRepository.findByPackageName(anyString())).thenReturn(tours);
		when(customerRepository.findByTourDetails(any(Tour.class))).thenReturn(customers);
		var res = customerServiceImpl.getByTour(anyString());
		assertEquals(3, res.size());
		assertEquals("Peter", res.get(2).getFirstName());
		assertEquals("Springfield", res.get(0).getCommunicationAdd().getCity());
		assertEquals("Beach Fun", res.get(1).getTour().getPackageName());
		verify(customerRepository, times(1)).findByTourDetails(any(Tour.class));
		verify(tourRepository, times(1)).findByPackageName(anyString());
	}

	@Test
	void testGetAllTours() {
		when(tourRepository.findAll()).thenReturn(tours);
		var res = customerServiceImpl.getAllTours();
		assertEquals(3,res.size());
		assertEquals("Capital City", res.get(2).getStartLoc());
		assertEquals("Weekend Getaway", res.get(0).getPackageName());
		assertEquals(2500.00, res.get(1).getCost());
		verify(tourRepository, times(1)).findAll();
	}

	@Test
	void testGetTourByName() {
		when(tourRepository.findByPackageName(anyString())).thenReturn(tours);
		var res = customerServiceImpl.getTourByName(anyString());
		assertEquals("Springfield", res.getStartLoc());
		assertEquals("Weekend Getaway", res.getPackageName());
		assertEquals(1500.00, res.getCost());
		assertEquals("Millennium Park", res.getLocationsCovered().get(1));
		verify(tourRepository, times(1)).findByPackageName(anyString());
	}

	@Test
	
	void testAddCustomer() {
		when(tourRepository.existsByPackageName(anyString())).thenReturn(true);
		when(tourRepository.findByPackageName(anyString())).thenReturn(tours);
		when(tourRepository.save(any(Tour.class))).thenReturn(tour1);
		when(customerRepository.save(any(Customer.class))).thenReturn(customer1);
		var res = customerServiceImpl.addCustomer(customerDto);
		assertEquals("Springfield", res.getCommunicationAdd().getCity());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Millennium Park", res.getTour().getLocationsCovered().get(1));
		verify(customerRepository, times(1)).save(any(Customer.class));
		verify(tourRepository, times(1)).findByPackageName(anyString());
		verify(tourRepository, times(1)).save(any(Tour.class));
		verify(tourRepository, times(1)).existsByPackageName(anyString());
		
	}
	@Test
	
	void testNegativeAddCustomer() {
		when(tourRepository.existsByPackageName(anyString())).thenReturn(false);
		when(tourRepository.save(any(Tour.class))).thenReturn(tour1);
		when(customerRepository.save(any(Customer.class))).thenReturn(customer1);
		var res = customerServiceImpl.addCustomer(customerDto);
		assertEquals("Springfield", res.getCommunicationAdd().getCity());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Millennium Park", res.getTour().getLocationsCovered().get(1));
		verify(customerRepository, times(1)).save(any(Customer.class));
		verify(tourRepository, times(1)).save(any(Tour.class));
		verify(tourRepository, times(1)).existsByPackageName(anyString());
		verify(tourRepository, times(0)).findByPackageName(anyString());
	}

	@Test
	void testAddTour() {
		when(tourRepository.existsByPackageName(anyString())).thenReturn(true);
		when(tourRepository.findByPackageName(anyString())).thenReturn(tours);
		when(tourRepository.save(any(Tour.class))).thenReturn(tour1);
		var res = customerServiceImpl.addTour(tourDto);
		assertEquals("Springfield", res.getStartLoc());
		assertEquals("Weekend Getaway", res.getPackageName());
		assertEquals(1500.00, res.getCost());
		assertEquals("Millennium Park", res.getLocationsCovered().get(1));
		verify(tourRepository, times(1)).existsByPackageName(anyString());
		verify(tourRepository, times(1)).findByPackageName(anyString());
		verify(tourRepository, times(1)).save(any(Tour.class));
		}

	@Test
	void testNegativeAddTour() {
		when(tourRepository.existsByPackageName(anyString())).thenReturn(false);
		when(tourRepository.save(any(Tour.class))).thenReturn(tour1);
		var res = customerServiceImpl.addTour(tourDto);
		assertEquals("Springfield", res.getStartLoc());
		assertEquals("Weekend Getaway", res.getPackageName());
		assertEquals(1500.00, res.getCost());
		assertEquals("Millennium Park", res.getLocationsCovered().get(1));
		verify(tourRepository, times(1)).existsByPackageName(anyString());
		verify(tourRepository, times(0)).findByPackageName(anyString());
		verify(tourRepository, times(1)).save(any(Tour.class));
		}
	
	@Test
	void testUpdateCustomer() {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer2));
		when(customerRepository.save(any(Customer.class))).thenReturn(customer2);
		var res = customerServiceImpl.updateCustomer(anyInt(),customerDto);
		assertEquals("Springfield", res.getCommunicationAdd().getCity());
		assertEquals("Weekend Getaway", res.getTour().getPackageName());
		assertEquals("Millennium Park", res.getTour().getLocationsCovered().get(1));
		verify(customerRepository, times(1)).save(any(Customer.class));
		verify(customerRepository,times(1)).findById(anyInt());
		}

	@Test
	void testNegativeUpdateCustomer() {
		when(customerRepository.findById(anyInt())).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class, ()->customerServiceImpl.updateCustomer(anyInt(),customerDto));		
		verify(customerRepository, times(1)).findById(anyInt());
	}
	
	@Test
	void testUpdateTour() {
		when(tourRepository.findById(anyInt())).thenReturn(Optional.of(tour2));
		when(tourRepository.save(any(Tour.class))).thenReturn(tour1);
		var res = customerServiceImpl.updateTour(anyInt(),tourDto);
		assertEquals("Springfield", res.getStartLoc());
		assertEquals("Weekend Getaway", res.getPackageName());
		assertEquals(1500.00, res.getCost());
		assertEquals("Millennium Park", res.getLocationsCovered().get(1));
		verify(tourRepository, times(1)).findById(anyInt());
		verify(tourRepository, times(1)).save(any(Tour.class));
	}
	
	@Test
	void testNegativeUpdateTour() {
		when(tourRepository.findById(anyInt())).thenThrow(TourNotFoundException.class);
		assertThrows(TourNotFoundException.class, ()->customerServiceImpl.updateTour(anyInt(),tourDto));		
		verify(tourRepository, times(1)).findById(anyInt());
	}

	
	
	@Test
	void testDeleteCustomer() {
		when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customer1));
		customerServiceImpl.deleteCustomer(anyInt());
		verify(customerRepository, times(1)).findById(anyInt());
		verify(addressRepository, times(2)).deleteById(anyInt());
		verify(customerRepository, times(1)).deleteById(anyInt());		
	}
	
	@Test
	void testNegativeDeleteCustomer() {
		when(customerRepository.findById(anyInt())).thenThrow(CustomerNotFoundException.class);
		assertThrows(CustomerNotFoundException.class, ()->customerServiceImpl.deleteCustomer(anyInt()));		
		verify(customerRepository, times(1)).findById(anyInt());
	}
	

}

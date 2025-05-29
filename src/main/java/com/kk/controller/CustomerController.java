	package com.kk.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kk.dtos.CustomerDto;
import com.kk.dtos.TourDto;
import com.kk.repository.AddressRepository;
import com.kk.service.CustomerService;
import com.kk.validations.Create;
import com.kk.validations.Update;

import jakarta.validation.Valid;

@RestController
@EnableMethodSecurity
@RequestMapping("/api/travel-agency")
public class CustomerController {

    private final AddressRepository addressRepository;
//	@Autowired
	private CustomerService customerService;

	public CustomerController(CustomerService customerService, AddressRepository addressRepository) {
		super();
		this.customerService = customerService;
		this.addressRepository = addressRepository;
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerDto>> getAllCustomers() {
		List<CustomerDto> customerDtos = customerService.getAll();
		return ResponseEntity.ok(customerDtos);
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable int id) {
		CustomerDto customerDto = customerService.getById(id);
		return ResponseEntity.ok(customerDto);
	}

	@GetMapping("/customers/lastname/{name}")
	public ResponseEntity<List<CustomerDto>> getCustomerByLastName(@PathVariable String name) {
		List<CustomerDto> customerDtos = customerService.getByLastName(name);
		return ResponseEntity.ok(customerDtos);
	}

	@GetMapping("/customers/tour/{name}")
	public ResponseEntity<List<CustomerDto>> getCustomerByPackage(@PathVariable String name) {
		List<CustomerDto> customerDtos = customerService.getByTour(name);
		return ResponseEntity.ok(customerDtos);
	}
	
	@GetMapping("/tours")
	public ResponseEntity<List<TourDto>> getAllTours()
	{
		List<TourDto> dtos = customerService.getAllTours();
		return ResponseEntity.ok(dtos);
	}
	@GetMapping("/tours/{name}")
	public ResponseEntity<TourDto> getTourByName(@PathVariable String name)
	{
		TourDto dto = customerService.getTourByName(name);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/customers")
	public ResponseEntity<CustomerDto> addCustomer(@Validated(Create.class) @RequestBody CustomerDto customerDto) {

		CustomerDto customer = customerService.addCustomer(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/tours")
	public ResponseEntity<TourDto> addTour(@Validated(Create.class) @RequestBody TourDto tourDto) {

		TourDto tourDto2 = customerService.addTour(tourDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(tourDto2);
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/customers/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable int id,@Validated(Update.class)@RequestBody CustomerDto customerDto) {

		CustomerDto customer = customerService.updateCustomer(id,customerDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/tours/{id}")
	public ResponseEntity<TourDto> updateTour(@PathVariable  int id,@Validated(Update.class) @RequestBody TourDto tourDto) {

		TourDto tourDto2 = customerService.updateTour(id,tourDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(tourDto2);
	}
	

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int id)
	{
		customerService.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}

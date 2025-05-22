package com.kk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.kk.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

//	@Autowired
	private CustomerRepository customerRepository;
//	@Autowired
	private TourRepository tourRepository;
//	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, TourRepository tourRepository,
			AddressRepository addressRepository) {
		super();
		this.customerRepository = customerRepository;
		this.tourRepository = tourRepository;
		this.addressRepository = addressRepository;
	}

	public CustomerServiceImpl(CustomerRepository customerRepository, TourRepository tourRepository) {
		super();
		this.customerRepository = customerRepository;
		this.tourRepository = tourRepository;
	}

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerDto> getAll() {
		List<Customer> customers = customerRepository.findAll();
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		for (Customer customer : customers) {
			AddressDto permanentAddressDto = new AddressDto(customer.getPermenantAdd().getId(),
					customer.getPermenantAdd().getHouseNo(), customer.getPermenantAdd().getStreet(),
					customer.getPermenantAdd().getLandMark(), customer.getPermenantAdd().getCity(),
					customer.getPermenantAdd().getState(), customer.getPermenantAdd().getPinCode());
			AddressDto communicationAddressDto = new AddressDto(customer.getCommunicationAdd().getId(),
					customer.getCommunicationAdd().getHouseNo(), customer.getCommunicationAdd().getStreet(),
					customer.getCommunicationAdd().getStreet(), customer.getCommunicationAdd().getCity(),
					customer.getCommunicationAdd().getState(), customer.getCommunicationAdd().getPinCode());
			TourDto tourDto = new TourDto(customer.getTourDetails().getId(), customer.getTourDetails().getStartLoc(),
					customer.getTourDetails().getCost(), customer.getTourDetails().getDestinationLoc(),
					customer.getTourDetails().getLocationsCovered(), customer.getTourDetails().getPackageName());
			CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
					customer.getPhone(), customer.getNotes(), permanentAddressDto, communicationAddressDto, tourDto);
			customerDtos.add(customerDto);
		}
		return customerDtos;
	}

	@Override
	public CustomerDto getById(int id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " not found"));
		AddressDto permanentAddressDto = new AddressDto(customer.getPermenantAdd().getId(),
				customer.getPermenantAdd().getHouseNo(), customer.getPermenantAdd().getStreet(),
				customer.getPermenantAdd().getLandMark(), customer.getPermenantAdd().getCity(),
				customer.getPermenantAdd().getState(), customer.getPermenantAdd().getPinCode());
		AddressDto communicationAddressDto = new AddressDto(customer.getCommunicationAdd().getId(),
				customer.getCommunicationAdd().getHouseNo(), customer.getCommunicationAdd().getStreet(),
				customer.getCommunicationAdd().getStreet(), customer.getCommunicationAdd().getCity(),
				customer.getCommunicationAdd().getState(), customer.getCommunicationAdd().getPinCode());
		TourDto tourDto = new TourDto(customer.getTourDetails().getId(), customer.getTourDetails().getStartLoc(),
				customer.getTourDetails().getCost(), customer.getTourDetails().getDestinationLoc(),
				customer.getTourDetails().getLocationsCovered(), customer.getTourDetails().getPackageName());
		CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
				customer.getPhone(), customer.getNotes(), permanentAddressDto, communicationAddressDto, tourDto);

		return customerDto;
	}

	@Override
	public List<CustomerDto> getByLastName(String lastName) {

		List<Customer> customers = customerRepository.findByLastName(lastName);
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		for (Customer customer : customers) {
			AddressDto permanentAddressDto = new AddressDto(customer.getPermenantAdd().getId(),
					customer.getPermenantAdd().getHouseNo(), customer.getPermenantAdd().getStreet(),
					customer.getPermenantAdd().getLandMark(), customer.getPermenantAdd().getCity(),
					customer.getPermenantAdd().getState(), customer.getPermenantAdd().getPinCode());
			AddressDto communicationAddressDto = new AddressDto(customer.getCommunicationAdd().getId(),
					customer.getCommunicationAdd().getHouseNo(), customer.getCommunicationAdd().getStreet(),
					customer.getCommunicationAdd().getStreet(), customer.getCommunicationAdd().getCity(),
					customer.getCommunicationAdd().getState(), customer.getCommunicationAdd().getPinCode());
			TourDto tourDto = new TourDto(customer.getTourDetails().getId(), customer.getTourDetails().getStartLoc(),
					customer.getTourDetails().getCost(), customer.getTourDetails().getDestinationLoc(),
					customer.getTourDetails().getLocationsCovered(), customer.getTourDetails().getPackageName());
			CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
					customer.getPhone(), customer.getNotes(), permanentAddressDto, communicationAddressDto, tourDto);
			customerDtos.add(customerDto);
		}
		return customerDtos;
	}

	@Override
	public List<CustomerDto> getByTour(String packageName) {
		List<Tour> packages = tourRepository.findByPackageName(packageName);
		List<Customer> customers = customerRepository.findByTourDetails(packages.getFirst());
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		for (Customer customer : customers) {
			AddressDto permanentAddressDto = new AddressDto(customer.getPermenantAdd().getId(),
					customer.getPermenantAdd().getHouseNo(), customer.getPermenantAdd().getStreet(),
					customer.getPermenantAdd().getLandMark(), customer.getPermenantAdd().getCity(),
					customer.getPermenantAdd().getState(), customer.getPermenantAdd().getPinCode());
			AddressDto communicationAddressDto = new AddressDto(customer.getCommunicationAdd().getId(),
					customer.getCommunicationAdd().getHouseNo(), customer.getCommunicationAdd().getStreet(),
					customer.getCommunicationAdd().getStreet(), customer.getCommunicationAdd().getCity(),
					customer.getCommunicationAdd().getState(), customer.getCommunicationAdd().getPinCode());
			TourDto tourDto = new TourDto(customer.getTourDetails().getId(), customer.getTourDetails().getStartLoc(),
					customer.getTourDetails().getCost(), customer.getTourDetails().getDestinationLoc(),
					customer.getTourDetails().getLocationsCovered(), customer.getTourDetails().getPackageName());
			CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),
					customer.getPhone(), customer.getNotes(), permanentAddressDto, communicationAddressDto, tourDto);
			customerDtos.add(customerDto);
		}
		return customerDtos;
	}

	@Override
	public List<TourDto> getAllTours() {
		List<TourDto> dtos = new ArrayList<TourDto>();
		List<Tour> tours = tourRepository.findAll();
		for (Tour tour : tours) {
			TourDto dto = new TourDto(tour.getId(), tour.getStartLoc(), tour.getCost(), tour.getDestinationLoc(),
					tour.getLocationsCovered(), tour.getPackageName());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public TourDto getTourByName(String name) {
		Tour tour = tourRepository.findByPackageName(name).get(0);
		TourDto dto = new TourDto(tour.getId(), tour.getStartLoc(), tour.getCost(), tour.getDestinationLoc(),
				tour.getLocationsCovered(), tour.getPackageName());
		return dto;
	}

	@Override
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Address permanentAddress = new Address(0, customerDto.getPermenantAdd().getHouseNo(),
				customerDto.getPermenantAdd().getStreet(), customerDto.getPermenantAdd().getLandMark(),
				customerDto.getPermenantAdd().getCity(), customerDto.getPermenantAdd().getState(),
				customerDto.getPermenantAdd().getPinCode());
		Address communicationAddress = new Address(0, customerDto.getCommunicationAdd().getHouseNo(),
				customerDto.getCommunicationAdd().getStreet(), customerDto.getCommunicationAdd().getLandMark(),
				customerDto.getCommunicationAdd().getCity(), customerDto.getCommunicationAdd().getState(),
				customerDto.getCommunicationAdd().getPinCode());
		Tour addedTour = new Tour(0, customerDto.getTour().getStartLoc(), customerDto.getTour().getCost(),
				customerDto.getTour().getDestinationLoc(), customerDto.getTour().getLocationsCovered(),
	   			customerDto.getTour().getPackageName());
		if (tourRepository.existsByPackageName(customerDto.getTour().getPackageName())) {
			addedTour = tourRepository.findByPackageName(customerDto.getTour().getPackageName()).get(0);
		}
		tourRepository.save(addedTour);
		Customer addedCustomer = new Customer(0, customerDto.getFirstName(), customerDto.getLastName(),
				customerDto.getPhone(), customerDto.getNotes(), permanentAddress, communicationAddress, addedTour);
		Customer customer = customerRepository.save(addedCustomer);
		AddressDto permanentAddressDto = new AddressDto(customer.getPermenantAdd().getId(),
				customer.getPermenantAdd().getHouseNo(), customer.getPermenantAdd().getStreet(),
				customer.getPermenantAdd().getLandMark(), customer.getPermenantAdd().getCity(),
				customer.getPermenantAdd().getState(), customer.getPermenantAdd().getPinCode());
		AddressDto communicationAddressDto = new AddressDto(customer.getCommunicationAdd().getId(),
				customer.getCommunicationAdd().getHouseNo(), customer.getCommunicationAdd().getStreet(),
				customer.getCommunicationAdd().getStreet(), customer.getCommunicationAdd().getCity(),
				customer.getCommunicationAdd().getState(), customer.getCommunicationAdd().getPinCode());
		TourDto tourDto = new TourDto(customer.getTourDetails().getId(), customer.getTourDetails().getStartLoc(),
				customer.getTourDetails().getCost(), customer.getTourDetails().getDestinationLoc(),
				customer.getTourDetails().getLocationsCovered(), customer.getTourDetails().getPackageName());
		CustomerDto addedCustomerDto = new CustomerDto(customer.getId(), customer.getFirstName(),
				customer.getLastName(), customer.getPhone(), customer.getNotes(), permanentAddressDto,
				communicationAddressDto, tourDto);
		return addedCustomerDto;
	}

	@Override
	public TourDto addTour(TourDto tourDto) {
		Tour tour = new Tour(0, tourDto.getStartLoc(), tourDto.getCost(), tourDto.getDestinationLoc(),
				tourDto.getLocationsCovered(), tourDto.getPackageName());
		if (tourRepository.existsByPackageName(tourDto.getPackageName())) {
			tour = tourRepository.findByPackageName(tourDto.getPackageName()).get(0);
		}
		Tour addedTour = tourRepository.save(tour);
		TourDto dto = new TourDto(addedTour.getId(), addedTour.getStartLoc(), addedTour.getCost(),
				addedTour.getDestinationLoc(), addedTour.getLocationsCovered(), addedTour.getPackageName());
		return dto;
	}

	private Address updateAddress(AddressDto updatedAdd, Address currentAdd) {
		if (updatedAdd.getHouseNo() != null) {
			currentAdd.setHouseNo(updatedAdd.getHouseNo());
		}
		if (updatedAdd.getStreet() != null) {
			currentAdd.setStreet(updatedAdd.getStreet());
		}
		if (updatedAdd.getLandMark() != null) {
			currentAdd.setLandMark(updatedAdd.getLandMark());
		}
		if (updatedAdd.getCity() != null) {
			currentAdd.setCity(updatedAdd.getCity());
		}
		if (updatedAdd.getState() != null) {
			currentAdd.setState(updatedAdd.getState());
		}
		if (updatedAdd.getPinCode() != 0) {
			currentAdd.setPinCode(updatedAdd.getPinCode());
		}
		return currentAdd;
	}

	@Override
	public CustomerDto updateCustomer(int id, CustomerDto customerDto) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with id" + id + " is not found"));
		Address updatedPermanentAddress = updateAddress(customerDto.getPermenantAdd(), customer.getPermenantAdd());
		Address updatedCommunicationAddress = updateAddress(customerDto.getCommunicationAdd(),
				customer.getCommunicationAdd());

		TourDto tourDto = customerDto.getTour();
		Tour tour = customer.getTourDetails();

		if (tourDto.getStartLoc() != null) {
			tour.setStartLoc(tourDto.getStartLoc());
		}
		if (tourDto.getDestinationLoc() != null) {
			tour.setDestinationLoc(tourDto.getDestinationLoc());
		}
		if (tourDto.getCost() != 0) {
			tour.setCost(tourDto.getCost());
		}
		if (tourDto.getPackageName() != null) {
			tour.setPackageName(tourDto.getPackageName());
		}
		if (tourDto.getLocationsCovered() != null) {
			tour.setLocationsCovered(tourDto.getLocationsCovered());
		}

		if (customerDto.getFirstName() != null) {
			customer.setFirstName(customerDto.getFirstName());
		}
		if (customerDto.getLastName() != null) {
			customer.setLastName(customerDto.getLastName());
		}
		if (customerDto.getPhone() != null) {
			customer.setPhone(customerDto.getPhone());
		}
		if (customerDto.getNotes() != null) {
			customer.setNotes(customerDto.getNotes());
		}
		customer.setPermenantAdd(updatedPermanentAddress);
		customer.setCommunicationAdd(updatedCommunicationAddress);
		customer.setTourDetails(tour);
		Customer updatedCustomer = customerRepository.save(customer);

		AddressDto permantentAddDto = new AddressDto(updatedCustomer.getPermenantAdd().getId(),
				updatedCustomer.getPermenantAdd().getHouseNo(), updatedCustomer.getPermenantAdd().getStreet(),
				updatedCustomer.getPermenantAdd().getLandMark(), updatedCustomer.getPermenantAdd().getCity(),
				updatedCustomer.getPermenantAdd().getState(), updatedCustomer.getPermenantAdd().getPinCode());
		AddressDto communicationAddDto = new AddressDto(updatedCustomer.getCommunicationAdd().getId(),
				updatedCustomer.getCommunicationAdd().getHouseNo(), updatedCustomer.getCommunicationAdd().getStreet(),
				updatedCustomer.getCommunicationAdd().getLandMark(), updatedCustomer.getCommunicationAdd().getCity(),
				updatedCustomer.getCommunicationAdd().getState(), updatedCustomer.getCommunicationAdd().getPinCode());
		TourDto updatedTourDto = new TourDto(updatedCustomer.getTourDetails().getId(),
				updatedCustomer.getTourDetails().getStartLoc(), updatedCustomer.getTourDetails().getCost(),
				updatedCustomer.getTourDetails().getDestinationLoc(),
				updatedCustomer.getTourDetails().getLocationsCovered(),
				updatedCustomer.getTourDetails().getPackageName());
		CustomerDto updatedCustomerDto = new CustomerDto(updatedCustomer.getId(), updatedCustomer.getFirstName(),
				updatedCustomer.getLastName(), updatedCustomer.getPhone(), updatedCustomer.getNotes(), permantentAddDto,
				communicationAddDto, updatedTourDto);

		return updatedCustomerDto;
	}

	@Override
	public TourDto updateTour(int id, TourDto tourDto) {
		Tour tour = tourRepository.findById(id)
				.orElseThrow(() -> new TourNotFoundException("TourDetails with id " + id + " is not found"));
		if (tourDto.getStartLoc() != null) {
			tour.setStartLoc(tourDto.getStartLoc());
		}
		if (tourDto.getDestinationLoc() != null) {
			tour.setDestinationLoc(tourDto.getDestinationLoc());
		}
		if (tourDto.getLocationsCovered() != null) {
			tour.setLocationsCovered(tourDto.getLocationsCovered());
		}
		if (tourDto.getCost() != 0) {
			tour.setCost(tourDto.getCost());
		}
		if (tourDto.getPackageName() != null) {
			tour.setPackageName(tourDto.getPackageName());
		}
		Tour updatedTour = tourRepository.save(tour);
		TourDto updatedTourDto = new TourDto(updatedTour.getId(), updatedTour.getStartLoc(), updatedTour.getCost(),
				updatedTour.getDestinationLoc(), updatedTour.getLocationsCovered(), updatedTour.getPackageName());
		return updatedTourDto;

	}

	@Override
	public void deleteCustomer(int id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with id" + id + " is not found"));
		// deleting addresses related to customer
		addressRepository.deleteById(customer.getPermenantAdd().getId());
		addressRepository.deleteById(customer.getCommunicationAdd().getId());
		customerRepository.deleteById(id);
	}

}

package com.kk.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.dtos.AddressDto;
import com.kk.dtos.CustomerDto;
import com.kk.dtos.TourDto;
import com.kk.service.impl.CustomerServiceImpl;
@AutoConfigureMockMvc
@SpringBootTest
class CustomerControllerTest {

	@Mock
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private CustomerServiceImpl customerServiceImpl;
	@Autowired
	private ObjectMapper objectMapper;
	
	CustomerDto customer1;
	CustomerDto customer2;
	CustomerDto customer3;
	List<CustomerDto> customers = new ArrayList<CustomerDto>();
	TourDto tour1;
	TourDto tour2;
	TourDto  tour3;
	List<TourDto> tours = new ArrayList<TourDto>();
	@BeforeEach
	void setUp() {
		// Customer 1
		AddressDto permanentAddress1 = new AddressDto(1,"123", "Main St", "Near Park", "Springfield", "IL", 62701);
		AddressDto communicationAddress1 = new AddressDto(2,"123", "Main St", "Near Park", "Springfield", "IL", 62701); 
		tour1 = new TourDto(1, "Springfield", 1500.00, "Chicago",
				Arrays.asList("Lincoln Home", "Millennium Park"), "Weekend Getaway");
		customer1 = new CustomerDto(1, "John", "Doe", "1234567890", "Prefers window seat", permanentAddress1, communicationAddress1,
				tour1);
 
		// Customer 2
		AddressDto permanentAddress2 = new AddressDto(3,"456", "Oak Ave", "Beside Lake", "Shelbyville", "KY", 40065);
		AddressDto communicationAddress2 = new AddressDto(4,"789", "Maple Dr", "Downtown", "Shelbyville", "KY", 40065);
		tour2 = new TourDto(2, "Shelbyville", 2500.00, "Miami",
				Arrays.asList("South Beach", "Art Deco District"), "Beach Fun");
		customer2 = new CustomerDto(2, "Jane", "Smith", "0987654321", "Allergic to nuts", permanentAddress2, communicationAddress2,
				tour2);
 
		// Customer 3
		AddressDto permanentAddress3 = new AddressDto(5,"789", "Pine Ln", "Opposite Mall", "Capital City", "NY", 12201);
		AddressDto communicationAddress3 = new AddressDto(6,"101", "Elm Rd", "Near Library", "Capital City", "NY", 12201);
		tour3 = new TourDto(3, "Capital City", 3500.00, "San Francisco",
				Arrays.asList("Golden Gate Bridge", "Alcatraz"), "City Explorer");
		customer3 = new CustomerDto(3, "Peter", "Jones", "1122334455", "Needs wheelchair access", permanentAddress3, communicationAddress3,
				tour3);
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		tours.add(tour1);
		tours.add(tour2);
		tours.add(tour3);
	}
		
	@Test
	@WithMockUser
	void testGetAllCustomers() throws Exception {
		when(customerServiceImpl.getAll()).thenReturn(customers);
		mockMvc.perform(get("/api/travel-agency/customers"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()").value(3))
		.andExpect(jsonPath("$[0].firstName").value("John"))
		.andExpect(jsonPath("$[1].permenantAdd.houseNo").value("456"))
		.andExpect(jsonPath("$[2].tour.packageName").value("City Explorer"));
		verify(customerServiceImpl,times(1)).getAll();
	}
	@Test
	@WithMockUser
	void testGetCustomerById() throws Exception {
		when(customerServiceImpl.getById(anyInt())).thenReturn(customer1);
		mockMvc.perform(get("/api/travel-agency/customers/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.firstName").value("John"))
		.andExpect(jsonPath("$.permenantAdd.houseNo").value("123"))
		.andExpect(jsonPath("$.tour.packageName").value("Weekend Getaway"));
		verify(customerServiceImpl,times(1)).getById(anyInt());
	}
	@Test
	@WithMockUser
	void testGetCustomerByLastName() throws Exception {
		when(customerServiceImpl.getByLastName(anyString())).thenReturn(customers);
		mockMvc.perform(get("/api/travel-agency/customers/lastname/Doe"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].firstName").value("John"))
		.andExpect(jsonPath("$[0].permenantAdd.houseNo").value("123"))
		.andExpect(jsonPath("$[0].tour.packageName").value("Weekend Getaway"));
		verify(customerServiceImpl,times(1)).getByLastName(anyString());
	}

	@Test
	@WithMockUser
	void testGetCustomerByPackage() throws Exception {
		when(customerServiceImpl.getByTour(anyString())).thenReturn(customers);
		mockMvc.perform(get("/api/travel-agency/customers/tour/Weekend Getaway"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].firstName").value("John"))
		.andExpect(jsonPath("$[0].permenantAdd.houseNo").value("123"))
		.andExpect(jsonPath("$[0].tour.packageName").value("Weekend Getaway"));
		verify(customerServiceImpl,times(1)).getByTour(anyString());
	}

	@Test
	@WithMockUser
	void testGetAllTours() throws Exception {
		when(customerServiceImpl.getAllTours()).thenReturn(tours);
		mockMvc.perform(get("/api/travel-agency/tours"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].startLoc").value("Springfield"))
		.andExpect(jsonPath("$[1].locationsCovered[0]").value("South Beach"))
		.andExpect(jsonPath("$[2].packageName").value("City Explorer"));
		verify(customerServiceImpl,times(1)).getAllTours();
	}

	@Test
	@WithMockUser
	void testGetTourByName() throws Exception {
		when(customerServiceImpl.getTourByName(anyString())).thenReturn(tour1);
		mockMvc.perform(get("/api/travel-agency/tours/Weekend Getaway"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.startLoc").value("Springfield"))
		.andExpect(jsonPath("$.locationsCovered[0]").value("Lincoln Home"))
		.andExpect(jsonPath("$.destinationLoc").value("Chicago"));
		verify(customerServiceImpl,times(1)).getTourByName(anyString());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void testAddCustomer() throws Exception {
		when(customerServiceImpl.addCustomer(any(CustomerDto.class))).thenReturn(customer1);
		var jsonObj=objectMapper.writeValueAsString(customer1);
		mockMvc.perform(post("/api/travel-agency/customers").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.firstName").value("John"))
		.andExpect(jsonPath("$.permenantAdd.houseNo").value("123"))
		.andExpect(jsonPath("$.tour.packageName").value("Weekend Getaway"));
		verify(customerServiceImpl,times(1)).addCustomer(any(CustomerDto.class));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void testAddTour() throws Exception {
		when(customerServiceImpl.addTour(any(TourDto.class))).thenReturn(tour1);
		var jsonObj=objectMapper.writeValueAsString(tour1);
		mockMvc.perform(post("/api/travel-agency/tours").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.startLoc").value("Springfield"))
		.andExpect(jsonPath("$.locationsCovered[0]").value("Lincoln Home"))
		.andExpect(jsonPath("$.destinationLoc").value("Chicago"));
		verify(customerServiceImpl,times(1)).addTour(any(TourDto.class));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void testUpdateCustomer() throws Exception {
		when(customerServiceImpl.updateCustomer(anyInt(),any(CustomerDto.class))).thenReturn(customer1);
		var jsonObj=objectMapper.writeValueAsString(customer1);
		mockMvc.perform(put("/api/travel-agency/customers/2").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.firstName").value("John"))
		.andExpect(jsonPath("$.permenantAdd.houseNo").value("123"))
		.andExpect(jsonPath("$.tour.packageName").value("Weekend Getaway"));
		verify(customerServiceImpl,times(1)).updateCustomer(anyInt(), any(CustomerDto.class));	
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void testUpdateTour() throws Exception {
		when(customerServiceImpl.updateTour(anyInt(),any(TourDto.class))).thenReturn(tour1);
		var jsonObj=objectMapper.writeValueAsString(tour1);
		mockMvc.perform(put("/api/travel-agency/tours/2").content(jsonObj).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.startLoc").value("Springfield"))
		.andExpect(jsonPath("$.locationsCovered[0]").value("Lincoln Home"))
		.andExpect(jsonPath("$.destinationLoc").value("Chicago"));
		verify(customerServiceImpl,times(1)).updateTour(anyInt(), any(TourDto.class));
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void testDeleteCustomer() throws Exception {
		mockMvc.perform(delete("/api/travel-agency/customers/2"))
		.andExpect(status().isAccepted());
		verify(customerServiceImpl,times(1)).deleteCustomer(anyInt());	
	}

}

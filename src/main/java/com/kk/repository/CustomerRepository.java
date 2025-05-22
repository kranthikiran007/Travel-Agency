package com.kk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.entity.Customer;
import com.kk.entity.Tour;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
	List<Customer> findByLastName(String secondName);
	List<Customer> findByTourDetails(Tour tourDetails);
}

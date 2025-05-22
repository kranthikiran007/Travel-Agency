package com.kk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>{

}

package com.kk.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role>findByName(String name);
	
	boolean existsByName(String name);
	

}

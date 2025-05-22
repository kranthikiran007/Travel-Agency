package com.kk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kk.entity.Tour;
import java.util.List;


public interface TourRepository extends JpaRepository<Tour,Integer>{
		List<Tour> findByPackageName(String packageName);
		boolean existsByPackageName(String packageName);
	
}

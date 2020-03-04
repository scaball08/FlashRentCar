package com.test.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.test.models.entity.Car;

public interface CarDao extends JpaRepository<Car, Long> {
	
	
	@Query("select c from Car c where c.rentado = ?1")
	public List<Car> findAllDisp(boolean rentado);
	
	@Query("select c  from Car c where c.id = ?1 and c.rentado = ?2")
	public Car findOneByDisp(Long id, boolean rentado );

}

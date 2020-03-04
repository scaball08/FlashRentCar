package com.test.models.services;

import java.util.List;

import com.test.models.entity.Car;

public interface ICarService {
	
	
	public List<Car> findAll();
	
	public Car findById(long id);
	
	public Car save(Car car);
	
	public void delete(long id); 
	
	public List<Car> findAll2(boolean rentado);
	
	public Car findForDisp(Long id, boolean disp );
	

}

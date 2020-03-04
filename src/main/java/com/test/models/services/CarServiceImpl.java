package com.test.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.models.dao.CarDao;
import com.test.models.entity.Car;

@Service
public class CarServiceImpl implements ICarService {
	
	@Autowired
	private CarDao carDao;

	@Override
	@Transactional(readOnly = true)
	public List<Car> findAll() {
		// listar carros
		return carDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Car findById(long id) {
		// buscar carro por id
		return carDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Car save(Car car) {
		// guardar un carro
		return carDao.save(car);
	}

	@Override
	@Transactional
	public void delete(long id) {
		// borrar carro
		carDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Car> findAll2(boolean rentado) {
		// buscar listado de carros disponibles
		return carDao.findAllDisp(rentado);
	}

	@Override
	@Transactional(readOnly = true)
	public Car findForDisp(Long id, boolean disp) {
		// buscar un auto disponible
		return carDao.findOneByDisp(id, disp);
	}

}

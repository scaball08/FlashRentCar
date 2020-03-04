package com.test.models.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.models.dao.IOrdenRentaDao;
import com.test.models.entity.OrdenRenta;

@Service
public class OrdenServiceImpl implements IOrdenService{
	
	@Autowired
	private IOrdenRentaDao ordenDao;
	
	@Autowired
	private EntityManager procManager;

	@Override
	@Transactional(readOnly = true)
	public List<OrdenRenta> findAll() {
		// listar ordenes
		return ordenDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenRenta findById(long id) {
		// buscar por id
		return ordenDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public OrdenRenta save(OrdenRenta orden) {
		// crear orden
		return ordenDao.save(orden);
	}

	@Override
	@Transactional
	public void delete(long id) {
		// borrar orden por id de orden
		ordenDao.deleteById(id);
		
	}

	@Override
	@Transactional
	public void procUsuarioOrden(Long id_usuario,Long id_orden ) {
		// crea relacion entre orden y usuario
		// createNamedStoredProcedureQuery("crear_orden") : crea una intancia de StoredProcedureQuery para ejecutar un SP
		StoredProcedureQuery procQuery =  procManager.createNamedStoredProcedureQuery("crear_orden");	
	
	
		if(id_usuario!=null && id_orden!=null) {
			procQuery.setParameter("i_id_usuario", id_usuario);
			procQuery.setParameter("i_id_orden", id_orden);
			procQuery.execute();
			
		}
		
	}

	@Override
	@Transactional
	public void procUpdCarDisp(Long id_car) {
		StoredProcedureQuery procQueryCar = procManager.createStoredProcedureQuery("upd_car_disp");
		
		if(id_car!=null) {
			procQueryCar.setParameter("i_id_car", id_car);
			procQueryCar.execute();
		}
	}

}

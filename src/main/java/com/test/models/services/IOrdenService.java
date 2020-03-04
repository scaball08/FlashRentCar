package com.test.models.services;

import java.util.List;

import com.test.models.entity.OrdenRenta;

public interface IOrdenService {
	
	public List<OrdenRenta> findAll();
	
	public OrdenRenta findById(long id);
	
	public OrdenRenta save(OrdenRenta orden);
	
	public void delete(long id);
	
	public void procUsuarioOrden(Long id_usuario,Long id_orden );
	public void procUpdCarDisp(Long id_car);

}

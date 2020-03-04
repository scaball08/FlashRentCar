package com.test.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.models.entity.OrdenRenta;
import com.test.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	
	@Query("from OrdenRenta")
	public List<OrdenRenta> findByOrdenes();
	
	

}

package com.test.models.services;

import java.util.List;

import com.test.models.entity.OrdenRenta;
import com.test.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Usuario findById(long id);
	
	public Usuario save(Usuario usuario);
	
	public void  delete(long id);
	
	public List<OrdenRenta> findAllOrdenRenta(); 

}

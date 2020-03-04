package com.test.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.models.dao.IUsuarioDao;
import com.test.models.entity.OrdenRenta;
import com.test.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioDao  usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// listar usuarios
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(long id) {
		// Busqueda por id
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		// guardar o actualizar usuario
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(long id) {
		// borrar usuario
		
		usuarioDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrdenRenta> findAllOrdenRenta() {
		// listar ordenes de rante
		return usuarioDao.findByOrdenes();
	}

}

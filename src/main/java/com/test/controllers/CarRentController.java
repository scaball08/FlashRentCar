package com.test.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.models.entity.Car;
import com.test.models.entity.OrdenRenta;
import com.test.models.entity.UserLogin;
import com.test.models.entity.Usuario;
import com.test.models.services.ICarService;
import com.test.models.services.IOrdenService;
import com.test.models.services.IUserService;
import com.test.models.services.IUsuarioService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CarRentController {
	
	@Autowired
	private ICarService carService;
	
	@Autowired
	private IUsuarioService usuarioService; 
	
	@Autowired
	private IUserService userService; 
	
	@Autowired
	private IOrdenService ordenservice;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/cars")
	public List<Car> index(){
		
		return carService.findAll2(true);
	}
	
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/usuario/{id}")
	public Usuario show(@PathVariable long id){
		
		return usuarioService.findById(id);
	}
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/usuario")
	public ResponseEntity<?> create(@Valid @RequestBody UserLogin usuario,BindingResult result) {

		UserLogin usernew = null;
		Usuario usuarioNew = null;
		Map<String,Object> response = new HashMap<String,Object>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
			        .map(err->"El campo '" + err.getField()+ "' " + err.getDefaultMessage()) 
			        .collect(Collectors.toList()); 
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);// 404			
			
		}
		
		usernew =  userService.findByUsername(usuario.getUsername());
		if(usernew != null) {
			response.put("error", "Ya existe usuario registrado con ese nombre de usuario");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);// 500	
			
		}
			
			
			try {
				usuarioNew = usuarioService.save(usuario.getUsuario());
				usuario.setUsuario(usuarioNew);
				usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
				usernew =  userService.save(usuario);
				
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);// 500			
				
				
			}
		
		
		
		
		response.put("mesanje", "El Cliente se ha creado con exito");
		response.put("cliente", usernew.getUsuario());
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);  // 201 created
	}
	
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/orden/{id_usuario}")
	public ResponseEntity<?> createOrder(@Valid @RequestBody OrdenRenta orden,BindingResult result, @PathVariable long id_usuario) {

		OrdenRenta ordenNew = null;
		Usuario usuarioactual = null;
		Car carroDisp =  orden.getAuto();
		Map<String,Object> response = new HashMap<String,Object>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
			        .map(err->"El campo '" + err.getField()+ "' " + err.getDefaultMessage()) 
			        .collect(Collectors.toList()); 
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);// 404			
			
		}
		
		// Se verifica la existencia del id del usuario en la peticion DB
		usuarioactual =  usuarioService.findById(id_usuario);
		if(usuarioactual == null) {
			response.put("error", "No existe usuario registrado,registrece");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);// 500	
			
		}
		
		// Se verifica la existencia del id del Auto en la peticion a la DB
				carroDisp =  carService.findForDisp(carroDisp.getId(), true);
				if(carroDisp == null) {
					response.put("error", "No existe Auto registrado en la DB");
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);// 500	
					
				}
		
		
			
			
			try {
				ordenNew = ordenservice.save(orden);
				
				if(ordenNew.getId()>0) {
					ordenservice.procUsuarioOrden(usuarioactual.getId(), ordenNew.getId());
					ordenservice.procUpdCarDisp(carroDisp.getId());
				}
				
				
			} catch (DataAccessException e) {
				response.put("mensaje", "Error al realizar el insert en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);// 500			
				
				
			}
		
		
		
		
		response.put("mesanje", "La Orden se ha creado con exito");
		response.put("orden", ordenNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);  // 201 created
	}

}

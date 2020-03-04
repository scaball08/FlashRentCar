package com.test.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "nombre")
	private String nombre;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "apellido")
	private String apellido;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "cedula_pasaport",unique = true)
	private String cedula_pasaport;

	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "no_licencia")
	private String no_licencia;

	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "telefono")
	private String telefono;

	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "celular")
	private String celular; 
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable( name="usuarios_ordenes",joinColumns = @JoinColumn(name="usuario_id") ,
	inverseJoinColumns = @JoinColumn(name="orden_id"),uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","orden_id"})})
	private List<OrdenRenta> ordenes;
	
	
	public List<OrdenRenta> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenRenta> ordenes) {
		this.ordenes = ordenes;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula_pasaport() {
		return cedula_pasaport;
	}

	public void setCedula_pasaport(String cedula_pasaport) {
		this.cedula_pasaport = cedula_pasaport;
	}

	public String getNo_licencia() {
		return no_licencia;
	}

	public void setNo_licencia(String no_licencia) {
		this.no_licencia = no_licencia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

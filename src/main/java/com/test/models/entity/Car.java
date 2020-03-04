package com.test.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;




@Entity
@Table(name="carros")
public class Car implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "modelo")
	private String modelo;
	

	@NotEmpty(message = "No puede estar vacio")
	@Column(name = "marca")
	private String marca;
	
	@NotEmpty(message="No puede estar  vacio")
	@Column(name="anio")
	private String anio;
	
	@NotNull(message = "No pude ser nulo o vacio")
	@Column(name = "precio_x_dia")
	private double precio_x_dia;
	
	@NotNull(message="no puede ser nulo o vacio")
	@Column(name = "precio")
	private double precio; 
	
	@NotEmpty(message="No puede estar vacio")
	@Column(name = "rentado")
	private boolean rentado;
	
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public boolean getRentado() {
		return rentado;
	}

	public void setRentado(boolean rentado) {
		this.rentado = rentado;
	}

	public double getPrecio_x_dia() {
		return precio_x_dia;
	}

	public void setPrecio_x_dia(double precio_x_dia) {
		this.precio_x_dia = precio_x_dia;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

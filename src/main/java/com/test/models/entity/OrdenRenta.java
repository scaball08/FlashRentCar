package com.test.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="ordenes_renta")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name="crear_orden",
		procedureName = "crear_orden",
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "i_id_usuario",type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "i_id_orden",type = Long.class)
		
		} ),
	@NamedStoredProcedureQuery(
			name="upd_car_disp",
			procedureName = "upd_car_disp",
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.IN, name = "i_id_car",type = Long.class)
			
			} )
	})
public class OrdenRenta implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name="fecha_retiro")
	@NotNull(message = "No puede estar vacia")
	@Temporal(TemporalType.DATE)
	private Date fecha_retiro;
	
	
	@Column(name="fecha_entrega")
	@NotNull(message = "No puede estar vacia")
	@Temporal(TemporalType.DATE)
	private Date fecha_entrega;
	
	@NotNull(message = "No puede estar vacio")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_auto")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Car auto;
	
	@NotNull(message = "No puede estar vacio o nulo")
	@Column(name = "precio_total")
	private double precio_total;
	
	@NotNull(message = "No puede estar vacio o nulo")
	@Column(name = "precio_unitario")
	private double precio_unitario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha_retiro() {
		return fecha_retiro;
	}

	public void setFecha_retiro(Date fecha_retiro) {
		this.fecha_retiro = fecha_retiro;
	}

	public Date getFecha_entrega() {
		return fecha_entrega;
	}

	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}

	public Car getAuto() {
		return auto;
	}

	public void setAuto(Car auto) {
		this.auto = auto;
	}

	public double getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

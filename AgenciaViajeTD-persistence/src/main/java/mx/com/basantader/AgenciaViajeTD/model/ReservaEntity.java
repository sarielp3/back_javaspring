package mx.com.basantader.AgenciaViajeTD.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Entity
@Table(name="RESERVACIONES")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor


public class ReservaEntity implements Serializable{
	
	@Id
	@SequenceGenerator(name = "seq", sequenceName = "RESERVAS_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "seq")
	private Long idReserva;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_HOTEL")
	private HotelEntity hotel;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUARTO")
	private CuartoEntity cuarto;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VUELO")
	private VueloEntity vuelo;
	
	@Column(name = "FECHA_INICIO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@Column(name = "FECHA_FIN", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	
	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;
	
	@Column(name = "NOMBRE_CLIENTE", nullable = false)
	private String nombreCliente;
	
	@Column(name = "APELLIDO_PATERNO", nullable = false)
	private String apellidoPaternoCliente;
	
	@Column(name = "APELLIDO_MATERNO", nullable = false)
	private String apellidoMaternoCliente;
	
}

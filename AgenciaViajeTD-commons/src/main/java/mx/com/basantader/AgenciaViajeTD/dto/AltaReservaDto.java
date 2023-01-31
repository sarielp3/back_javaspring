package mx.com.basantader.AgenciaViajeTD.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AltaReservaDto {
	
	private Long idReserva;
	
	@NotNull(message = "La fecha de inicio no puede ser nula")
	@Future
	private Date fechaInicio;
	
	@NotNull(message = "La fecha de fin no puede ser nula")
	@Future
	private Date fechaFin;
	
	@NotNull(message = "El nombre del cliente no puede ser nulo")
	private String nombreCliente;
	
	@NotNull(message = "El apellido paterno del cliente no puede ser nulo")
	private String apellidoPaternoCliente;
	
	@NotNull(message = "El apellido materno del cliente no puede ser nulo")
	private String apellidoMaternoCliente;
	
	@NotNull(message = "La descripcion no puede ser nula")
	private String descripcion;

	private Date fechaCreacion;
	
	@NotNull(message = "El id del hotel no puede ser nulo")
	private Long idHotel;
	
	@NotNull(message = "El id del cuarto no puede ser nul")
	private Long idCuarto;
	
	@NotNull(message = "El id del vuelo no puede ser nulo")
	private Long idVuelo;
	

}

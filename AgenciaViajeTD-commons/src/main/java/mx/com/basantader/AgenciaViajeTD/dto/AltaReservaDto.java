package mx.com.basantader.AgenciaViajeTD.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class AltaReservaDto {
	
	private Date fechaInicio;
	private Date fechaFin;
	private Long idReserva;
	private String nombreCliente;
	private String apellidoPaternoCliente;
	private String apellidoMaternoCliente;
	private String descripcion;
	private Date fechaCreacion;
	
	@NotNull(message = "Por favor ingrese el id del hotel")
	private Long idHotel;
	
	@Min(value = 0, message = "mayor a 0")
	private Long idCuarto;
	
	
	private Long idVuelo;
	

}

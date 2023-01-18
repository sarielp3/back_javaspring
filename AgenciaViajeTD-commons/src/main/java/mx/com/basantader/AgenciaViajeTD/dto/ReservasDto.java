package mx.com.basantader.AgenciaViajeTD.dto;

import java.util.Date;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservasDto {
	
	private Date fechaInicio;
	private Date fechaFin;
	private String descripcion;
	private String nombreCliente;
	private String apellidoPaternoCliente;
	private String apellidoMaternoCliente;
	private Long idReserva;
	private Long Hotel;
	private Long Cuarto;
	private Long Vuelo;
	
	
	

}

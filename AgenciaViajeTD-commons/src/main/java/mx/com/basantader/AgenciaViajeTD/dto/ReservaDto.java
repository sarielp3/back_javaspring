package mx.com.basantader.AgenciaViajeTD.dto;

import java.util.Date;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ReservaDto {
	
	private Date fechaInicio;
	private Date fechaFin;
	private Long idReserva;
	private String nombreCliente;
	private String apellidoPaternoCliente;
	private String apellidoMaternoCliente;
	private String descripcion;
	private HotelDto hotel;
	private CuartoDto cuarto;
	private VueloDto vuelo;
	private Date fechaCreacion;
	
	
	private Long idHotel;
	
	
	private Long idCuarto;
	
	
	private Long idVuelo;
	

}

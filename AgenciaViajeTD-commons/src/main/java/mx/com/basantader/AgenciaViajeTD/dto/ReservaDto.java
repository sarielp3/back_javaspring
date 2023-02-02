package mx.com.basantader.AgenciaViajeTD.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto implements Serializable{
	
	private Long idReserva;
	private VueloDto vuelo;
	private CuartoDto cuarto;
	private String nombreCliente;
	private String apellidoPaternoCliente;
	private String apellidoMaternoCliente;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaInicio;
	private Date fechaFin;

}

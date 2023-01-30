package mx.com.basantader.AgenciaViajeTD.dto;

import java.util.Date;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDto {
	
	private VueloDto vuelo;
	private String nombreCliente;
	private String apellidoPaternoCliente;
	private String apellidoMaternoCliente;
	private String descripcion;
	private Date fechaCreacion;

}

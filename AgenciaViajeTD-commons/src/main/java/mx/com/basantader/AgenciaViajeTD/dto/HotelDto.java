package mx.com.basantader.AgenciaViajeTD.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDto implements Serializable {
	
	private Long idHotel;
	
	@NotNull(message = "La ciudad no puede ser nula")
	private CiudadDto ciudad;
	
	@NotNull(message = "Nombre de hotel necesario")
	@NotBlank(message = "El nombre de hotel no puede estar vacio")
	private String nombreHotel;
	
	@NotNull(message = "El codigo del hotel no puede ser nulo")
	@NotBlank(message = "El codigo del hotel no puede estar vacio")
	private String codigoHotel;
	
	@NotNull(message = "La direccion no puede ser nula")
	@NotBlank(message = "La direccion no puede estar en blanco")
	private String direccion;
	
	private int estatus;
	
	private byte[] logo;
	

}


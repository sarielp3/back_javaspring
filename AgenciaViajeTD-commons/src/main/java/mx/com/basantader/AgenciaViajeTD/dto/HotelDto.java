package mx.com.basantader.AgenciaViajeTD.dto;

import java.io.Serializable;

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
	
	private CiudadDto ciudad;
	
	private String nombreHotel;
	
	private String codigoHotel;
	
	private String direccion;
	
	private int estatus;
	
	private byte[] logo;
	

}


package mx.com.basantader.AgenciaViajeTD.dto;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Base64;

import com.google.common.primitives.Bytes;

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

	private String estatus;

	private String logo;

}

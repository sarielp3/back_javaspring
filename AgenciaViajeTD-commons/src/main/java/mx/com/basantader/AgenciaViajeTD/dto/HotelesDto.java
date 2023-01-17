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
public class HotelesDto implements Serializable {
	private Long id_hotel;
	
	private CiudadDto Ciudad;
	
	private String Nombre_Hotel;
	
	private String Codigo_Hotel;
	
	private String Direccion;
	
	private String Estatus;
	
	private String Logo;
	

}


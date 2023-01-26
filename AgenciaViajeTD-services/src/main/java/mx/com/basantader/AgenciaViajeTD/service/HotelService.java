package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;

public interface HotelService {
	List<HotelDto>  getallHoteles();
	
	List<HotelDto> getHotelbyName(String nomHotel,String codHotel,Long idCiudad);
	
	HotelDto getHotelBycodigo(String codHotel);
	
	HotelDto createHotel(HotelDto newHotel);
	
	HotelDto updateHotel(HotelDto actualizarHotel,Long idHotel);
	
	RespuestaEliminarDto eliminarHotel(Long idHotel);
}

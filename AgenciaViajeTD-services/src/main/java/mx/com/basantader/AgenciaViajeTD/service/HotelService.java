package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;

public interface HotelService {
	List<HotelDto>  getallHoteles();
	
	HotelDto getHotelbyName(String nomHotel,String codHotel,Long idCiudad);
	
	HotelDto getHotelByciudad(Long ciudad_hotel);
	
	HotelDto createHotel(HotelDto newHotel);
}

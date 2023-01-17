package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.HotelesDto;

public interface HotelService {
	List<HotelesDto>  getallHoteles();
	
	HotelesDto getHotelbyName(String nomHotel,String codHotel,Long idCiudad);
	
	HotelesDto getHotelByciudad(Long ciudad_hotel);
	
	HotelesDto createHotel(HotelesDto newHotel);
}

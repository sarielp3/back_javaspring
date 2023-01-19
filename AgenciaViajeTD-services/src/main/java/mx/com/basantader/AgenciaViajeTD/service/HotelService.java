package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.HotelesDto;

public interface HotelService {
	List<HotelesDto>  getallHoteles();
	
	List<HotelesDto> getHotelbyName(String nomHotel,String codHotel,Long idCiudad);
	
	HotelesDto getHotelBycodigo(String codHotel);
	
	HotelesDto createHotel(HotelesDto newHotel);
}

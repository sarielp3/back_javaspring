package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.ReservasDto;
import mx.com.basantader.AgenciaViajeTD.model.ReservasEntity;

public interface ReservasService {
	
	List<ReservasDto> getReservasEntity();
	
	List<ReservasEntity> getReservaByVueloOrHotel(Long idVuelo, Long idHotel);
	
	
}

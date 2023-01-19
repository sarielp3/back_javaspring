package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.ReservasDto;
import mx.com.basantader.AgenciaViajeTD.model.ReservasEntity;

public interface ReservasService {
	
	List<ReservasDto> getAllReservas();
	
	ReservasEntity getReservaById(Long Vuelo);
	
	ReservasEntity createReserva(ReservasDto createReserva);
	
	
}

package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;

public interface ReservaService {
	
	List<ReservaDto> getReservasByFiltros(Long cuarto, Long origen, Long destino, Long aerolinea);
	
	ReservaEntity createReserva(ReservaDto createReserva);
	
	ReservaDto updateReserva(ReservaDto updateReserva);
	
	void deleteReservaEntity(Long idReserva);
	
	
}

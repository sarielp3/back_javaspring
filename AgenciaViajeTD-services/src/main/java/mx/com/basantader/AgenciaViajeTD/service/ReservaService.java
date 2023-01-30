package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;
import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.dto.AltaReservaDto;

public interface ReservaService {
	
	List<ReservaDto> getReservasByFiltros(Long hotel, Long origen, Long destino, Long aerolinea);
	
	AltaReservaDto createReserva(AltaReservaDto createReserva);
	
	AltaReservaDto updateReserva(AltaReservaDto updateReserva);
	
	void deleteReservaEntity(Long idReserva);
	
	
}

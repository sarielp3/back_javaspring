package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.AerolineaDto;

import java.util.List;

public interface AerolineaService {

    List<AerolineaDto> getAllAerolineas();
    
    AerolineaDto createAerolinea(AerolineaDto aerolinea);
}

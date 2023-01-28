package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import java.util.List;

public interface CiudadService {

    List<CiudadDto> getAllCiudades();
    CiudadDto getCiudadByName(String nombreCiudad);
    List<CiudadDto> getCiudadesByOrigen();
    List<CiudadDto> getCiudadesByDestino();

}

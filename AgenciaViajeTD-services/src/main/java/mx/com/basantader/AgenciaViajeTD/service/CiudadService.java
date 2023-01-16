package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;

import java.util.List;

public interface CiudadService {

    List<CiudadDto> getAllCiudades();
    CiudadDto getCiudadbyName(String nombreCiudad);
}

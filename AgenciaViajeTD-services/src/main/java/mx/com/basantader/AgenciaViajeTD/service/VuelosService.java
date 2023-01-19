package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.VuelosDto;

import java.util.List;

public interface VuelosService {

    List<VuelosDto> getAllVuelos();
    List<VuelosDto> getVuelosByOrigen(Long origen);
    List<VuelosDto> getVuelosByDestino(Long destino);
    List<VuelosDto> getVuelosByAerolinea(Long aerolinea);

    List<VuelosDto> getVuelosByFiltros(Long origen, Long destino,Long aerolinea);

}

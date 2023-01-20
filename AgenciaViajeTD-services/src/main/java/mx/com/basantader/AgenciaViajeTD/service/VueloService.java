package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;

import java.util.List;

public interface VueloService {


    List<VueloDto> getVuelosByFiltros(Long origen, Long destino, Long aerolinea);


}

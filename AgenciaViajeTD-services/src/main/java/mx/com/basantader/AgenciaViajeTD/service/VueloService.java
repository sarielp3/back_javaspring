package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;

import java.util.List;

public interface VueloService {


    List<VueloDto> getVuelosByFiltros(Long origen, Long destino, Long aerolinea);

    VueloDto getVueloByCodigo(String codigoVuelo);
    
    AltaVueloDto createVuelo(AltaVueloDto vueloDto);
    
    AltaVueloDto updateVuelo(AltaVueloDto vueloDto,Long idVuelo);


}

package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.Respuesta;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;

import java.util.List;

public interface VueloService {


    List<VueloDto> getVuelosByFiltros(Long origen, Long destino, Long aerolinea);
    
    VueloDto createVuelo(AltaVueloDto vueloDto);
    
    AltaVueloDto updateVuelo(AltaVueloDto vueloDto,Long idVuelo);
    
    Respuesta updateEstatusVuelo(Long idVuelo);
    
    Respuesta deleteVuelo(Long idVuelo);


}

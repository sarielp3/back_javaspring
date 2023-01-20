package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.repository.VueloRepository;
import mx.com.basantader.AgenciaViajeTD.service.VueloService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author MarioManuelCortezGrano
 */
@Service
public class VueloServiceImpl implements VueloService {

    @Autowired
    private VueloRepository vueloRepository;
    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<VueloDto> getVuelosByFiltros(Long origen, Long destino, Long aerolinea) {
        CiudadEntity origenEntity = null;
        CiudadEntity destinoEntity = null;
        AerolineaEntity aerolineaEntity = null;

        if(origen != null){
            origenEntity  = ciudadRepository.findById(origen).orElseThrow( () -> new ResourceNotFoundException("No hay vuelos desde esta ciudad"));
        }

        if(destino != null){
            destinoEntity  = ciudadRepository.findById(destino).orElseThrow( () -> new ResourceNotFoundException("No hay vuelos hacia esta ciudad"));
        }
        if(aerolinea != null){
            aerolineaEntity  = aerolineaRepository.findById(aerolinea).orElseThrow( () -> new ResourceNotFoundException("No hay vuelos hacia esta ciudad"));
        }
        List<VueloDto> listaVuelosDto = vueloRepository.findVuelosByFiltros(origenEntity, destinoEntity,aerolineaEntity)
                .stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VueloDto.class))
                .collect(Collectors.toList());

        return listaVuelosDto;
    }

}

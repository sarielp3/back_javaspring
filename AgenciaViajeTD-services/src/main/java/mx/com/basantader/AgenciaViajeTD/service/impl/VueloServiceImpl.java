package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.controller.ApplicationController;
import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.VueloEntity;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.repository.VueloRepository;
import mx.com.basantader.AgenciaViajeTD.service.VueloService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private static final Logger log = LoggerFactory.getLogger(VueloServiceImpl.class);

    @Override
    public List<VueloDto> getVuelosByFiltros(Long origen, Long destino, Long aerolinea) {
        CiudadEntity origenEntity = null;
        CiudadEntity destinoEntity = null;
        AerolineaEntity aerolineaEntity = null;

        if(origen != null){
            origenEntity  = ciudadRepository.findById(origen).orElseThrow( () -> {
                log.error("No hay vuelos desde esta ciudad");
                return new ResourceNotFoundException("No hay vuelos desde esta ciudad");
            });
        }

        if(destino != null){
            destinoEntity  = ciudadRepository.findById(destino).orElseThrow( () -> {
                log.error("No hay vuelos hacia esta ciudad");
                return new ResourceNotFoundException("No hay vuelos hacia esta ciudad");
            });
        }
        if(aerolinea != null){
            aerolineaEntity  = aerolineaRepository.findById(aerolinea).orElseThrow( () -> {
                log.error("No hay vuelos hacia esta ciudad");
                return new ResourceNotFoundException("No hay vuelos hacia esta ciudad");
            });
        }
        List<VueloDto> listaVuelosDto = vueloRepository.findVuelosByFiltros(origenEntity, destinoEntity,aerolineaEntity)
                .stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VueloDto.class))
                .collect(Collectors.toList());

        return listaVuelosDto;
    }

    @Override
	public VueloDto getVueloByCodigo(String codigoVuelo) {
    	VueloEntity vueloEntity = vueloRepository.findByCodigoVuelo(codigoVuelo);
    	if(vueloEntity == null) {
    		log.error("No se encontro el vuelo del codigo proporcionado");
    		throw new ResourceNotFoundException("No se encontro el vuelo del codigo proporcionado");
    	}
		VueloDto vueloDto =  this.mapper.map(vueloEntity, VueloDto.class);
		return vueloDto;
	}
    
    
    @Override
    public AltaVueloDto createVuelo(AltaVueloDto altaVueloDto) {
    	VueloEntity vuelosEntity = new VueloEntity();
        vuelosEntity = vueloEntityToAltaVueloDto(altaVueloDto, vuelosEntity);
        vueloRepository.save(vuelosEntity);

        altaVueloDto.setIdVuelo(vuelosEntity.getIdVuelo());

        return altaVueloDto;
    }

	@Override
	public AltaVueloDto updateVuelo(AltaVueloDto vueloDto, Long idVuelo) {
		
		Integer estatus;
		VueloEntity vueloEntity = vueloRepository.findById(idVuelo).orElseThrow(() -> {
			log.error("No se encontro el id vuelo proporcionado");
			return new ResourceNotFoundException("No se encontro el vuelo proporcionado");
		});
		
		estatus = vueloEntity.getEstatus();
		vueloEntity = vueloEntityToAltaVueloDto(vueloDto, vueloEntity);
		vueloEntity.setEstatus(estatus);
		vueloRepository.save(vueloEntity);
		vueloDto.setIdVuelo(vueloEntity.getIdVuelo());
		return vueloDto;
	}
	
	
    private VueloEntity vueloEntityToAltaVueloDto(AltaVueloDto vueloDto, VueloEntity vuelosEntity){
        CiudadEntity origen = ciudadRepository.findById(vueloDto.getOrigen())
                .orElseThrow(() -> {
                    log.error("No se encontro la ciudad de origen seleccionada");
                    return new ResourceNotFoundException("No se encontro la ciudad de origen seleccionada");
                });
        CiudadEntity destino = ciudadRepository.findById(vueloDto.getDestino())
                .orElseThrow(() ->{
                    log.error("No se encontro la ciudad de origen seleccionada");
                    return new ResourceNotFoundException("No se encontro la ciudad de origen seleccionada");
                });
        AerolineaEntity aerolineaEntity = aerolineaRepository.findById(vueloDto.getAerolinea())
                .orElseThrow(() -> {
                    log.error("No se encontro la aerolinea seleccionada");
                    return new ResourceNotFoundException("No se encontro la aerolinea seleccionada");
                });
        vuelosEntity.setOrigen(origen);
        vuelosEntity.setDestino(destino);
        vuelosEntity.setAerolinea(aerolineaEntity);
        vuelosEntity.setEstatus(vueloDto.getEstatus());
        vuelosEntity.setHoraSalida(vueloDto.getHoraSalida());
        vuelosEntity.setHoraLlegada(vueloDto.getHoraLlegada());
        vuelosEntity.setCodigoVuelo(vueloDto.getCodigoVuelo());
        vuelosEntity.setCosto(vueloDto.getCosto());

        return vuelosEntity;
    }

	



}

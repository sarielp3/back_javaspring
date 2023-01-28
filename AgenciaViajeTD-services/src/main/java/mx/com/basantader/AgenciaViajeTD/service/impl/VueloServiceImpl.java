package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.Respuesta;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.VueloEntity;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.repository.ReservaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.VueloRepository;
import mx.com.basantader.AgenciaViajeTD.service.VueloService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ReservaRepository reservaRepository;
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
                log.error("No hay vuelos en esta aerolinea");
                return new ResourceNotFoundException("No hay vuelos en esta aerolinea");
            });
        }
        List<VueloDto> listaVuelosDto = vueloRepository.findVuelosByFiltros(origenEntity, destinoEntity,aerolineaEntity)
                .stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VueloDto.class))
                .collect(Collectors.toList());
        if (listaVuelosDto.isEmpty()) {
        	throw new ResourceNotFoundException("No hay coincidencias de vuelos con los filtros seleccionados");
        }

        return listaVuelosDto;
    }
        
    @Override
    public AltaVueloDto createVuelo(AltaVueloDto altaVueloDto) {
    	VueloEntity vuelosEntity = new VueloEntity();
        vuelosEntity = vueloEntityToAltaVueloDto(altaVueloDto, vuelosEntity);
        
        Optional<VueloEntity> vueloEntityAux = vueloRepository.findByCodigoVuelo(altaVueloDto.getCodigoVuelo());
		if(vueloEntityAux.isPresent()){
			throw new BadRequestException("El codigo del vuelo debe ser unico");
		}
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
		
		Optional<VueloEntity> vueloEntityAux = vueloRepository.findByCodigoVuelo(vueloDto.getCodigoVuelo());
		if(vueloEntityAux.get().getIdVuelo() != vueloEntity.getIdVuelo() && vueloEntityAux.isPresent()) {
			throw new BadRequestException("El codigo del vuelo debe ser unico");
		}
		vueloEntity = vueloEntityToAltaVueloDto(vueloDto, vueloEntity);
		vueloEntity.setEstatus(estatus);
		
		
		vueloRepository.save(vueloEntity);
		vueloDto.setIdVuelo(vueloEntity.getIdVuelo());
		return vueloDto;
	}
	
	@Override
	public Respuesta updateEstatusVuelo(Long idVuelo) {
		Respuesta respuestaEstatus = new Respuesta();
		String respuesta = "Desactivado";
		VueloEntity vueloEntity = vueloRepository.findById(idVuelo).orElseThrow(() -> {
			log.error("No se encontro el id vuelo proporcionado");
			return new ResourceNotFoundException("No se encontro el vuelo proporcionado");
		});
		
		if(vueloEntity.getEstatus() == 0) {
			vueloEntity.setEstatus(1);
			respuesta = "Activado";
		}else {
			vueloEntity.setEstatus(0);
		}
		
		log.info("Se modifico el estatus");
		respuestaEstatus.setMensajeRespuesta("El estatus del vuelo ha cambiado a " + respuesta);
		
		vueloRepository.save(vueloEntity);
		return respuestaEstatus; 
	}
	
	@Override
	public Respuesta deleteVuelo(Long idVuelo) {
		Respuesta respuestaEstatus = new Respuesta();
		VueloEntity vueloEntity = vueloRepository.findById(idVuelo).orElseThrow(() -> {
			log.error("No se encontro el id vuelo proporcionado");
			return new ResourceNotFoundException("No se encontro el vuelo proporcionado");
		});
		List<VueloEntity> vuelosReservados = reservaRepository.findVuelosReservadosById(vueloEntity);
		if(vuelosReservados.size() > 0) {
			log.info("Entro en la excepcion");
			throw new BadRequestException("No se puede eliminar el vuelo");
		}
		vueloRepository.delete(vueloEntity);
		log.info("Se ha eliminado el vuelo ");
		respuestaEstatus.setMensajeRespuesta("Se ha eliminado el vuelo de manera exitosa");
		return respuestaEstatus;
	}
	
    private VueloEntity vueloEntityToAltaVueloDto(AltaVueloDto vueloDto, VueloEntity vuelosEntity){
        CiudadEntity origen = ciudadRepository.findById(vueloDto.getOrigen())
                .orElseThrow(() -> {
                    log.error("No se encontro la ciudad de origen seleccionada");
                    return new ResourceNotFoundException("No se encontro la ciudad de origen seleccionada");
                });
        CiudadEntity destino = ciudadRepository.findById(vueloDto.getDestino())
                .orElseThrow(() ->{
                    log.error("No se encontro la ciudad de destino seleccionada");
                    return new ResourceNotFoundException("No se encontro la ciudad de destino seleccionada");
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

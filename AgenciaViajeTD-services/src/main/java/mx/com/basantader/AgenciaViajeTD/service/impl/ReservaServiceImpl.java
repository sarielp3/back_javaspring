package mx.com.basantader.AgenciaViajeTD.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.dto.AltaReservaDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.CuartoEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;
import mx.com.basantader.AgenciaViajeTD.model.VueloEntity;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.repository.CuartoRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.repository.ReservaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.VueloRepository;
import mx.com.basantader.AgenciaViajeTD.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private VueloRepository vueloRepository;
	
	@Autowired
	private CuartoRepository cuartoRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired 
	private CiudadRepository ciudadRepository;
	
	@Autowired 
	private AerolineaRepository aerolineaRepository;
	
	private static final Logger log = LoggerFactory.getLogger(ReservaServiceImpl.class);

	@Override
	public List<ReservaDto> getReservasByFiltros(Long hotel, Long origen, Long destino, Long aerolinea) {
        HotelEntity hotelEntity = null;
        CiudadEntity origenEntity = null;
        CiudadEntity destinoEntity = null;
        AerolineaEntity aerolineaEntity = null;

        if(hotel != null){
        	hotelEntity  = hotelRepository.findById(hotel).orElseThrow( () -> new ResourceNotFoundException("No hay reservas en este hotel"));
        }
        if(origen != null){
        	origenEntity  = ciudadRepository.findById(origen).orElseThrow( () -> new ResourceNotFoundException("No hay reservas con esta ciudad de origen"));
        }
        if(destino != null){
        	destinoEntity  = ciudadRepository.findById(destino).orElseThrow( () -> new ResourceNotFoundException("No hay reservas con esta ciudad de destino"));
        }
        if(aerolinea != null){
        	aerolineaEntity  = aerolineaRepository.findById(aerolinea).orElseThrow( () -> new ResourceNotFoundException("No hay reservas en esta aerolinea"));
        }
        
        List<ReservaDto> listaReservas = reservaRepository.findReservasByFiltros(hotelEntity,origenEntity,destinoEntity,aerolineaEntity)
                .stream()
                .map(reservasEntity -> modelMapper.map(reservasEntity, ReservaDto.class))
                .collect(Collectors.toList());
        if (listaReservas.isEmpty()) {
        	throw new ResourceNotFoundException("No hay coincidencias de reservas con los filtros seleccionados");
        }
		return listaReservas;
	}
	
	@Override
	public AltaReservaDto createReserva(AltaReservaDto createReserva) {
			Optional<HotelEntity> hotelEntity = hotelRepository.findById(createReserva.getIdHotel());
			if(!hotelEntity.isPresent()) {
				throw new ResourceNotFoundException("El Hotel ingresado no existe");
			}
			Optional<VueloEntity> vueloEntity = vueloRepository.findById(createReserva.getIdVuelo());
			if(!vueloEntity.isPresent()) {
				throw new ResourceNotFoundException("El vuelo ingresado no existe");
			}
			Optional<CuartoEntity> cuartoEntity = cuartoRepository.findById(createReserva.getIdCuarto());
			if(!cuartoEntity.isPresent()) {
				throw new ResourceNotFoundException("El cuarto ingresado no existe");
			}
			
			Boolean f1 = false;
			Boolean f2 = false;
			List<Date> fechasReservadasInicio = reservaRepository.findCuartoByFechaInicio();
			List<Date> fechasReservadasFin = reservaRepository.findCuartoByFechaFin();
			
			for (int i=0; i < fechasReservadasInicio.size(); i++) {
				if(fechasReservadasInicio.get(i).before(createReserva.getFechaFin())
						&&fechasReservadasInicio.get(i).after(createReserva.getFechaInicio())) {
					f1 = true;
				}
				if(fechasReservadasFin.get(i).before(createReserva.getFechaFin())
						&&fechasReservadasFin.get(i).after(createReserva.getFechaInicio())) {
					f2 = true;
				}
			}
			if (f1 == true || f2 == true) {
				throw new BadRequestException("El cuarto es ocupado");
			}
			
			ReservaEntity reserva = modelMapper.map(createReserva, ReservaEntity.class);
			reserva.setFechaCreacion(new Date());
			reserva.setHotel(hotelEntity.get());
			reserva.setVuelo(vueloEntity.get());
			reserva.setCuarto(cuartoEntity.get());
			reservaRepository.save(reserva);
			createReserva.setIdReserva(reserva.getIdReserva());
			createReserva.setFechaCreacion(reserva.getFechaCreacion());
				return createReserva;
	}

	@Override
	public AltaReservaDto updateReserva(AltaReservaDto updateReserva) {
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(updateReserva.getIdHotel());
		if(!hotelEntity.isPresent()) {
			throw new ResourceNotFoundException("El Hotel ingresado no existe");
		}
		Optional<VueloEntity> vueloEntity = vueloRepository.findById(updateReserva.getIdVuelo());
		if(!vueloEntity.isPresent()) {
			throw new ResourceNotFoundException("El vuelo ingresado no existe");
		}
		Optional<CuartoEntity> cuartoEntity = cuartoRepository.findById(updateReserva.getIdCuarto());
		if(!cuartoEntity.isPresent()) {
			throw new ResourceNotFoundException("El cuarto ingresado no existe");
		}
		ReservaEntity reserva = reservaRepository.findById(updateReserva.getIdReserva()).orElseThrow(() -> {
            log.error("No hay ninguna reservacion con ese ID");
            return new ResourceNotFoundException("No hay ninguna reservacion con ese ID");
        });
		
		ReservaEntity reservaUp = modelMapper.map(updateReserva, ReservaEntity.class);
		reservaUp.setFechaCreacion(new Date());
		reservaUp.setHotel(hotelEntity.get());
		reservaUp.setVuelo(vueloEntity.get());
		reservaUp.setCuarto(cuartoEntity.get());
		reservaRepository.save(reservaUp);
		return updateReserva;
	}

	@Override
	public void deleteReservaEntity(Long idReserva) {
		ReservaEntity reserva = reservaRepository.findById(idReserva).orElseThrow(() -> {
            log.error("No hay ninguna reservacion con ese ID");
            return new ResourceNotFoundException("No hay ninguna reservacion con ese ID");
        });
		reservaRepository.deleteById(idReserva);
	}

}

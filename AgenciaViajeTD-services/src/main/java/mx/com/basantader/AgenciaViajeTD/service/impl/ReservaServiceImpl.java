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
import org.springframework.transaction.annotation.Transactional;

import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
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
	public List<ReservaDto> getReservasByFiltros(Long cuarto, Long origen, Long destino, Long aerolinea) {
        CuartoEntity cuartoEntity = null;
        CiudadEntity origenEntity = null;
        CiudadEntity destinoEntity = null;
        AerolineaEntity aerolineaEntity = null;

        if(cuarto != null){
        	cuartoEntity  = cuartoRepository.findById(cuarto).orElseThrow( () -> new ResourceNotFoundException("No hay reservas en este hotel"));
        }
        if(origen != null){
        	origenEntity  = ciudadRepository.findById(origen).orElseThrow( () -> new ResourceNotFoundException("No hay reservas con esta ciudad de origen"));
        }
        if(destino != null){
        	destinoEntity  = ciudadRepository.findById(destino).orElseThrow( () -> new ResourceNotFoundException("No hay reservas con esta ciudad de destino"));
        }
        if(aerolinea != null){
        	aerolineaEntity  = aerolineaRepository.findById(aerolinea).orElseThrow( () -> new ResourceNotFoundException("No hay reservas con esta ciudad de origen"));
        }
        
        List<ReservaDto> listaReservas = reservaRepository.findReservasByFiltros(cuartoEntity,origenEntity,destinoEntity,aerolineaEntity)
                .stream()
                .map(reservasEntity -> modelMapper.map(reservasEntity, ReservaDto.class))
                .collect(Collectors.toList());
		return listaReservas;
	}
	
	@Override
	public ReservaEntity createReserva(ReservaDto createReserva) {
			Optional<HotelEntity> hotelEntity = hotelRepository.findById(createReserva.getIdHotel());
			Optional<VueloEntity> vueloEntity = vueloRepository.findById(createReserva.getIdVuelo());
			Optional<CuartoEntity> cuartoEntity = cuartoRepository.findById(createReserva.getIdCuarto());
			
			ReservaEntity reserva = modelMapper.map(createReserva, ReservaEntity.class);
			reserva.setFechaCreacion(new Date());
			reserva.setHotel(hotelEntity.get());
			reserva.setVuelo(vueloEntity.get());
			reserva.setCuarto(cuartoEntity.get());
			reservaRepository.save(reserva);
			return null;
			
	}

	@Override
	public ReservaDto updateReserva(ReservaDto updateReserva) {
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(updateReserva.getIdHotel());
		Optional<VueloEntity> vueloEntity = vueloRepository.findById(updateReserva.getIdVuelo());
		Optional<CuartoEntity> cuartoEntity = cuartoRepository.findById(updateReserva.getIdCuarto());
		ReservaEntity reserva = reservaRepository.findById(updateReserva.getIdReserva()).orElseThrow(() -> {
            log.error("No hay ninguna reservacion con ese ID");
            return new ResourceNotFoundException("No hay ninguna reservacion con ese ID");
        });
		
		ReservaEntity reservaUp = modelMapper.map(updateReserva, ReservaEntity.class);
		reservaUp.setFechaCreacion(updateReserva.getFechaCreacion());
		reservaUp.setHotel(hotelEntity.get());
		reservaUp.setVuelo(vueloEntity.get());
		reservaUp.setCuarto(cuartoEntity.get());
		reservaRepository.save(reservaUp);
		return updateReserva;
	}

	@Override
	public void deleteReservaEntity(Long idReserva) {
		reservaRepository.deleteById(idReserva);
	}

}

package mx.com.basantader.AgenciaViajeTD.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.basantader.AgenciaViajeTD.dto.ReservasDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.model.CuartosEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.model.ReservasEntity;
import mx.com.basantader.AgenciaViajeTD.model.VuelosEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CuartosRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.repository.ReservasRepository;
import mx.com.basantader.AgenciaViajeTD.repository.VueloRepository;
import mx.com.basantader.AgenciaViajeTD.service.ReservasService;

@Service
public class ReservasServiceImpl implements ReservasService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ReservasRepository reservasRepository;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private VueloRepository vueloRepository;
	
	@Autowired
	private CuartosRepository cuartosRepository;

	@Override
	@Transactional
	public List<ReservasDto> getAllReservas() {
		List<ReservasDto> reservas = reservasRepository.findAll().stream()
				.map(reservasEntity -> modelMapper.map(reservasEntity, ReservasDto.class))
				.collect(Collectors.toList());
		return reservas;
	}

	@Override
	public ReservasEntity getReservaById(Long Vuelo) {
		Optional<ReservasEntity> optId = reservasRepository.findById(Vuelo);
		if(!optId.isPresent()) {
			
			throw new BusinessException(6);
		}else {
			return optId.get();
		}
	}

	@Override
	public ReservasEntity createReserva(ReservasDto createReserva) {
			Optional<HotelEntity> hotelEntity = hotelRepository.findById(createReserva.getIdHotel());
			Optional<VuelosEntity> vueloEntity = vueloRepository.findById(createReserva.getIdVuelo());
			Optional<CuartosEntity> cuartosEntity = cuartosRepository.findById(createReserva.getIdCuarto());
			
			ReservasEntity reserva = modelMapper.map(createReserva, ReservasEntity.class);
			reserva.setHotel(hotelEntity.get());
			reserva.setVuelo(vueloEntity.get());
			reserva.setCuarto(cuartosEntity.get());
			reservasRepository.save(reserva);
			
			return reserva;
			
	}

}

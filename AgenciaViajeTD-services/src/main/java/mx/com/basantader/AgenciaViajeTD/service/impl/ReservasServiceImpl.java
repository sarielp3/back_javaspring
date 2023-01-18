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
import mx.com.basantader.AgenciaViajeTD.model.ReservasEntity;
import mx.com.basantader.AgenciaViajeTD.repository.ReservasRepository;
import mx.com.basantader.AgenciaViajeTD.service.ReservasService;

@Service
public class ReservasServiceImpl implements ReservasService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ReservasRepository reservasRepository;

	@Override
	@Transactional
	public List<ReservasDto> getAllReservas() {
		List<ReservasDto> reservas = reservasRepository.findAll().stream()
				.map(reservasEntity -> mapper.map(reservasEntity, ReservasDto.class))
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

}

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
	private ModelMapper modelMapper;

	@Autowired
	private ReservasRepository reservasRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ReservasDto> getReservasEntity() {
		List<ReservasDto> reservas = reservasRepository.findAll().stream()
				.map(reservasItem -> modelMapper.map(reservasItem, ReservasDto.class)).collect(Collectors.toList());
		return reservas;
	}

	@Override
	public List<ReservasEntity> getReservaByVueloOrHotel(Long idVuelo, Long idHotel) {
		Optional<List<ReservasEntity>> optId = Optional.ofNullable (reservasRepository.finByVueloOrHotel(idVuelo, idHotel));
		if(!optId.isPresent()) {
			
			throw new BusinessException(6);
		}else {
			return optId.get();
		}
	}

}

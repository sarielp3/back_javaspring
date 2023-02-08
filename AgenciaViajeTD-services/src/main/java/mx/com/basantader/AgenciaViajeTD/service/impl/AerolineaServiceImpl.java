package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.AerolineaDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.service.AerolineaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AerolineaServiceImpl implements AerolineaService {

    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AerolineaDto> getAllAerolineas() {
        return aerolineaRepository.findAll().stream()
                .map(aerolineaEntity -> mapper.map(aerolineaEntity, AerolineaDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public AerolineaDto createAerolinea(AerolineaDto aerolineaDto) {
		AerolineaEntity aerolineaEntity = mapper.map(aerolineaDto, AerolineaEntity.class);;
		Optional<Long> aerolineaValidacion = aerolineaRepository.findAerolineaByNombre(aerolineaDto.getNombreAerolinea().toUpperCase());
		if(aerolineaValidacion.isPresent()) {
			throw new BadRequestException("La aerolinea ya se encuentra registrada");
		}
		aerolineaEntity.setNombreAerolinea(aerolineaEntity.getNombreAerolinea().toUpperCase());
		aerolineaRepository.save(aerolineaEntity);
		
		aerolineaDto = mapper.map(aerolineaEntity, AerolineaDto.class);
		return aerolineaDto;
	}
}
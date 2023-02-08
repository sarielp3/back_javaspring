package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author MarioManuelCorezGrano
 */
@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private ModelMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(CiudadServiceImpl.class);

    @Override
    public List<CiudadDto> getAllCiudades() {

        return ciudadRepository.findAll().stream()
                .map(ciudadEntity -> mapper.map(ciudadEntity, CiudadDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CiudadDto getCiudadByName(String nombreCiudad) {

        Optional<CiudadEntity> ciudadEntity = Optional.ofNullable(ciudadRepository.findByNombreCiudad(nombreCiudad));
        if (!ciudadEntity.isPresent()){
            log.error("No se encontró ciudad con ese nombre");
            throw new ResourceNotFoundException("No se encontró ciudad con ese nombre");
        }
        CiudadDto ciudadDto = this.mapper.map(ciudadEntity.get(), CiudadDto.class);

        return ciudadDto;
    }

    @Override
    public List<CiudadDto> getCiudadesByOrigen() {
        return ciudadRepository.findCiudadesByOrigen().stream()
                .map(ciudadEntity -> mapper.map(ciudadEntity, CiudadDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<CiudadDto> getCiudadesByDestino() {
        return ciudadRepository.findCiudadesByDestino().stream()
                .map(ciudadEntity -> mapper.map(ciudadEntity, CiudadDto.class))
                .collect(Collectors.toList());
    }

	@Override
	public CiudadDto createCiudad(CiudadDto ciudadDto) {
		CiudadEntity ciudadEntity = mapper.map(ciudadDto, CiudadEntity.class);
		Optional<Long> ciudadValidacion = ciudadRepository.findCiudadByNombre(ciudadDto.getNombreCiudad().toUpperCase());
		if(ciudadValidacion.isPresent()) {
			throw new BadRequestException("La ciudad ya se encuentra registrada");
		}
		ciudadEntity.setNombreCiudad(ciudadEntity.getNombreCiudad().toUpperCase());
		ciudadRepository.save(ciudadEntity);
		
		ciudadDto.setIdCiudad(ciudadEntity.getIdCiudad());
		
		return ciudadDto;
	}
}

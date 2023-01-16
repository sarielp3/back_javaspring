package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<CiudadDto> getAllCiudades() {

        List<CiudadDto> listaCiudadesDto = ciudadRepository.findAll().stream()
                .map(ciudadEntity -> mapper.map(ciudadEntity, CiudadDto.class))
                .collect(Collectors.toList());

        return listaCiudadesDto;
    }

    @Override
    public CiudadDto getCiudadbyName(String nombreCiudad) {

        Optional<CiudadEntity> ciudadEntity = Optional.ofNullable(ciudadRepository.findByNombreCiudad(nombreCiudad));
        if (!ciudadEntity.isPresent()){
            throw new BusinessException(5);
        }
        CiudadDto ciudadDto = this.mapper.map(ciudadEntity.get(), CiudadDto.class);

        return ciudadDto;
    }
}

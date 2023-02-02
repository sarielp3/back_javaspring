package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.AerolineaDto;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.service.AerolineaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
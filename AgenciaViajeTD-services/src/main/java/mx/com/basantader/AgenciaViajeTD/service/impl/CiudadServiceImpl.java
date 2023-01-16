package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<CiudadDto> getAllCiudades() {
        List<CiudadEntity> listaCiudadesEntity = ciudadRepository.findAll();
        List<CiudadDto> listaCiudadesDto = new ArrayList<CiudadDto>();
        CiudadDto ciudadDto;
        for (CiudadEntity ciudadEntity: listaCiudadesEntity){
            ciudadDto = new CiudadDto();
            ciudadDto.setIdCiudad(ciudadEntity.getIdCiudad());
            ciudadDto.setNombreCiudad(ciudadEntity.getNombreCiudad());
            listaCiudadesDto.add(ciudadDto);
        }
        return listaCiudadesDto;
    }

    @Override
    public CiudadDto getCiudadbyName(String nombreCiudad) {
        Optional<CiudadEntity> ciudadEntity = Optional.ofNullable(ciudadRepository.findByNombreCiudad(nombreCiudad));
        if (!ciudadEntity.isPresent()){
            throw new BusinessException(5);
        }
        CiudadDto ciudadDto = new CiudadDto();

        ciudadDto.setIdCiudad(ciudadEntity.get().getIdCiudad());
        ciudadDto.setNombreCiudad(ciudadEntity.get().getNombreCiudad());

        return ciudadDto;
    }
}

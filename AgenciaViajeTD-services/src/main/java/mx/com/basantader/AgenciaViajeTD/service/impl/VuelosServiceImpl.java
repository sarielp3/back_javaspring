package mx.com.basantader.AgenciaViajeTD.service.impl;

import mx.com.basantader.AgenciaViajeTD.dto.VuelosDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.repository.AerolineaRepository;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.repository.VueloRepository;
import mx.com.basantader.AgenciaViajeTD.service.VuelosService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author MarioManuelCortezGrano
 */
@Service
public class VuelosServiceImpl implements VuelosService {

    @Autowired
    private VueloRepository vueloRepository;
    @Autowired
    private AerolineaRepository aerolineaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public List<VuelosDto> getAllVuelos() {
        List<VuelosDto> listaVuelosDto = vueloRepository.findAll().stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VuelosDto.class))
                .collect(Collectors.toList());

        return listaVuelosDto;
    }

    @Override
    public List<VuelosDto> getVuelosByOrigen(Long origen) {
        if (!(origen instanceof Long)){
            throw new ResourceNotFoundException("Tipo de dato invalida");
        }
        Optional<CiudadEntity> ciudadEntity = ciudadRepository.findById(origen);
        if (!ciudadEntity.isPresent()){
            throw new ResourceNotFoundException("No se encotrno con la ciudad");
        }
        List<VuelosDto> listaVuelosDto = vueloRepository.findByOrigen(ciudadEntity.get()).stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VuelosDto.class))
                .collect(Collectors.toList());

        return listaVuelosDto;
    }

    @Override
    public List<VuelosDto> getVuelosByDestino(Long destino) {
        Optional<CiudadEntity> ciudadEntity = ciudadRepository.findById(destino);
        if (!ciudadEntity.isPresent()){
            throw new ResourceNotFoundException("No se encotrno con la ciudad");
        }
        List<VuelosDto> listaVuelosDto = vueloRepository.findByDestino(ciudadEntity.get()).stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VuelosDto.class))
                .collect(Collectors.toList());

        return listaVuelosDto;
    }

    @Override
    public List<VuelosDto> getVuelosByAerolinea(Long aerolinea) {
        Optional<AerolineaEntity> aerolineaEntity = aerolineaRepository.findById(aerolinea);
        if (!aerolineaEntity.isPresent()){
            throw new ResourceNotFoundException("No se encotrno aerolinea con el id:" + aerolineaEntity);
        }
        List<VuelosDto> listaVuelosDto = vueloRepository.findByAerolinea(aerolineaEntity.get()).stream()
                .map(vuelosEntity -> mapper.map(vuelosEntity, VuelosDto.class))
                .collect(Collectors.toList());

        return listaVuelosDto;

    }

}

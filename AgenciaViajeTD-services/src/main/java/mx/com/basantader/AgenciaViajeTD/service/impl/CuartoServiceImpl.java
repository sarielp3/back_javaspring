package mx.com.basantader.AgenciaViajeTD.service.impl;


import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.CuartoEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CuartoRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.service.CuartoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuartoServiceImpl implements CuartoService {
    private static final Logger log = LoggerFactory.getLogger(CuartoServiceImpl.class);
    @Autowired
    private CuartoRepository cuartosRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    private  void init(){
        TypeMap<CuartoEntity, CuartoDto> mapeoHotel = mapper.createTypeMap(CuartoEntity.class, CuartoDto.class);

        mapeoHotel.addMappings( mapper -> mapper.map(src -> src.getHotel().getIdHotel(), CuartoDto::setIdHotel) );
    }

    @Override
    public List<CuartoDto> filterCuartosById(Long idHotel) {
        HotelEntity hotelEntity = null;
        if(idHotel != null){
            hotelEntity = hotelRepository.findById(idHotel).orElseThrow(() -> new ResourceNotFoundException("No hay hoteles disponibles"));
        }
        List<CuartoDto> filters = cuartosRepository.findByHotel(hotelEntity).stream().map(cuartoEntity ->  mapper.map(cuartoEntity, CuartoDto.class)).collect(Collectors.toList());

        return filters;
    }

    @Override
    public CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel) {
      Optional<HotelEntity> hotelId = hotelRepository.findById(idHotel);
      CuartoEntity cuartosEntity = mapper.map(cuartoAdd, CuartoEntity.class);

      Optional<CuartoEntity> validarNC = cuartosRepository.findByNombreCuarto(cuartoAdd.getNombreCuarto());
      Optional<CuartoEntity> validarCC = cuartosRepository.findByCodigoCuartos(cuartoAdd.getCodigoCuartos());

      if (validarNC.isPresent()){
          throw new BusinessException("El nombre del cuarto ya existe");
      }

        if (validarCC.isPresent()){
            throw new BusinessException("El codigo del cuarto ya existe");
        }

      cuartosEntity.setHotel(hotelId.get());

      CuartoEntity cuartosEn = cuartosRepository.save(cuartosEntity);
      CuartoDto cuartosDTO = mapper.map(cuartosEn, CuartoDto.class);

      return cuartosDTO;

    }
}

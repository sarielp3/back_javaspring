package mx.com.basantader.AgenciaViajeTD.service.impl;


import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.model.CuartosEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CuartosRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.service.CuartosService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class CuartosServiceImpl implements CuartosService {
    private static final Logger log = LoggerFactory.getLogger(CuartosServiceImpl.class);
    @Autowired
    private CuartosRepository cuartosRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    private  void init(){
        TypeMap<CuartosEntity, CuartoDto> mapeoHotel = mapper.createTypeMap(CuartosEntity.class, CuartoDto.class);

        mapeoHotel.addMappings( mapper -> mapper.map(src -> src.getHotel().getId_hotel(), CuartoDto::setIdHotel) );
    }

    @Override
    public CuartoDto filterCuartosById(Long idHotel) {
        Optional<CuartosEntity> filter = cuartosRepository.findById(idHotel);

        if (!filter.isPresent()){
            throw new BusinessException(6);
        }

       CuartoDto cuartosDTO = mapper.map(filter.get(), CuartoDto.class);

        return  cuartosDTO;
    }

    @Override
    public CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel) {
      Optional<HotelEntity> hotelId = hotelRepository.findById(idHotel);
      CuartosEntity cuartosEntity = mapper.map(cuartoAdd, CuartosEntity.class);

      Optional<CuartosEntity> validarNC = cuartosRepository.findByNombreCuarto(cuartoAdd.getNombreCuarto());
      Optional<CuartosEntity> validarCC = cuartosRepository.findByCodigoCuartos(cuartoAdd.getCodigoCuartos());

      if (validarNC.isPresent()){
          throw new BusinessException("El nombre del cuarto ya existe");
      }

        if (validarCC.isPresent()){
            throw new BusinessException("El codigo del cuarto ya existe");
        }

      cuartosEntity.setHotel(hotelId.get());

      CuartosEntity cuartosEn = cuartosRepository.save(cuartosEntity);
      CuartoDto cuartosDTO = mapper.map(cuartosEn, CuartoDto.class);

      return cuartosDTO;

    }
}

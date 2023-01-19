package mx.com.basantader.AgenciaViajeTD.service.impl;


import mx.com.basantader.AgenciaViajeTD.dto.CuartosDTO;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
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
        TypeMap<CuartosEntity, CuartosDTO> mapeoHotel = mapper.createTypeMap(CuartosEntity.class, CuartosDTO.class);

        mapeoHotel.addMappings( mapper -> mapper.map(src -> src.getHotel().getId_hotel(), CuartosDTO::setIdHotel) );
    }

    @Override
    public CuartosDTO filterCuartosById(Long idHotel) {
        Optional<CuartosEntity> filter = cuartosRepository.findById(idHotel);

        if (!filter.isPresent()){
            throw new BusinessException(6);
        }

       CuartosDTO cuartosDTO = mapper.map(filter.get(), CuartosDTO.class);

        return  cuartosDTO;
    }

    @Override
    public CuartosDTO crearCuarto(CuartosDTO cuartoAdd, Long idHotel) {
      Optional<HotelEntity> hotelId = hotelRepository.findById(idHotel);
      CuartosEntity cuartosEntity = mapper.map(cuartoAdd, CuartosEntity.class);

      //Optional<CuartosEntity> validarNC = cuartosRepository.finByNombreCuarto(cuartoAdd.getNombreCuarto());
      // Optional<CuartosEntity> validarCC = cuartosRepository.finByCodigoCuarto(cuartoAdd.getCodigoCuartos());

     /* if (validarNC.isPresent()){
          throw new ResourceNotFoundException("El nombre del cuarto ya existe");
      }

        /*if (validarCC.isPresent()){
            throw new ResourceNotFoundException("El codigo del cuarto ya existe");
        }*/

      cuartosEntity.setHotel(hotelId.get());

      CuartosEntity cuartosEn = cuartosRepository.save(cuartosEntity);
      CuartosDTO cuartosDTO = mapper.map(cuartosEn, CuartosDTO.class);

      return cuartosDTO;

    }
}

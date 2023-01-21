package mx.com.basantader.AgenciaViajeTD.service.impl;


import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
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
import java.util.Optional;

@Service
public class CuartoServiceImpl implements CuartoService {
    private static final Logger log = LoggerFactory.getLogger(CuartoServiceImpl.class);
    @Autowired
    private CuartoRepository cuartoRepository;


    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper mapper;

    @PostConstruct
    private  void init(){
    	TypeMap<CuartoEntity, CuartoDto> mapeoHotel = mapper.createTypeMap(CuartoEntity.class, CuartoDto.class);

        mapeoHotel.addMappings( mapper -> mapper.map(src -> src.getHotel().getId_hotel(), CuartoDto::setIdHotel) );
    }

    @Override
    public CuartoDto filterCuartosById(Long idHotel) {

        Optional<CuartoEntity> filter = cuartoRepository.findById(idHotel);


        if (!filter.isPresent()){
            throw new BusinessException(6);
        }

       CuartoDto cuartoDto = mapper.map(filter.get(), CuartoDto.class);

        return  cuartoDto;
    }

    @Override
    public CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel) {
      Optional<HotelEntity> hotelId = hotelRepository.findById(idHotel);
      CuartoEntity cuartoEntity = mapper.map(cuartoAdd, CuartoEntity.class);

      Optional<CuartoEntity> validarNC = cuartoRepository.findByNombreCuarto(cuartoAdd.getNombreCuarto());
      Optional<CuartoEntity> validarCC = cuartoRepository.findByCodigoCuartos(cuartoAdd.getCodigoCuartos());


      if (validarNC.isPresent()){
          throw new BusinessException("El nombre del cuarto ya existe");
      }

        if (validarCC.isPresent()){
            throw new BusinessException("El codigo del cuarto ya existe");
        }

      cuartoEntity.setHotel(hotelId.get());

      CuartoEntity cuartosEn = cuartoRepository.save(cuartoEntity);
      CuartoDto cuartoDto = mapper.map(cuartosEn, CuartoDto.class);


      return cuartoDto;

    }
}

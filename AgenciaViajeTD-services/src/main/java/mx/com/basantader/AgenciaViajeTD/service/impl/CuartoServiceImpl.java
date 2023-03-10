package mx.com.basantader.AgenciaViajeTD.service.impl;


import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.CuartoEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CuartoRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.service.CuartoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuartoServiceImpl implements CuartoService {
    @Autowired
    private CuartoRepository cuartosRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostConstruct
    private  void init(){
        TypeMap<CuartoEntity, CuartoDto> mapeoHotel = modelMapper.createTypeMap(CuartoEntity.class, CuartoDto.class);

        mapeoHotel.addMappings( mapper -> mapper.map(src -> src.getHotel().getIdHotel(), CuartoDto::setIdHotel) );
    }

    @Override
    public List<CuartoDto> filterCuartosById(Long idHotel) {
        HotelEntity hotelEntity = null;
        if(idHotel != null){
            hotelEntity = hotelRepository.findById(idHotel)
            		.orElseThrow(() -> new ResourceNotFoundException("No hay hoteles disponibles"));
        }
        return cuartosRepository.findByHotel(hotelEntity).stream().map(cuartoEntity ->  modelMapper.map(cuartoEntity, CuartoDto.class)).collect(Collectors.toList());

    }

    @Override
    public CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel) {
      HotelEntity hotelId = hotelRepository.findById(idHotel)
    		  .orElseThrow(() -> new ResourceNotFoundException("No se encontr?? el hotel"));
      CuartoEntity cuartosEntity = modelMapper.map(cuartoAdd, CuartoEntity.class);

      cuartosEntity.setNombreCuarto(cuartosEntity.getNombreCuarto().toUpperCase());
      cuartosEntity.setCodigoCuartos(cuartosEntity.getCodigoCuartos().toUpperCase());
      Optional<Long> validarNC = cuartosRepository.findCuartoByNombreCuarto(cuartoAdd.getNombreCuarto().toUpperCase());
      
      if (validarNC.isPresent()){
          throw new BadRequestException("El nombre del cuarto ya existe");
      }
      
      Optional<Long> validarCC = cuartosRepository.findCuartoByCodigoCuarto(cuartoAdd.getCodigoCuartos().toUpperCase());
	
	  if (validarCC.isPresent()){
		  throw new BadRequestException("El c??digo del cuarto ya existe");
	  }

      cuartosEntity.setHotel(hotelId);
      
      CuartoEntity cuartosEn = cuartosRepository.save(cuartosEntity);
      
      return modelMapper.map(cuartosEn, CuartoDto.class);

    }

    @Override
    public CuartoDto modificarCuarto(CuartoDto cuartoDto, Long idCuarto) {
        Optional<CuartoEntity> cuartoEditar = cuartosRepository.findById(idCuarto);

        CuartoEntity cuartosEntity = cuartoEditar.orElseThrow(()-> new ResourceNotFoundException("Cuarto no existe"));
        

        Optional<CuartoEntity> validarNC = cuartosRepository.findByNombreCuarto(cuartoDto.getNombreCuarto().toUpperCase());
        Optional<CuartoEntity> validarCC = cuartosRepository.findByCodigoCuartos(cuartoDto.getCodigoCuartos().toUpperCase());

        if (validarNC.isPresent() && !validarNC.get().getIdCuarto().equals(cuartosEntity.getIdCuarto())){   
        	throw new BadRequestException("El nombre del cuarto ya existe");  }
        if (validarCC.isPresent() && !validarCC.get().getIdCuarto().equals(cuartosEntity.getIdCuarto())){     
        	throw new BadRequestException("El c??digo del cuarto ya existe"); }

        cuartosEntity.setNombreCuarto(cuartoDto.getNombreCuarto().toUpperCase());
        cuartosEntity.setDescripcion(cuartoDto.getDescripcion());
        cuartosEntity.setNumeroPersonas(cuartoDto.getNumeroPersonas());
        cuartosEntity.setCodigoCuartos(cuartoDto.getCodigoCuartos().toUpperCase());
        cuartosEntity.setCostoNoche(cuartoDto.getCostoNoche());
        cuartosEntity.setTipoCuarto(cuartoDto.getTipoCuarto());

        HotelEntity hotelEntity = hotelRepository.findById(cuartoDto.getIdHotel()).orElseThrow(()-> new ResourceNotFoundException("No se encuentra ID de Hotel"));
        cuartosEntity.setHotel(hotelEntity);

        CuartoEntity cuartosSave = cuartosRepository.save(cuartosEntity);

        return modelMapper.map(cuartosSave, CuartoDto.class);


    }

    @Override
    public List<CuartoDto> listaCuartos() {
        List<CuartoDto> listaCuartosDto = cuartosRepository.findAll().stream()
                .map(cuartoEntity ->  modelMapper.map(cuartoEntity,CuartoDto.class))
                .collect(Collectors.toList());
        return listaCuartosDto;
    }


    @Override
    public RespuestaEliminarDto eliminarCuarto(Long idCuarto) {
        Optional<CuartoEntity> cuartoEntity = cuartosRepository.findById(idCuarto);
        if (!cuartoEntity.isPresent()){
            throw  new ResourceNotFoundException("El id del cuarto no existe");
        }

        RespuestaEliminarDto respuestaEliminarDto = new RespuestaEliminarDto();
        CuartoEntity aux = cuartoEntity.get();
        if (aux.getReservasCuartos().isEmpty()){
            cuartosRepository.deleteById(idCuarto);
            respuestaEliminarDto.setMensajeRespuesta("El id del cuarto " + idCuarto + " fue eliminado");
        }else {
            throw new BadRequestException("No se puede eliminar cuarto por que tiene una reserva");
        }
        return respuestaEliminarDto;
    }

    @Override
    public CuartoDto statusCuartos(Long idCuarto) {
        Optional<CuartoEntity> cuartoEntity = cuartosRepository.findById(idCuarto);
        Byte statusActual;
        CuartoDto cuartoDto;

        if (!cuartoEntity.isPresent()){
            throw  new ResourceNotFoundException("el id del cuarto no existe");
        }else {
            statusActual = cuartoEntity.get().getStatus();
            cuartoEntity.get().setStatus((byte)(statusActual == 0 ? 1: 0));
            cuartosRepository.save(cuartoEntity.get());
            cuartoDto= modelMapper.map(cuartoEntity.get(), CuartoDto.class);
        }
        return  cuartoDto;
    }
}

package mx.com.basantader.AgenciaViajeTD.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.dto.Respuesta;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;


/**
 * @author VictorHugoAcostaHernandez
 */
@Service
public class HotelServiceImpl implements HotelService,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<HotelDto> getallHoteles() {
		List<HotelDto> lstHoteles = hotelRepository.findAll().stream()
				.map(HotelEntity -> mapper.map(HotelEntity, HotelDto.class))
				.collect(Collectors.toList());

		return lstHoteles;
	}


	@Override
	public List<HotelDto> getHotelbyName(String nomHotel,String codHotel,Long idCiudad) {
		List<HotelEntity> hotelEntity = hotelRepository.encontrarByNombreHotelAndCodigoHotelAndCiudadIdCiudad(nomHotel,codHotel,idCiudad);
		if(hotelEntity.isEmpty()) {
			throw new ResourceNotFoundException("No existe un hotel con los filtros ingresados");
		}
	
		List<HotelDto> lstHoteles = hotelEntity.stream()
				.map(HotelEntity -> mapper.map(HotelEntity, HotelDto.class))
				.collect(Collectors.toList());
		
		return lstHoteles;
	}


	@Override
	public HotelDto getHotelBycodigo(String codHotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByCodigoHotel(codHotel));
		if(!hotelEntity.isPresent()) {
			throw new ResourceNotFoundException("No existe un hotel con el codigo ingresado");
		}
		HotelDto hoteldto = this.mapper.map(hotelEntity.get(), HotelDto.class);
		
		return hoteldto;
	}


	@Override
	@Transactional
	public HotelDto createHotel(HotelDto newHotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByCodigoHotel(newHotel.getCodigoHotel()));
		if(hotelEntity.isPresent()) {
			throw new BusinessException("Ya existe hotel con el codigo ingresado");
		}
		
		HotelEntity nuevoRegistro = this.mapper.map(newHotel, HotelEntity.class);
		
		
		hotelRepository.save(nuevoRegistro);
		newHotel.setIdHotel(nuevoRegistro.getIdHotel());
		
		return newHotel;
	}


	@Override
	public HotelDto updateHotel(HotelDto actualizarHotel,Long idHotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByCodigoHotel(actualizarHotel.getCodigoHotel()));
		if(hotelEntity.isPresent()) {
			throw new BusinessException("Ya existe hotel con el codigo ingresado");
		}
		Optional<HotelEntity> registro = hotelRepository.findById(idHotel);
		if(!registro.isPresent()) {
			throw new BusinessException("No existe el id Ingresado");
		}
		
		HotelEntity cambiosHotel = registro.get();
		CiudadEntity ciudad = new CiudadEntity();
		ciudad.setIdCiudad(actualizarHotel.getCiudad().getIdCiudad());
		cambiosHotel.setCiudad(ciudad);
		cambiosHotel.setNombreHotel(actualizarHotel.getNombreHotel());
		cambiosHotel.setCodigoHotel(actualizarHotel.getCodigoHotel());
		cambiosHotel.setDireccion(actualizarHotel.getDireccion());
		cambiosHotel.setEstatus(actualizarHotel.getEstatus());
		cambiosHotel.setLogo(actualizarHotel.getLogo());
		hotelRepository.save(cambiosHotel);
		actualizarHotel.setIdHotel(idHotel);
		
		return actualizarHotel;
	}


	@Override
	public RespuestaEliminarDto eliminarHotel(Long idHotel) {
		RespuestaEliminarDto mensaje = new RespuestaEliminarDto();
		Optional<HotelEntity> hotelEntity = hotelRepository.findById(idHotel);
		if(!hotelEntity.isPresent()) {
			throw new ResourceNotFoundException("No existe un hotel con el ID ingresado");
		}
		
		hotelRepository.deleteById(idHotel);
		mensaje.setMensajeRespuesta("Registro eliminado correctamente");
		return mensaje;
	}

	@Override
	public Respuesta cambiarEstatus(Long idHotel) {
		Optional<HotelEntity> registro = hotelRepository.findById(idHotel);
		Respuesta mensaje = new Respuesta();
		if(!registro.isPresent()) {
			throw new ResourceNotFoundException("No existe el id Ingresado");
		}
		HotelEntity cambiosStatusHotel = registro.get();
		if(cambiosStatusHotel.getEstatus()==1) {
			cambiosStatusHotel.setEstatus(0);
			mensaje.setMensajeRespuesta("Cambio el estatus del hotel a Desactivo");
		}else if(cambiosStatusHotel.getEstatus()==0) {
			cambiosStatusHotel.setEstatus(1);
			mensaje.setMensajeRespuesta("Cambio el estatus del hotel a Activo");
		}
		hotelRepository.save(cambiosStatusHotel);
		
		return mensaje;
	}
	


}

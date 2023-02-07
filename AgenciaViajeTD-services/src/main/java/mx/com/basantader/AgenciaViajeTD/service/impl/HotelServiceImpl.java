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
import mx.com.basantader.AgenciaViajeTD.exceptions.BadRequestException;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.repository.ReservaRepository;
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
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public List<HotelDto> getallHoteles() {
		return hotelRepository.findAll().stream()
				.map(HotelEntity -> mapper.map(HotelEntity, HotelDto.class))
				.collect(Collectors.toList());

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
		if (lstHoteles.isEmpty()) {
        	throw new ResourceNotFoundException("No hay coincidencias de hoteles con los filtros seleccionados");
        }
		return lstHoteles;
	}


	@Override
	public HotelDto getHotelBycodigo(String codHotel) {
		Optional<HotelEntity> hotelEntity = hotelRepository.findByCodigoHotel(codHotel);
		if(!hotelEntity.isPresent()) {
			throw new ResourceNotFoundException("No existe un hotel con el codigo ingresado");
		}
		return this.mapper.map(hotelEntity.get(), HotelDto.class);

	}


	@Override
	@Transactional
	public HotelDto createHotel(HotelDto newHotel) {
		Optional<Long> hotelEntity = hotelRepository.findHotelByCodigo(newHotel.getCodigoHotel().toUpperCase());
		if(hotelEntity.isPresent()) {
			throw new BusinessException("Ya existe hotel con el codigo ingresado");
		}
		
		Optional<Long> hotelEntityNombre = hotelRepository.findHotelByNombre(newHotel.getNombreHotel().toUpperCase());
		if(hotelEntityNombre.isPresent()) {
			throw new BusinessException("Ya existe hotel con el nombre ingresado");
		}
		HotelEntity nuevoRegistro = this.mapper.map(newHotel, HotelEntity.class);
		
		nuevoRegistro.setEstatus(1);
		nuevoRegistro.setCodigoHotel(nuevoRegistro.getCodigoHotel().toUpperCase());
		nuevoRegistro.setNombreHotel(nuevoRegistro.getNombreHotel().toUpperCase());
		
		hotelRepository.save(nuevoRegistro);
		newHotel.setIdHotel(nuevoRegistro.getIdHotel());
		
		return newHotel;
	}


	@Override
	public HotelDto updateHotel(HotelDto actualizarHotel,Long idHotel) {
		HotelEntity registro = hotelRepository.findById(idHotel)
				.orElseThrow(()-> new ResourceNotFoundException("Hotel no existe"));
		
		Optional<Long> hotelEntidad = hotelRepository.findHotelByCodigo(actualizarHotel.getCodigoHotel().toUpperCase());
		
		if(hotelEntidad.isPresent() && !hotelEntidad.get().equals(registro.getIdHotel())) {
			throw new BadRequestException("El codigo del hotel debe ser unico");
		}
		Optional<Long> hotelEntidadAux = hotelRepository.findHotelByNombre(actualizarHotel.getNombreHotel().toUpperCase());
		
		if(hotelEntidadAux.isPresent() && !hotelEntidadAux.get().equals(registro.getIdHotel())) {
			throw new BadRequestException("El nombre del hotel debe ser unico");
		}
		CiudadEntity ciudad = new CiudadEntity();
		ciudad.setIdCiudad(actualizarHotel.getCiudad().getIdCiudad());
		registro.setCiudad(ciudad);
		registro.setNombreHotel(actualizarHotel.getNombreHotel().toUpperCase());
		registro.setCodigoHotel(actualizarHotel.getCodigoHotel().toUpperCase());
		registro.setDireccion(actualizarHotel.getDireccion());
		registro.setEstatus(actualizarHotel.getEstatus());
		registro.setLogo(actualizarHotel.getLogo());
		hotelRepository.save(registro);
		actualizarHotel.setIdHotel(idHotel);
		
		return actualizarHotel;
	}


	@Override
	public RespuestaEliminarDto eliminarHotel(Long idHotel) {
		RespuestaEliminarDto mensaje = new RespuestaEliminarDto();
		boolean hotelExiste = hotelRepository.existsById(idHotel);
		if(hotelExiste == false) {
			throw new ResourceNotFoundException("No existe un hotel con el ID ingresado");
		}
		
		List<Long> reservaEntity = reservaRepository.findByIdHotel(idHotel);
		if(!reservaEntity.isEmpty()) {
			throw new BadRequestException("No se puede eliminar el hotel porque ya existe una reservacion con ese ID");
		}
		
		hotelRepository.deleteById(idHotel);
		mensaje.setMensajeRespuesta("Registro eliminado correctamente");
		return mensaje;
	}

	@Transactional
	@Override
	public Respuesta cambiarEstatus(Long idHotel) {
		
		Integer estatusHotel = hotelRepository.findEstatusByIdHotel(idHotel).orElseThrow(() -> {
			return new ResourceNotFoundException("No existe nungun hotel con el id ingresado");
		});
		Respuesta mensaje = new Respuesta();
		
		if(estatusHotel == 1) {
			estatusHotel = 0;
			mensaje.setMensajeRespuesta("Cambio el estatus del hotel a Desactivo");
		}else if(estatusHotel ==0) {
			estatusHotel = 1;
			mensaje.setMensajeRespuesta("Cambio el estatus del hotel a Activo");
		}
		hotelRepository.updateEstatusHotel(idHotel, estatusHotel);;
		
		return mensaje;
	}
	


}

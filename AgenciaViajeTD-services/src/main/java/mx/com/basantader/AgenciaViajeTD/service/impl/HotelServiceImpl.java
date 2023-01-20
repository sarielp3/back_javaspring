package mx.com.basantader.AgenciaViajeTD.service.impl;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CiudadRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;


/**
 * @author VictorHugoAcostaHernandez
 */
@Service
public class HotelServiceImpl implements HotelService {

	
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
	public HotelDto getHotelbyName(String nomHotel,String codHotel,Long idCiudad) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByNombre_hotelOrCodigo_hotelOrCiudadIdCiudad(nomHotel,codHotel,idCiudad));
		if(!hotelEntity.isPresent()) {
			throw new BusinessException("No existe un hotel con el nombre ingresado");
		}
		HotelDto hoteldto = this.mapper.map(hotelEntity.get(), HotelDto.class);
		
		return hoteldto;
	}


	@Override
	public HotelDto getHotelByciudad(Long ciudad_hotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByciudad_hotel(ciudad_hotel));
		if(!hotelEntity.isPresent()) {
			throw new BusinessException("No existe un hotel con id ciudad ingresado");
		}
		HotelDto hoteldto = this.mapper.map(hotelEntity.get(), HotelDto.class);
		
		
		hoteldto.setLogo(null);
		
		return hoteldto;
	}


	@Override
	@Transactional
	public HotelDto createHotel(HotelDto newHotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByciudad_hotel(Long.parseLong(newHotel.getCodigoHotel())));
		if(hotelEntity.isPresent()) {
			throw new BusinessException("Ya existe hotel con el codigo ingresado");
		}
	
		CiudadEntity ciudadentidad = new CiudadEntity();
		ciudadentidad.setIdCiudad(newHotel.getCiudad().getIdCiudad());
		
		HotelEntity nuevoRegistro = new HotelEntity();
		nuevoRegistro.setCiudad(ciudadentidad);
		nuevoRegistro.setNombre_hotel(newHotel.getNombreHotel());
		nuevoRegistro.setCodigo_hotel(newHotel.getCodigoHotel());
		nuevoRegistro.setDireccion(newHotel.getDireccion());
		nuevoRegistro.setEstatus(newHotel.getEstatus());
		/*
		byte[] decodedByte = newHotel.getLogo().getBytes(); 
		Blob b=null;
		try {
			b = new SerialBlob(decodedByte);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nuevoRegistro.setLogo(b);*/
		hotelRepository.save(nuevoRegistro);
		newHotel.setIdHotel(nuevoRegistro.getIdHotel());
		
		return newHotel;
	}

}

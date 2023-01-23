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

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.model.ApplicationItem;
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
	public List<HotelDto> getHotelbyName(String nomHotel,String codHotel,Long idCiudad) {
		List<HotelEntity> hotelEntity = hotelRepository.encontrarByNombreHotelAndCodigoHotelAndCiudadIdCiudad(nomHotel,codHotel,idCiudad);
		if(hotelEntity.isEmpty()) {
			throw new ResourceNotFoundException("No existe un hotel con los filtros ingresados");
		}
	
		List<HotelDto> lstHoteles = hotelRepository.encontrarByNombreHotelAndCodigoHotelAndCiudadIdCiudad(nomHotel, codHotel, idCiudad).stream()
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

}

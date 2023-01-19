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
import mx.com.basantader.AgenciaViajeTD.dto.HotelesDto;
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
	public List<HotelesDto> getallHoteles() {
		List<HotelesDto> lstHoteles = hotelRepository.findAll().stream()
				.map(HotelEntity -> mapper.map(HotelEntity, HotelesDto.class))
				.collect(Collectors.toList());
		
		return lstHoteles;
	}


	@Override
	public List<HotelesDto> getHotelbyName(String nomHotel,String codHotel,Long idCiudad) {
		List<HotelEntity> hotelEntity = hotelRepository.findByNombreHotelOrCodigoHotelOrCiudadIdCiudad(nomHotel,codHotel,idCiudad);
		if(hotelEntity.isEmpty()) {
			throw new BusinessException("No existe un hotel con el nombre ingresado");
		}
		//HotelesDto hoteldto = this.mapper.map(hotelEntity.get(), HotelesDto.class);
		List<HotelesDto> lstHoteles = hotelRepository.findByNombreHotelOrCodigoHotelOrCiudadIdCiudad(nomHotel, codHotel, idCiudad).stream()
				.map(HotelEntity -> mapper.map(HotelEntity, HotelesDto.class))
				.collect(Collectors.toList());
		
		return lstHoteles;
	}


	@Override
	public HotelesDto getHotelBycodigo(String codHotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByCodigoHotel(codHotel));
		if(!hotelEntity.isPresent()) {
			throw new BusinessException("No existe un hotel con id ciudad ingresado");
		}
		HotelesDto hoteldto = this.mapper.map(hotelEntity.get(), HotelesDto.class);

		
	/*	CiudadDto ciudad = new CiudadDto();
		ciudad.setIdCiudad(hotelEntity.get().getCiudad().getIdCiudad());
		hoteldto.setId_hotel(hotelEntity.get().getId_hotel());
		hoteldto.setCiudad(ciudad);
		//hoteldto.setLogo("hihi");*/
		
		return hoteldto;
	}


	@Override
	@Transactional
	public HotelesDto createHotel(HotelesDto newHotel) {
		Optional<HotelEntity> hotelEntity = Optional.ofNullable(hotelRepository.findByCodigoHotel(newHotel.getCodigoHotel()));
		if(hotelEntity.isPresent()) {
			throw new BusinessException("Ya existe hotel con el codigo ingresado");
		}
	
		CiudadEntity ciudadentidad = new CiudadEntity();
		ciudadentidad.setIdCiudad(Long.parseLong(newHotel.getCiudad()));
		
		HotelEntity nuevoRegistro = new HotelEntity();
		nuevoRegistro.setCiudad(ciudadentidad);
		nuevoRegistro.setNombreHotel(newHotel.getNombreHotel());
		nuevoRegistro.setCodigoHotel(newHotel.getCodigoHotel());
		nuevoRegistro.setDireccion(newHotel.getDireccion());
		nuevoRegistro.setEstatus(newHotel.getEstatus());
		//byte[] decodedByte = Base64.decode(newHotel.getLogo());*/
	//	HotelEntity hotelItem = this.mapper.map(newHotel, HotelEntity.class);
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
		nuevoRegistro.setLogo(b);
		hotelRepository.save(nuevoRegistro);
		newHotel.setIdHotel(nuevoRegistro.getIdHotel());
		
		return newHotel;
	}

}

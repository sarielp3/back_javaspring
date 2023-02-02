package mx.com.basantader.AgenciaViajeTD.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.dto.Respuesta;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;

@RestController
@RequestMapping("/hoteles")
@Api(value = "Endpoints para obtener lista de hoteles, obtener por nombre y codigo de hotel")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanfetdd01.azurewebsites.net"} )
@Validated
public class HotelController {
    
    @Autowired
    HotelService hotelservice;
    
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Ver lista de hoteles", response = HotelDto.class)
    public List<HotelDto> listahoteles(){
    	
        return hotelservice.getallHoteles();
    }
    
    @GetMapping(value="/filtros", produces = "application/json")
    @ApiOperation(value = "Ver lista de hoteles por filtros", response = HotelDto.class)
    public List<HotelDto> getHotel(@RequestParam(required = false) String nomHotel,@RequestParam(required = false) String codHotel
    		, @RequestParam(required=false) Long ciudad) {
    	return hotelservice.getHotelbyName(nomHotel,codHotel,ciudad);
    }
    
    @GetMapping(value="/codigo/{codHotel}", produces = "application/json")
    @ApiOperation(value = "Ver hotel por codigo de hotel", response = HotelDto.class)
    public HotelDto getCodigoHotel(@PathVariable String codHotel) {
    	if(codHotel == null) {
    		throw new ResourceNotFoundException("Codigo de hotel necesario");
    	}
    	return hotelservice.getHotelBycodigo(codHotel);
    }
    
    @PostMapping(value="/nuevo-hotel",produces="application/json")
    public HotelDto crearHotel(@RequestBody HotelDto nuevoReg) {
    	if(nuevoReg.getCodigoHotel() == null || nuevoReg.getCodigoHotel().trim().isEmpty()) {
    		throw new ResourceNotFoundException("Codigo de hotel necesario");
    	}
    	if(nuevoReg.getCiudad().getIdCiudad() == null || nuevoReg.getCiudad().getIdCiudad().toString().trim().isEmpty()) {
    		throw new ResourceNotFoundException("ID de ciudad necesario");
    	}
    	if(nuevoReg.getNombreHotel() == null || nuevoReg.getNombreHotel().trim().isEmpty()) {
    		throw new ResourceNotFoundException("Nombre de hotel necesario");
    	}
    	if(nuevoReg.getDireccion() == null || nuevoReg.getDireccion().trim().isEmpty()) {
    		throw new ResourceNotFoundException("direccion de hotel necesario");
    	}
    	if(Integer.toString(nuevoReg.getEstatus()) == null || Integer.toString(nuevoReg.getEstatus()).trim().isEmpty()) {
    		throw new ResourceNotFoundException("estatus de hotel necesario");
    	}
    	
    	return hotelservice.createHotel(nuevoReg);
    }
    
    @PutMapping(value="/update-hotel/{id:[\\d]+}",produces="application/json")
    public HotelDto updateHotel(@PathVariable("id") Long id,@RequestBody HotelDto actualizarhotel) {
    	if(actualizarhotel.getCodigoHotel() == null || actualizarhotel.getCodigoHotel().trim().isEmpty()) {
    		throw new ResourceNotFoundException("Codigo de hotel necesario");
    	}
    	if(actualizarhotel.getCiudad().getIdCiudad() == null || actualizarhotel.getCiudad().getIdCiudad().toString().trim().isEmpty()) {
    		throw new ResourceNotFoundException("ID de ciudad necesario");
    	}
    	if(actualizarhotel.getNombreHotel() == null || actualizarhotel.getNombreHotel().trim().isEmpty()) {
    		throw new ResourceNotFoundException("Nombre de hotel necesario");
    	}
    	if(actualizarhotel.getDireccion() == null || actualizarhotel.getDireccion().trim().isEmpty()) {
    		throw new ResourceNotFoundException("direccion de hotel necesario");
    	}
    	if(Integer.toString(actualizarhotel.getEstatus()) == null || Integer.toString(actualizarhotel.getEstatus()).toString().trim().isEmpty()) {
    		throw new ResourceNotFoundException("estatus de hotel necesario");
    	}
    	
    	return hotelservice.updateHotel(actualizarhotel,id);
    }
    
    @DeleteMapping(value="/delete-hotel/{id:[\\d]+}")
    public RespuestaEliminarDto eliminarHotel(@PathVariable("id") Long id) {
    	return hotelservice.eliminarHotel(id);
    }
    
    @PutMapping(value="/cambiar-estatus-hotel/{id:[\\d]+}")
	public Respuesta cambiarEstatus(@PathVariable("id") Long id) {
		return hotelservice.cambiarEstatus(id);
	}
   
}

package mx.com.basantader.AgenciaViajeTD.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.ResourceNotFoundException;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;

@RestController
@RequestMapping("/hoteles")
@Api(value = "Endpoints para obtener lista de hoteles, obtener por nombre y codigo de hotel")
@CrossOrigin(origins = "http://localhost:4200")
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
    	return hotelservice.getHotelBycodigo(codHotel);
    }
    
    @PostMapping(value="/nuevo-hotel",produces="application/json")
    public HotelDto crearHotel(@RequestBody HotelDto nuevoReg) {
    	if(nuevoReg.getCodigoHotel() == null) {
    		throw new ResourceNotFoundException("Codigo de hotel necesario");
    	}
    	
    	return hotelservice.createHotel(nuevoReg);
    }
    
    @PutMapping(value="/update-hotel/{id:[\\d]+}",produces="application/json")
    public HotelDto updateHotel(@PathVariable Long id,@RequestBody HotelDto actualizarhotel) {
    	if(actualizarhotel.getCodigoHotel() == null) {
    		throw new ResourceNotFoundException("Codigo de hotel necesario");
    	}
    	
    	return hotelservice.updateHotel(actualizarhotel,id);
    }
    
    @DeleteMapping(value="/delete-hotel/{id:[\\d]+}")
    public RespuestaEliminarDto eliminarHotel(@PathVariable("id") Long id) {
    	return hotelservice.eliminarHotel(id);
    }
   
}

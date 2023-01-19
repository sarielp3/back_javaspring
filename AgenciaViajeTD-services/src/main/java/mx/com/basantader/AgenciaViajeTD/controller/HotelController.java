package mx.com.basantader.AgenciaViajeTD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.HotelesDto;
import mx.com.basantader.AgenciaViajeTD.dto.ReservasDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;

@RestController
@RequestMapping("/hoteles")
@Api(value = "Endpoints para obtener lista de hoteles, obtener por nombre y codigo de hotel")
public class HotelController {
    
    @Autowired
    HotelService hotelservice;
    
    @GetMapping( produces = "application/json")
    @ApiOperation(value = "Ver lista de hoteles", response = HotelesDto.class)
    public List<HotelesDto> listahoteles(){
        return hotelservice.getallHoteles();
    }
    
    @GetMapping(value="/filtros", produces = "application/json")
    @ApiOperation(value = "Ver lista de hoteles por filtros", response = HotelesDto.class)
    public List<HotelesDto> getHotel(@RequestParam(required = false, value = "") String nomHotel,@RequestParam(required = false ,value = "") String codHotel
    		,@RequestParam(required = false ,value = "") Long ciudad) {
    	return hotelservice.getHotelbyName(nomHotel,codHotel,ciudad);
    }
    
    @GetMapping(value="/codigo/{codHotel}", produces = "application/json")
    @ApiOperation(value = "Ver hotel por codigo de hotel", response = HotelesDto.class)
    public HotelesDto getCodigoHotel(@PathVariable String codHotel) {
    	return hotelservice.getHotelBycodigo(codHotel);
    }
    
    @PostMapping(value="/nuevoHotel",produces="application/json")
    public HotelesDto crearHotel(@RequestBody HotelesDto nuevoReg) {
    	if(nuevoReg.getCodigoHotel() == null) {
    		throw new BusinessException("Codigo de hotel necesario");
    	}
    	
    	return hotelservice.createHotel(nuevoReg);
    }
   
}

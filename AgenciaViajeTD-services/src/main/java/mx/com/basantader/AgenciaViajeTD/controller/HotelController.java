package mx.com.basantader.AgenciaViajeTD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;

@RestController
@RequestMapping("/hoteles")
@Api(value = "Endpoints para obtener lista de hoteles, obtener por nombre y codigo de hotel")
public class HotelController {
    
    @Autowired
    HotelService hotelservice;
    
    @GetMapping( produces = "application/json")
    public List<HotelDto> listahoteles(){
        return hotelservice.getallHoteles();
    }
    
    @GetMapping(value="/filtros", produces = "application/json")
    public HotelDto getHotel(@RequestParam(required = false, value = "") String nomHotel,@RequestParam(required = false ,value = "") String codHotel
    		,@RequestParam(required = false ,value = "") Long ciudad) {
    	return hotelservice.getHotelbyName(nomHotel,codHotel,ciudad);
    }
    
    @GetMapping(value="/codigo/{ciudad_hotel}", produces = "application/json")
    public HotelDto getCodigoHotel(@PathVariable Long ciudad_hotel) {
    	return hotelservice.getHotelByciudad(ciudad_hotel);
    }
   
}

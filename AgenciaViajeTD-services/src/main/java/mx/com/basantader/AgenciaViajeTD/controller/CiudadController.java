package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import mx.com.basantader.AgenciaViajeTD.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ciudades")
@Api(value = "Endpoints para obtener lista de paises y pais por nombre")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @GetMapping( produces = "application/json")
    public List<CiudadDto> ListaCiudades(){
        return ciudadService.getAllCiudades();
    }
    
    @GetMapping(value = "/{nombreCiudad}",  produces = "application/json")
    public CiudadDto getCiudad(@PathVariable String nombreCiudad){
        return ciudadService.getCiudadbyName(nombreCiudad);
    }
    
    
}

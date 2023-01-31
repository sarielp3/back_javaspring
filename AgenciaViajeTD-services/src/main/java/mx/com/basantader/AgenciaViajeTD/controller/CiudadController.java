package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudades")
@Api(value = "Endpoints para obtener listas ciudades")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanapptdd01.azurewebsites.net"} )
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @GetMapping( produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las ciudades", response = CiudadDto.class)
    public List<CiudadDto> ListaCiudades(){
        return ciudadService.getAllCiudades();
    }
    
    @GetMapping(value = "/{nombreCiudad}",  produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra el registro de la ciudad a partir del nombre", response = CiudadDto.class)
    public CiudadDto getCiudad(@PathVariable String nombreCiudad){
        return ciudadService.getCiudadByName(nombreCiudad);
    }

    @GetMapping(value = "/origen",  produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las ciudades filtradas por origen", response = CiudadDto.class)
    public List<CiudadDto> getCiudadesByOrigen(){
        return ciudadService.getCiudadesByOrigen();
    }

    @GetMapping(value = "/destino",  produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las ciudades filtradas por destino", response = CiudadDto.class)
    public List<CiudadDto> getCiudadesbyDestino(){
        return ciudadService.getCiudadesByDestino();
    }
}

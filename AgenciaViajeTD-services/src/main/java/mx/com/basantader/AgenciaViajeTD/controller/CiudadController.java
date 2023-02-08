package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/ciudades")
@Api(value = "Endpoints para obtener listas ciudades")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanfetdd01.azurewebsites.net"} )
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @GetMapping( produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las ciudades", response = CiudadDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok, Se encontraton y devolvieron los recursos solicitados")
	})
    public List<CiudadDto> ListaCiudades(){
        return ciudadService.getAllCiudades();
    }
    
    @GetMapping(value = "/{nombreCiudad}",  produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra el registro de la ciudad a partir del nombre", response = CiudadDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok, Se encontraton y devolvieron los recursos solicitados"),
			@ApiResponse(code = 404, message = "Not Found, No se encuentran conicidencias con el parametro indicado")
	})
    public CiudadDto getCiudad(@PathVariable String nombreCiudad){
        return ciudadService.getCiudadByName(nombreCiudad);
    }

    @GetMapping(value = "/origen",  produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las ciudades filtradas por origen", response = CiudadDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok, Se encontraton y devolvieron los recursos solicitados")
	})
    public List<CiudadDto> getCiudadesByOrigen(){
        return ciudadService.getCiudadesByOrigen();
    }

    @GetMapping(value = "/destino",  produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las ciudades filtradas por destino", response = CiudadDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "Ok, Se encontraton y devolvieron los recursos solicitados")
	})
    public List<CiudadDto> getCiudadesbyDestino(){
        return ciudadService.getCiudadesByDestino();
    }
    
    @PostMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Creacion de nuevo registro de ciudad en base de datos")
    @ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Se guardo la ciudad correctamente", response = CiudadDto.class),
			@ApiResponse(code = 400, message = "Bad Request, Error en los parametros de request body"),
			@ApiResponse(code = 404, message = "Not Found, No se encuentran los parametros vinculados con el vuelo dados en body request")
	})
    public CiudadDto createCiudad(@Valid @RequestBody CiudadDto ciudadDto){
        return ciudadService.createCiudad(ciudadDto);
    }
}

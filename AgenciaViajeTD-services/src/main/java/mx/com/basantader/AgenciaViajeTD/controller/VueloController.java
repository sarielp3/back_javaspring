package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.Respuesta;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/vuelos")
@Api(value = "Endpoins para administracion de vuelos")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanapptdd01.azurewebsites.net"} )
public class VueloController {

    @Autowired
    VueloService vueloService;


    @GetMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "vista de la lista de todos los vuelos, asi como filtrados ", response = VueloDto.class)
    public List<VueloDto> listaVuelosfiltrados(
    		@RequestParam(required = false) Long hotel,
            @RequestParam(required = false) Long origen,
            @RequestParam(required = false) Long destino,
            @RequestParam(required = false) Long aerolinea
    ){
        return vueloService.getVuelosByFiltros(origen, destino, aerolinea);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Guarda un vuelo en la base de datos", response = AltaVueloDto.class)
    public AltaVueloDto createVuelo(@Valid @RequestBody AltaVueloDto vueloDto){
        return vueloService.createVuelo(vueloDto);
    }
    
    @PutMapping(value = "/{idVuelo}", produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "actauliza los datos del vuelo correspondiente al id proporcionado ", response = AltaVueloDto.class)
    public AltaVueloDto updateVuelo(@Valid @RequestBody AltaVueloDto vueloDto, @PathVariable("idVuelo") Long idVuelo) {
    	return vueloService.updateVuelo(vueloDto, idVuelo);
    }
    
    @PutMapping(value = "/cambiar-estado/{idVuelo}", produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Cambia el estatus de vuelo ", response = Respuesta.class)
    public Respuesta updateEstatusVuelo(@PathVariable("idVuelo") Long idVuelo) {
    	return vueloService.updateEstatusVuelo(idVuelo);
    }
    
    @DeleteMapping(value = "/{idVuelo}", produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Cambia el estatus de vuelo ", response = Respuesta.class)
    public Respuesta deleteVuelo(@PathVariable("idVuelo") Long idVuelo) {
    	return vueloService.deleteVuelo(idVuelo);
    }
}

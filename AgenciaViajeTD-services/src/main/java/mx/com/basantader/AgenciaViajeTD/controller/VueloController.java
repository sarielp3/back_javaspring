package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanfetdd01.azurewebsites.net"} )
public class VueloController {

    @Autowired
    VueloService vueloService;


    @GetMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "listado de los vuelos", 
    	notes = "Muestra la lista completa de los vuelos registrados en base de datos, "
    			+ "asi como lista filtrada por los parametros de ciudad de origen, ciudade de destino y aerolinea")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "Ok, Operacion exitosa", response = VueloDto.class),
    		@ApiResponse(code = 400, message = "Bad Request, Error en los parametros de filtrado de datos"),
    		@ApiResponse(code = 404, message = "Not Found, No se encontro el recurso solicitado")
    })
    public List<VueloDto> listaVuelosfiltrados(
    		@RequestParam(required = false) Long hotel,
            @RequestParam(required = false) Long origen,
            @RequestParam(required = false) Long destino,
            @RequestParam(required = false) Long aerolinea
    ){
        return vueloService.getVuelosByFiltros(origen, destino, aerolinea);
    }

    @PostMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Creacion de nuevo registro de vuelos en base de datos", response = VueloDto.class)
    @ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Se guardo en vuelo correctamente", response = VueloDto.class),
			@ApiResponse(code = 400, message = "Bad Request, Error en los parametros de request body"),
			@ApiResponse(code = 404, message = "Not Found, No se encuentran los parametros vinculados con el vuelo dados en body request")
	})
    public VueloDto createVuelo(@Valid @RequestBody AltaVueloDto vueloDto){
        return vueloService.createVuelo(vueloDto);
    }
    
    @PutMapping(value = "/{idVuelo}", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "actaulizacion los datos del vuelo correspondiente al id proporcionado ", response = AltaVueloDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Ok, Se actualizaron los datos del vuelo correctamente", response = VueloDto.class),
			@ApiResponse(code = 400, message = "Bad Request, Error en parametros de request body de vuelo "),
			@ApiResponse(code = 404, message = "Not Found, No se encuentran el indice del vuelo a actualizar")
	})
    public AltaVueloDto updateVuelo(@Valid @RequestBody AltaVueloDto vueloDto, @PathVariable("idVuelo") Long idVuelo) {
    	return vueloService.updateVuelo(vueloDto, idVuelo);
    }
    
    @PutMapping(value = "/cambiar-estado/{idVuelo}", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cambia el estatus de vuelo ", response = Respuesta.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Ok, Se actualizo el estatus del vuelo correctamente", response = VueloDto.class),
			@ApiResponse(code = 400, message = "Bad Request, Error en parametro de indidice de vuelo "),
			@ApiResponse(code = 404, message = "Not Found, No se encontro el indice del vuelo a actualizar")
	})
    public Respuesta updateEstatusVuelo(@PathVariable("idVuelo") Long idVuelo) {
    	return vueloService.updateEstatusVuelo(idVuelo);
    }
    
    @DeleteMapping(value = "/{idVuelo}", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Eliminacion de registro de vuelo ", response = Respuesta.class)
	@ApiResponses(value = {
			@ApiResponse(code = 202, message = "Ok, Se elimino el registro del vuelo correctamente", response = VueloDto.class),
			@ApiResponse(code = 400, message = "Bad Request, Error en parametro de indidice de vuelo "),
			@ApiResponse(code = 404, message = "Not Found, No se encontro el indice del vuelo a eliminar")
	})
    public Respuesta deleteVuelo(@PathVariable("idVuelo") Long idVuelo) {
    	return vueloService.deleteVuelo(idVuelo);
    }
}

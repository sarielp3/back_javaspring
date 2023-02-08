package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;
import mx.com.basantader.AgenciaViajeTD.service.CuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanfetdd01.azurewebsites.net"} )
public class CuartoController {

    @Autowired
    private CuartoService cuartosService;

    @GetMapping(value = "filter-cuartos/{idHotel:[\\d]+}")
    @ResponseBody
	@ApiOperation(value = "Ver lista de cuartos de hotel", response = CuartoDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK, Recursos solicitados encontrados"),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el cuarto ingresados")
	})
    public List<CuartoDto> filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }
    
    @GetMapping(value = "/lista-cuartos")
    @ResponseBody
	@ApiOperation(value = "Ver lista de cuartos de hotel", response = CuartoDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK, Recursos solicitados encontrados"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los recursos solicitados")
	})
    public List<CuartoDto> listaCuartos()
    {
    	return  cuartosService.listaCuartos();
    }

    @PostMapping(value = "/agregar/{idHotel:[\\d]+}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crear nuevo registro de cuarto", response = CuartoDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Se registro cuarto de manera exitosa"),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el cuarto ingresados")
	})
    public CuartoDto crearCuarto(@Valid @RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){
        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }
    

    @PutMapping(value = "/modificar/{idCuarto}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Actualizar registro de cuarto", response = CuartoDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Se actualizo registro cuarto de manera exitosa"),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el cuarto ingresados")
	})
    public  CuartoDto modificarCuarto(@Valid @RequestBody CuartoDto cuartoDto, @PathVariable Long idCuarto){
        return  cuartosService.modificarCuarto(cuartoDto, idCuarto);
    }

    @DeleteMapping(value = "/eliminar-cuarto/{idCuarto:[\\d]+}")
    @ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Eliminar registro de cuarto")
	@ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted, Se elimino el cuarto correctamente", response = ReservaDto.class),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron el cuarto con el identificador ingresado")
	})
    public RespuestaEliminarDto eliminarCuarto(@PathVariable("idCuarto") Long idCuarto){
        return  cuartosService.eliminarCuarto(idCuarto);
    }

    @PostMapping(value = "/status-cuarto/{idCuarto:[\\d]+}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Actualizar estatus de cuarto", response = CuartoDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Se actualizo el estado del cuarto de manera exitosa"),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el cuarto ingresados")
	})
    public CuartoDto statusCuarto(@PathVariable(required = true, value = "") Long idCuarto){
        return  cuartosService.statusCuartos(idCuarto);
    }
}

package mx.com.basantader.AgenciaViajeTD.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.basantader.AgenciaViajeTD.dto.HotelDto;
import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
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
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK, Hoteles encontrada"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el hotel")
	})
    public List<HotelDto> listahoteles(){
    	
        return hotelservice.getallHoteles();
    }
    
    @GetMapping(value="/filtros", produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Ver lista de hoteles por filtros", response = HotelDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK, Hoteles encontrada"),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el hotel")
	})
    public List<HotelDto> getHotel(@RequestParam(required = false) String nomHotel,@RequestParam(required = false) String codHotel
    		, @RequestParam(required=false) Long ciudad) {
    	return hotelservice.getHotelbyName(nomHotel,codHotel,ciudad);
    }
    
    @GetMapping(value="/codigo/{codHotel}", produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Ver hotel por codigo de hotel", response = HotelDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK, Hoteles encontrada"),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el hotel")
	})
    public HotelDto getCodigoHotel(@PathVariable String codHotel) {
    	if(codHotel == null) {
    		throw new ResourceNotFoundException("Codigo de hotel necesario");
    	}
    	return hotelservice.getHotelBycodigo(codHotel);
    }
    
    @PostMapping(value="/nuevo-hotel",produces="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Crear nuevo registro de hotel", response = HotelDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Registro de hotel exitoso"),
            @ApiResponse(code = 400, message = "Bad Request, Error en parametros de request body"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el hotel")
	})
    public HotelDto crearHotel(@Valid @RequestBody HotelDto nuevoReg) { 
    	
    	return hotelservice.createHotel(nuevoReg);
    }
    
    @PutMapping(value="/update-hotel/{id:[\\d]+}",produces="application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Actualizacion de registro de hotel por codigo de hotel", response = HotelDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created,Actualizacion de registro de hotel exitoso"),
            @ApiResponse(code = 400, message = "Bad Request, Error en parametros de request body"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el hotel")
	})
    public HotelDto updateHotel(@Valid @PathVariable("id") Long id,@RequestBody HotelDto actualizarhotel) {
    	
    	return hotelservice.updateHotel(actualizarhotel,id);
    }
    
    @DeleteMapping(value="/delete-hotel/{id:[\\d]+}")
    @ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Eliminar registro de hotel")
	@ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted, Se elimino el registro correctamente", response = ReservaDto.class),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encontro el hotel con el identificador ingresado")
	})
    public RespuestaEliminarDto eliminarHotel(@PathVariable("id") Long id) {
    	return hotelservice.eliminarHotel(id);
    }
    
    @PutMapping(value="/cambiar-estatus-hotel/{id:[\\d]+}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Actualizacion de estatus de hotel", response = HotelDto.class)
    @ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Cambio de estado de hotel exitoso"),
            @ApiResponse(code = 400, message = "Bad Request, Error en parametros de request body"),
            @ApiResponse(code = 404, message = "Not Found, No se encontraron los parametros vinculados con el hotel")
	})
	public Respuesta cambiarEstatus(@PathVariable("id") Long id) {
		return hotelservice.cambiarEstatus(id);
	}
   
}

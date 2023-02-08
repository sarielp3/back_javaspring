package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.basantader.AgenciaViajeTD.dto.AerolineaDto;
import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/aerolineas")
@Api(value = "Endpoins para administracion de aerolineas")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanfetdd01.azurewebsites.net"} )
public class AerolineaController {

    @Autowired
    AerolineaService aerolineaService;

    @GetMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Muestra la lista de todas las aerolineas", response = AerolineaDto.class)
    public List<AerolineaDto> getAllAerolineas(){
        return aerolineaService.getAllAerolineas();
    }
    
    @PostMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Creacion de nuevo registro de aerolinea en base de datos")
    @ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Created, Se guardo la aerolinea correctamente", response = CiudadDto.class),
			@ApiResponse(code = 400, message = "Bad Request, Error en los parametros de request body"),
			@ApiResponse(code = 404, message = "Not Found, No se encuentran los parametros vinculados con el vuelo dados en body request")
	})
    public AerolineaDto createCiudad(@Valid @RequestBody AerolineaDto aerolineaDto){
        return aerolineaService.createAerolinea(aerolineaDto);
    }
}

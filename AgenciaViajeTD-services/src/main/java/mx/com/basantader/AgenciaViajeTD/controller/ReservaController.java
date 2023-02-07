package mx.com.basantader.AgenciaViajeTD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.List;
import javax.validation.Valid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.dto.AltaReservaDto;
import mx.com.basantader.AgenciaViajeTD.service.ReservaService;

@RestController
@RequestMapping("/reservas")
@Api(value = "Reservas en Agencia de viajes")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanfetdd01.azurewebsites.net"} )

public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Ver lista de Reservas", response = AltaReservaDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK, Reserva encontrada", response = ReservaDto.class),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encuentran los parametros vinculados con la reserva ingresada")
	})
	public List<ReservaDto> listaReservas(
			@RequestParam(required = false) Long hotel,
			@RequestParam(required = false) Long origen, 
			@RequestParam(required = false) Long destino,
			@RequestParam(required = false) Long aerolinea) {
		return reservaService.getReservasByFiltros(hotel, origen, destino, aerolinea);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = { "/creart" })
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crear Reserva")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created, Se guardo en reserva correctamente", response = ReservaDto.class),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de request body"),
            @ApiResponse(code = 404, message = "Not Found, No se encuentran los parametros vinculados con la reserva dados en body request")
	})
	public AltaReservaDto createReserva(@RequestBody @Valid AltaReservaDto createReserva) {
		return reservaService.createReserva(createReserva);

	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = {
			"/actualizar/{id}" })
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Modificar una reserva")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created, Se guardo en reserva correctamente", response = ReservaDto.class),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros ingresados en el body request"),
            @ApiResponse(code = 404, message = "Not Found, No se encuentran los parametros vinculados con la reserva ingresada")
	})
	public AltaReservaDto updateReserva(@PathVariable("id") Long idReserva, @Valid @RequestBody AltaReservaDto updateReserva) {
		updateReserva.setIdReserva(idReserva);
		return reservaService.updateReserva(updateReserva);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = {
			"/eliminar/{id}" })
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Eliminar una reserva")
	@ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted, Se elimino la reserva correctamente", response = ReservaDto.class),
            @ApiResponse(code = 400, message = "Bad Request, Error en los parametros de busqueda"),
            @ApiResponse(code = 404, message = "Not Found, No se encuentran la reserva con el identificador ingresado")
	})
	public void deleteReservaEntity(@PathVariable("id") Long idReserva) {
		reservaService.deleteReservaEntity(idReserva);
	}

}

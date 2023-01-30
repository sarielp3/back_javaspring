package mx.com.basantader.AgenciaViajeTD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.ReservaDto;
import mx.com.basantader.AgenciaViajeTD.dto.AltaReservaDto;
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;
import mx.com.basantader.AgenciaViajeTD.service.ReservaService;

@RestController
@RequestMapping("/reservas")
@Api(value = "Reservas en Agencia de viajes")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanapptdd01.azurewebsites.net"} )

public class ReservaController {

	private static final Logger log = LoggerFactory.getLogger(ReservaController.class);

	@Autowired
	private ReservaService reservaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Ver lista de Reservas", response = AltaReservaDto.class)
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
	public AltaReservaDto createReserva(@RequestBody @Valid AltaReservaDto createReserva) {
		return reservaService.createReserva(createReserva);

	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = {
			"/actualizar/{id}" })
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Modificar una reserva")
	public void updateReserva(@PathVariable("id") Long idReserva, @Valid @RequestBody AltaReservaDto updateReserva) {
		updateReserva.setIdReserva(idReserva);
		reservaService.updateReserva(updateReserva);
	}

	@DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = {
			"/eliminar/{id}" })
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Eliminar una reserva")
	public void deleteReservaEntity(@PathVariable("id") Long idReserva) {
		reservaService.deleteReservaEntity(idReserva);
	}

}

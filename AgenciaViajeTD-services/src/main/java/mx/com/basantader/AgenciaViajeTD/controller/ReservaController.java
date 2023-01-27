package mx.com.basantader.AgenciaViajeTD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;
import mx.com.basantader.AgenciaViajeTD.service.ReservaService;

@RestController
@RequestMapping("/reservas")
@Api(value = "Reservas en Agencia de viajes")
@CrossOrigin(origins = "http://localhost:4200")

public class ReservaController {

	private static final Logger log = LoggerFactory.getLogger(ReservaController.class);

	@Autowired
	private ReservaService reservaService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Ver lista de Reservas", response = ReservaDto.class)
	public List<ReservaDto> listaReservas(@RequestParam(required = false) Long cuarto,
			@RequestParam(required = false) Long origen, @RequestParam(required = false) Long destino,
			@RequestParam(required = false) Long aerolinea) {
		return reservaService.getReservasByFiltros(cuarto, origen, destino, aerolinea);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = { "/creart" })
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Crear Reserva")
	public ReservaEntity createReserva(@RequestBody @Valid ReservaDto createReserva) {
		return reservaService.createReserva(createReserva);

	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, value = {
			"/actualizar/{id}" })
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Modificar una reserva")
	public void updateReserva(@PathVariable("id") Long idReserva, @Valid @RequestBody ReservaDto updateReserva) {
		updateReserva.setIdReserva(idReserva);
		reservaService.updateReserva(updateReserva);
	}

	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, value = {
			"/eliminar/{id}" })
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiOperation(value = "Eliminar una reserva")
	public void deleteReservaEntity(@PathVariable("id") Long idReserva) {
		reservaService.deleteReservaEntity(idReserva);
	}

}

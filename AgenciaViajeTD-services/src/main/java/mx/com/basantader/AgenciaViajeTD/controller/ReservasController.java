package mx.com.basantader.AgenciaViajeTD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.ReservasDto;
import mx.com.basantader.AgenciaViajeTD.model.ReservasEntity;
import mx.com.basantader.AgenciaViajeTD.service.ReservasService;

@RestController
@RequestMapping("/reservas")
@Api(value = "Reservas en Agencia de viajes")

public class ReservasController {

	private static final Logger log = LoggerFactory.getLogger(ReservasController.class);

	@Autowired
	private ReservasService reservasService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Ver lista de Reservas", response = ReservasDto.class)
	public List<ReservasDto> reservas() {
		log.debug("Recuperando todas las alertas");
		return reservasService.getAllReservas();
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/{vuelo}")
	@ResponseBody
	@ApiOperation(value = "Ver vuelo asignado a la reserva", response = ReservasDto.class)
	public ReservasEntity reserva(@PathVariable("vuelo") Long Vuelo) {
		log.debug("Recuperando la reserva");
		return reservasService.getReservaById(Vuelo);

	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, value = { "/creart" })
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Create an application entry into the application manager")
	public void createReserva(@Valid @RequestBody ReservasDto createReserva) {
	reservasService.createReserva(createReserva);
	}
	
//	@PostMapping(value = "/crear")
//	public ResponseEntity<ReservasDto> crear(@RequestBody ReservasDto reserva){
//		ReservasEntity save = reservasService.createReserva(reserva);
//		return new ResponseEntity<ReservasDto>(HttpStatus.CREATED);
//	}

}

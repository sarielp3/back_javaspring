package mx.com.basantader.AgenciaViajeTD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
	
	@RequestMapping(method = RequestMethod.GET, produces = "json", value = { "/mostarTodas" })
	@ResponseBody
	@ApiOperation(value = "Ver lista de Reservas", response = ReservasDto.class)
	public List<ReservasDto> allReservas() {
		log.debug("Recuperando todas las alertas");
		return reservasService.getReservasEntity();
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "json", value={"/{id}"})
    @ResponseBody
    @ApiOperation(value = "Ver vuelo asignado a la reserva", response = ReservasDto.class)
	public ReservasEntity reservaId(@RequestParam(required = false) Long idVuelo, @RequestParam(required = false) Long idHotel ) {
		log.debug("Recuperando la reserva");
		return (ReservasEntity) reservasService.getReservaByVueloOrHotel(idVuelo, idHotel);
		
	}

}

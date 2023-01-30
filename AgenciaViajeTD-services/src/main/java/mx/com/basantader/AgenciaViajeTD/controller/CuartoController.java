package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;
import mx.com.basantader.AgenciaViajeTD.service.CuartoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
@CrossOrigin(origins = {"http://localhost:4200","https://capbasanapptdd01.azurewebsites.net"} )
public class CuartoController {

    private  static final Logger log = LoggerFactory.getLogger(CuartoController.class);

    @Autowired
    private CuartoService cuartosService;

    @GetMapping(value = "filter-cuartos/{idHotel:[\\d]+}")
    public List<CuartoDto> filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel:[\\d]+}")
    public CuartoDto crearCuarto(@Valid @RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){
        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }
    

    @PutMapping(value = "/modificar/{idCuarto}")
    public  CuartoDto modificarCuarto(@Valid @RequestBody CuartoDto cuartoDto, @PathVariable Long idCuarto){
        return  cuartosService.modificarCuarto(cuartoDto, idCuarto);
    }

    @DeleteMapping(value = "/eliminar-cuarto/{idCuarto:[\\d]+}")
    public RespuestaEliminarDto eliminarCuarto(@PathVariable("idCuarto") Long idCuarto){
        return  cuartosService.eliminarCuarto(idCuarto);
    }

    @PostMapping(value = "/status-cuarto/{idCuarto:[\\d]+}")
    public CuartoDto statusCuarto(@PathVariable(required = true, value = "") Long idCuarto){
        return  cuartosService.statusCuartos(idCuarto);
    }
}

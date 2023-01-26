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

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
@CrossOrigin(origins = "http://localhost:4200" )
public class CuartoController {

    private  static final Logger log = LoggerFactory.getLogger(CuartoController.class);

    @Autowired
    private CuartoService cuartosService;

    @GetMapping(value = "filter-cuartos/{idHotel:[\\d]+}")
    public List<CuartoDto> filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel:[\\d]+}")
    public CuartoDto crearCuarto(@RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){
        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }
    

    @PutMapping(value = "/modificar/{idCuarto}")
    public  CuartoDto modificarCuarto(@RequestBody CuartoDto cuartoDto, @PathVariable Long idCuarto){
        return  cuartosService.modificarCuarto(cuartoDto, idCuarto);
    }

    @DeleteMapping(value = "/eliminar-cuarto/{idCuarto:[\\d]+}")
    public RespuestaEliminarDto eliminarCuarto(@PathVariable("idCuarto") Long idCuarto){
        return  cuartosService.eliminarCuarto(idCuarto);
    }
}

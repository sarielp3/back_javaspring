package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.service.CuartosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
public class CuartosController {

    private  static final Logger log = LoggerFactory.getLogger(CuartosController.class);

    @Autowired
    private CuartosService cuartosService;

    @GetMapping(value = "filter-cuartos/{idHotel}")
    public CuartoDto filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel}")
    public CuartoDto crearCuarto(@RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){

        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }

}

package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
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

    @GetMapping(value = "filter-cuartos/{idHotel}")
    public List<CuartoDto> filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel}")
    public CuartoDto crearCuarto(@RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){
        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }
    

}

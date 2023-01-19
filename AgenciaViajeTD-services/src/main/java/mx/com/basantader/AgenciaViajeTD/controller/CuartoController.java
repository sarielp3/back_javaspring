package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.service.CuartoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
public class CuartoController {

    private  static final Logger log = LoggerFactory.getLogger(CuartoController.class);

    @Autowired
    private CuartoService cuartosService;

    @GetMapping(value = "filter-cuartos/{idHotel}")
    public CuartoDto filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel}")
<<<<<<< HEAD:AgenciaViajeTD-services/src/main/java/mx/com/basantader/AgenciaViajeTD/controller/CuartoController.java
    public CuartoDto crearCuarto(@RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){
=======
    public CuartosDTO crearCuarto(@RequestBody CuartosDTO cuartoAdd, @PathVariable("idHotel") Long idHotel){
>>>>>>> Development:AgenciaViajeTD-services/src/main/java/mx/com/basantader/AgenciaViajeTD/controller/CuartosController.java

        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }

}

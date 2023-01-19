package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CuartosDTO;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.repository.CuartosRepository;
import mx.com.basantader.AgenciaViajeTD.service.CuartosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
public class CuartosController {

    private  static final Logger log = LoggerFactory.getLogger(CuartosController.class);

    @Autowired
    private CuartosService cuartosService;

    @GetMapping(value = "filter-cuartos/{idHotel}")
    public CuartosDTO filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartosService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel}")
    public CuartosDTO crearCuarto(@RequestBody CuartosDTO cuartoAdd, @PathVariable("idHotel") Long idHotel){

        return cuartosService.crearCuarto(cuartoAdd, idHotel);
    }

}

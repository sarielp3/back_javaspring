package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.repository.CuartoRepository;
import mx.com.basantader.AgenciaViajeTD.service.CuartoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuartos")
@Api(value = "CRUD de tabla Cuartos")
public class CuartoController {

    private  static final Logger log = LoggerFactory.getLogger(CuartoController.class);

    @Autowired
    private CuartoService cuartoService;

    @GetMapping(value = "filter-cuartos/{idHotel}")
    public CuartoDto filterCuartos(@PathVariable("idHotel") Long idHotel){
        return  cuartoService.filterCuartosById(idHotel);
    }

    @PostMapping(value = "/agregar/{idHotel}")
    public CuartoDto crearCuarto(@RequestBody CuartoDto cuartoAdd, @PathVariable("idHotel") Long idHotel){

        return cuartoService.crearCuarto(cuartoAdd, idHotel);
    }

}

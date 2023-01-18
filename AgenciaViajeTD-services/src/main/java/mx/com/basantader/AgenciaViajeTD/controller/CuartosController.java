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
@RequestMapping("/Cuartos")
@Api(value = "CRUD de tabla Cuartos")
public class CuartosController {

    private  static final Logger log = LoggerFactory.getLogger(CuartosController.class);

    @Autowired
    private CuartosService cuartosService;

    @GetMapping(value = "filterCuartos/{idHotel}")
    public CuartosDTO filterCuartos(@PathVariable Long idHotel){
        if (idHotel == null){
            throw  new BusinessException(1);
        }
        return  cuartosService.filterCuartosById(idHotel);
    }
}

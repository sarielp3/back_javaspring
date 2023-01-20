package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.AerolineaDto;
import mx.com.basantader.AgenciaViajeTD.service.AerolineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aerolineas")
@Api(value = "Endpoins para administracion de vuelos")
public class AerolineaController {

    @Autowired
    AerolineaService aerolineaService;

    @GetMapping(produces = "application/json")
    public List<AerolineaDto> getAllAerolineas(){
        return aerolineaService.getAllAerolineas();
    }
}

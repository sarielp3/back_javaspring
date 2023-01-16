package mx.com.basantader.AgenciaViajeTD.controller;

import mx.com.basantader.AgenciaViajeTD.dto.CiudadDto;
import mx.com.basantader.AgenciaViajeTD.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    CiudadService ciudadService;

    @GetMapping()
    public List<CiudadDto> listaCiudades(){
        return ciudadService.getAllCiudades();
    }
    @GetMapping("/{nombreCiudad}")
    public CiudadDto getCiudad(@PathVariable String nombreCiudad){
        return ciudadService.getCiudadbyName(nombreCiudad);
    }
}

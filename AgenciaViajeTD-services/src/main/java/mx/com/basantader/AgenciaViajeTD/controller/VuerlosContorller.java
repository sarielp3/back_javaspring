package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import mx.com.basantader.AgenciaViajeTD.dto.VuelosDto;
import mx.com.basantader.AgenciaViajeTD.service.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
@Api(value = "Endpoins para administracion de vuelos")
public class VuerlosContorller {

    @Autowired
    VuelosService vuelosService;

    @GetMapping(produces = "application/json")
    public List<VuelosDto> listaVuelosfiltrados(
            @RequestParam(required = false) Long origen,
            @RequestParam(required = false) Long destino,
            @RequestParam(required = false) Long aerolinea
    ){
        return vuelosService.getVuelosByFiltros(origen, destino, aerolinea);
    }
}

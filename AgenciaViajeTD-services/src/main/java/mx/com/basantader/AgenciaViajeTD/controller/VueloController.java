package mx.com.basantader.AgenciaViajeTD.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.basantader.AgenciaViajeTD.dto.AltaVueloDto;
import mx.com.basantader.AgenciaViajeTD.dto.VueloDto;
import mx.com.basantader.AgenciaViajeTD.service.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vuelos")
@Api(value = "Endpoins para administracion de vuelos")
public class VueloController {

    @Autowired
    VueloService vueloService;


    @GetMapping(produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "vista de la lista de todos los vuelos, asi como filtrados ", response = VueloDto.class)
    public List<VueloDto> listaVuelosfiltrados(
            @RequestParam(required = false) Long origen,
            @RequestParam(required = false) Long destino,
            @RequestParam(required = false) Long aerolinea
    ){
        return vueloService.getVuelosByFiltros(origen, destino, aerolinea);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Guarda un vuelo en la base de datos", response = AltaVueloDto.class)
    public AltaVueloDto createVuelo(@RequestBody AltaVueloDto vueloDto){
        return vueloService.createVuelo(vueloDto);
    }
}

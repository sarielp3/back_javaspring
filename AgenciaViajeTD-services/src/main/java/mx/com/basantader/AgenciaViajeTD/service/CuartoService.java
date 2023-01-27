package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;
import mx.com.basantader.AgenciaViajeTD.dto.RespuestaEliminarDto;

import java.util.List;

public interface CuartoService {

    List<CuartoDto> filterCuartosById(Long idHotel);

    CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel);

    CuartoDto modificarCuarto(CuartoDto cuartoDto, Long idCuarto);
    RespuestaEliminarDto eliminarCuarto(Long idCuarto);
    List<CuartoDto> listaCuartos();

    CuartoDto statusCuartos(Long idCuarto);
}

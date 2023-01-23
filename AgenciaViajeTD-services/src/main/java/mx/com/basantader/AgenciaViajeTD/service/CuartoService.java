package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;

import java.util.List;

public interface CuartoService {

    List<CuartoDto> filterCuartosById(Long idHotel);

    CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel);

    CuartoDto modificarCuarto(CuartoDto cuartoDto, Long idCuarto);
}
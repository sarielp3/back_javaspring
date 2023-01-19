package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.CuartoDto;

public interface CuartosService {

    CuartoDto filterCuartosById(Long idHotel);

    CuartoDto crearCuarto(CuartoDto cuartoAdd, Long idHotel);
}

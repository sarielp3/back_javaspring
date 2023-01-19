package mx.com.basantader.AgenciaViajeTD.service;

import mx.com.basantader.AgenciaViajeTD.dto.CuartosDTO;

public interface CuartosService {

    CuartosDTO filterCuartosById(Long idHotel);

    CuartosDTO crearCuarto(CuartosDTO cuartoAdd, Long idHotel);
}

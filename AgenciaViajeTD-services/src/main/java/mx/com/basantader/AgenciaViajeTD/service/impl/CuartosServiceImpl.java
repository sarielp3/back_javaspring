package mx.com.basantader.AgenciaViajeTD.service.impl;


import mx.com.basantader.AgenciaViajeTD.dto.CuartosDTO;
import mx.com.basantader.AgenciaViajeTD.exceptions.BusinessException;
import mx.com.basantader.AgenciaViajeTD.model.CuartosEntity;
import mx.com.basantader.AgenciaViajeTD.repository.CuartosRepository;
import mx.com.basantader.AgenciaViajeTD.repository.HotelRepository;
import mx.com.basantader.AgenciaViajeTD.service.CuartosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuartosServiceImpl implements CuartosService {
    private static final Logger log = LoggerFactory.getLogger(CuartosServiceImpl.class);
    @Autowired
    private CuartosRepository cuartosRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public CuartosDTO filterCuartosById(Long idHotel) {
        Optional<CuartosEntity> filter = cuartosRepository.findById(idHotel);

        if (!filter.isPresent()){
            throw new BusinessException(6);
        }

        CuartosDTO cuartosDTO = new CuartosDTO();

        cuartosDTO.setIdCuarto(filter.get().getIdCuarto());
        cuartosDTO.setNombreCuarto(filter.get().getNombreCuarto());
        cuartosDTO.setDescripcion(filter.get().getDescripcion());
        cuartosDTO.setNumeroPersonas(filter.get().getNumeroPersonas());
        cuartosDTO.setCodigoCuartos(filter.get().getCodigoCuartos());
        cuartosDTO.setCostoNoche(filter.get().getCostoNoche());
        cuartosDTO.setTipoCuarto(filter.get().getTipoCuarto());

        //Datos tomados de la tabla hotel
        cuartosDTO.setIdHotel(filter.get().getHotel().getId_hotel());

        return  cuartosDTO;
    }
}

package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.VuelosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VueloRepository extends JpaRepository<VuelosEntity, Long> {

    List<VuelosEntity> findByOrigen(CiudadEntity origen);
    List<VuelosEntity> findByDestino(CiudadEntity destino);
    List<VuelosEntity> findByAerolinea(AerolineaEntity aerolinea);

}

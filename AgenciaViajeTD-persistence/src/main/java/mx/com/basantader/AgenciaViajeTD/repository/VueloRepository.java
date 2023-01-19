package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.VuelosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VueloRepository extends JpaRepository<VuelosEntity, Long> {

    List<VuelosEntity> findByOrigen(CiudadEntity origen);
    List<VuelosEntity> findByDestino(CiudadEntity destino);
    List<VuelosEntity> findByAerolinea(AerolineaEntity aerolinea);

    /*@Query("SELECT v FROM Vuelos v WHERE (:origen is null or v.origen = :origen) " +
            "and (:destino is null or v.destino = :destino) " +
            "and (:id_aerolinea is null or v.id_aerolinea = :id_aerolinea)")
    List<VuelosEntity> findVuelosByFiltros(
            @Param("origen") CiudadEntity origen,
            @Param("destino") CiudadEntity destino,
            @Param("id_aerolinea") AerolineaEntity aerolinea);*/


}

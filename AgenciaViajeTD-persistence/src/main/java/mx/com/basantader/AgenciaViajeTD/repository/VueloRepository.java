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

    @Query("SELECT v FROM VuelosEntity v WHERE (:origen is null or v.origen = :origen) " +
            "and (:destino is null or v.destino = :destino) " +
            "and (:aerolinea is null or v.aerolinea = :aerolinea)")
    List<VuelosEntity> findVuelosByFiltros(
            @Param("origen") CiudadEntity origen,
            @Param("destino") CiudadEntity destino,
            @Param("aerolinea") AerolineaEntity aerolinea);


}

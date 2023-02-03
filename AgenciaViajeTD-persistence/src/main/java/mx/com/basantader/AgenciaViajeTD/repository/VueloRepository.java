package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.VueloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VueloRepository extends JpaRepository<VueloEntity, Long> {

    List<VueloEntity> findByOrigen(CiudadEntity origen);
    List<VueloEntity> findByDestino(CiudadEntity destino);
    List<VueloEntity> findByAerolinea(AerolineaEntity aerolinea);

    @Query("SELECT v FROM VueloEntity v WHERE (:origen is null or v.origen = :origen) " +
            "and (:destino is null or v.destino = :destino) " +
            "and (:aerolinea is null or v.aerolinea = :aerolinea)")
    List<VueloEntity> findVuelosByFiltros(
            @Param("origen") CiudadEntity origen,
            @Param("destino") CiudadEntity destino,
            @Param("aerolinea") AerolineaEntity aerolinea);
    
    @Query("SELECT v.idVuelo FROM VueloEntity v WHERE codigoVuelo = :codigoVuelo")
    Optional<Long> findByCodigoVuelo(@Param("codigoVuelo")String codigoVuelo);
    
    @Query("SELECT v.estatus FROM VueloEntity v WHERE idVuelo = :idVuelo")
    Optional<Long> findEstatusByIdVuelo(@Param("idVuelo")Long idVuelo);
    
    @Modifying
    @Query("UPDATE VueloEntity v SET v.estatus = :estatus WHERE idVuelo = :idVuelo")
    void updateEstatusVuelo(@Param("idVuelo")Long idVuelo, @Param("estatus")Long estatus);




}

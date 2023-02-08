package mx.com.basantader.AgenciaViajeTD.repository;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {
    CiudadEntity findByNombreCiudad(String nombreCiudad);

    @Query("SELECT DISTINCT v.origen FROM VueloEntity v ")
    List<CiudadEntity> findCiudadesByOrigen();

    @Query("SELECT DISTINCT v.destino FROM VueloEntity v ")
    List<CiudadEntity> findCiudadesByDestino();
    
    @Query("SELECT c.idCiudad FROM CiudadEntity c WHERE nombreCiudad = :nombreCiudad")
    Optional<Long> findCiudadByNombre(@Param("nombreCiudad")String nombreCiudad);
}

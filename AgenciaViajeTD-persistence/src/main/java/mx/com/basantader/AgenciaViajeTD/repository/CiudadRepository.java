package mx.com.basantader.AgenciaViajeTD.repository;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {
    CiudadEntity findByNombreCiudad(String nombreCiudad);

    @Query("SELECT DISTINCT v.origen FROM VuelosEntity v ")
    List<CiudadEntity> findCiudadesByOrigen();

    @Query("SELECT DISTINCT v.destino FROM VuelosEntity v ")
    List<CiudadEntity> findCiudadesByDestino();
}

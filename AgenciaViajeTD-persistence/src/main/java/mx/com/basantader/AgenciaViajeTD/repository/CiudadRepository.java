package mx.com.basantader.AgenciaViajeTD.repository;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<CiudadEntity, Long> {
    CiudadEntity findByNombreCiudad(String nombreCiudad);
}

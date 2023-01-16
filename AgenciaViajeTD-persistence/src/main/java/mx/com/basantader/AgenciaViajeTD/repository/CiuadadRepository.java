package mx.com.basantader.AgenciaViajeTD.repository;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiuadadRepository extends JpaRepository<CiudadEntity, Long> {
    CiudadEntity findByNombre_Ciudad(String nombreCiudad);
}

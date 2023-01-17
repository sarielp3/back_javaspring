package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.VuelosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<VuelosEntity, Long> {
}

package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.CuartosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuartosRepository extends JpaRepository<CuartosEntity, Long> {
   Optional<CuartosEntity> finByNombreCuarto(String validarNC);
  // Optional<CuartosEntity> finByCodigoCuarto(String validarCC);
}

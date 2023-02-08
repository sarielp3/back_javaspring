package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AerolineaRepository extends JpaRepository<AerolineaEntity, Long> {
	
	@Query("SELECT a.idAerolinea FROM AerolineaEntity a WHERE nombreAerolinea = :nombreAerolinea")
    Optional<Long> findAerolineaByNombre(@Param("nombreAerolinea")String nombreAerolinea);
}

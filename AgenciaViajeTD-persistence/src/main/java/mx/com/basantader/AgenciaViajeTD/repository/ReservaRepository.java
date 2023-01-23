package mx.com.basantader.AgenciaViajeTD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.CuartoEntity;
import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;
import mx.com.basantader.AgenciaViajeTD.model.VueloEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long>{
	
	@Query ("SELECT r FROM ReservaEntity r JOIN VueloEntity v ON v.idVuelo = r.vuelo WHERE (:cuarto is null or r.cuarto = :cuarto)"
			)
	List<ReservaEntity> findReservasByFiltros(
            @Param("cuarto") CuartoEntity cuarto);
	        
}

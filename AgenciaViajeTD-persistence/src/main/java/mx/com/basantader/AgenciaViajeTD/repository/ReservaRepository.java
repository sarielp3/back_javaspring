package mx.com.basantader.AgenciaViajeTD.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.AerolineaEntity;
import mx.com.basantader.AgenciaViajeTD.model.CiudadEntity;
import mx.com.basantader.AgenciaViajeTD.model.CuartoEntity;

import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {

	@Query("SELECT r FROM ReservaEntity r JOIN VueloEntity v ON v.idVuelo = r.vuelo WHERE (:cuarto is null or r.cuarto = :cuarto)"
			+"and(:origen is null or v.origen = :origen)"
			+"and(:destino is null or v.destino = :destino)"
			+"and(:aerolinea is null or v.aerolinea = :aerolinea)")
	List<ReservaEntity> findReservasByFiltros(
			@Param("cuarto") CuartoEntity cuarto,
			@Param("origen") CiudadEntity origen,
	        @Param("destino") CiudadEntity destino,
	        @Param("aerolinea") AerolineaEntity aerolinea);
	
	@Query("SELECT r.fechaInicio FROM ReservaEntity r WHERE r.fechaInicio = :fechaInicio")
    List<Date> findCuartoByFechaInicio(
    		@Param("fechaInicio") Date fechaInicio);
//	
//	@Query("SELECT r.fechaFin FROM ReservaEntity r WHERE r.fechaFin = :fechaFin")
//    ReservaEntity findCuartoByFechaFin(
//    		@Param("fechaFin") Date fechaFin);

}
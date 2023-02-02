package mx.com.basantader.AgenciaViajeTD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import mx.com.basantader.AgenciaViajeTD.model.ReservaEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{

	@Query("SELECT v FROM HotelEntity v " +      "WHERE (:idCiudad is null or v.ciudad.idCiudad IN (SELECT DISTINCT destino.idCiudad from VueloEntity))" +      "and (:nombreHotel is null or v.nombreHotel = :nombreHotel)" +      "and (:codigohotel is null or v.codigoHotel = :codigohotel)" +      "and (:idCiudad is null or v.ciudad.idCiudad = :idCiudad)")
	List<HotelEntity> encontrarByNombreHotelAndCodigoHotelAndCiudadIdCiudad(String nombreHotel,String codigohotel,Long idCiudad);

	Optional<HotelEntity>findByCodigoHotel(String codHotel);
	
	Optional<HotelEntity> findByNombreHotel(String nomHotel);
	
	@Query("SELECT h.idHotel FROM HotelEntity h WHERE codigoHotel = :codigoHotel")
    Optional<Long> findHotelByCodigo(@Param("codigoHotel")String codigoHotel);
	
	@Query("SELECT h.idHotel FROM HotelEntity h WHERE nombreHotel = :nombreHotel")
    Optional<Long> findHotelByNombre(@Param("nombreHotel")String nombreHotel);
}

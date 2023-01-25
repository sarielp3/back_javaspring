package mx.com.basantader.AgenciaViajeTD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
	
	@Query("SELECT v FROM HotelEntity v WHERE v.ciudad.idCiudad IN (SELECT DISTINCT destino.idCiudad from VueloEntity) and (:nombreHotel is null or v.nombreHotel = :nombreHotel) " +
            "and (:codigohotel is null or v.codigoHotel = :codigohotel) " +
            "and (:idCiudad is null or v.ciudad.idCiudad = :idCiudad)")
	List<HotelEntity> encontrarByNombreHotelAndCodigoHotelAndCiudadIdCiudad(String nombreHotel,String codigohotel,Long idCiudad);

	HotelEntity findByCodigoHotel(String codHotel);
	
}

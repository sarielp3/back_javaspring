package mx.com.basantader.AgenciaViajeTD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
	
	//@Query(value ="Select e from HotelEntity e where e.nombreHotel like %:nombreHotel% or e.codigoHotel like %:codigohotel% or e.ciudad.idCiudad = :idCiudad")
	List<HotelEntity> findByNombreHotelOrCodigoHotelOrCiudadIdCiudad(String nombreHotel,String codigohotel,Long idCiudad);

	//@Query(value ="Select e from HotelEntity e where e.codigoHotel = :codHotel ")
	HotelEntity findByCodigoHotel(String codHotel);
	
}

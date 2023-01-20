package mx.com.basantader.AgenciaViajeTD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
	
	
	List<HotelEntity> findByNombreHotelAndCodigoHotelAndCiudadIdCiudad(String nombreHotel,String codigohotel,Long idCiudad);

	HotelEntity findByCodigoHotel(String codHotel);
	
}

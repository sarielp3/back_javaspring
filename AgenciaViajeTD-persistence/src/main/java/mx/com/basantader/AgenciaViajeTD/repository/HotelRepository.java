package mx.com.basantader.AgenciaViajeTD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long>{
	
	@Query(value ="Select e from HotelEntity e where e.nombre_hotel like %:nombreHotel% or e.codigo_hotel like %:codigohotel% or e.ciudad.idCiudad = :idCiudad")
	HotelEntity buscarFiltro(String nombreHotel,String codigohotel,Long idCiudad);

	@Query(value ="Select e from HotelEntity e where e.ciudad.idCiudad = :idCiudad ")
	HotelEntity findByciudad_hotel(Long idCiudad);
}

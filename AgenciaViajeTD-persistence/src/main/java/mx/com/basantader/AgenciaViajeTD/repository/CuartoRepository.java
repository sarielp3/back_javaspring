package mx.com.basantader.AgenciaViajeTD.repository;

import mx.com.basantader.AgenciaViajeTD.model.CuartoEntity;

import mx.com.basantader.AgenciaViajeTD.model.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuartoRepository extends JpaRepository<CuartoEntity, Long> {
   Optional<CuartoEntity> findByNombreCuarto(String validarNC);
   Optional<CuartoEntity> findByCodigoCuartos(String validarCC);

    List<CuartoEntity> findByHotel(HotelEntity idHotel);
}

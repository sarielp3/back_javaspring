package mx.com.basantader.AgenciaViajeTD.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.com.basantader.AgenciaViajeTD.model.ReservasEntity;

@Repository
public interface ReservasRepository extends JpaRepository<ReservasEntity, Long>{
	

}

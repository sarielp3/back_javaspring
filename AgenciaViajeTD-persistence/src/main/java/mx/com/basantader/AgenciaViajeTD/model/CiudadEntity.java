package mx.com.basantader.AgenciaViajeTD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CIUDADES")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CiudadEntity {
    @Id
    @SequenceGenerator(name = "ciudad", sequenceName = "ciudad_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "ciudad")
    private Long idCiudad;

    @Column(name = "NOMBRE_CIUDAD", nullable = false)
    private String nombreCiudad;
}

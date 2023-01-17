package mx.com.basantader.AgenciaViajeTD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    
    @OneToOne(mappedBy="ciudad",cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
    private HotelEntity hotel;

    @OneToMany(mappedBy = "origen", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VuelosEntity> vuelosOrigen;

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VuelosEntity> vuelosDestino;
}

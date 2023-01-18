package mx.com.basantader.AgenciaViajeTD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AEROLINEAS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AerolineaEntity {
    @Id
    @SequenceGenerator(name = "aerolinea", sequenceName = "aeroliena_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "aeroliena")
    private Long idAerolinea;

    @Column(name = "NOMBRE_AEROLINEA", nullable = false)
    private String nombreAeroliena;

    @OneToMany(mappedBy = "aerolinea", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VuelosEntity> vuelosAerolinea;
}

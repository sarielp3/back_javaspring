package mx.com.basantader.AgenciaViajeTD.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "VUELOS")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VueloEntity {
    @Id
    @SequenceGenerator(name = "vuelo", sequenceName = "vuelo_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "vuelo")
    private Long idVuelo;

    @ManyToOne
    @JoinColumn(name = "ORIGEN")
    private CiudadEntity origen;

    @ManyToOne
    @JoinColumn(name = "DESTINO")
    private CiudadEntity destino;

    @ManyToOne
    @JoinColumn(name = "ID_AEROLINEA")
    private AerolineaEntity aerolinea;


    @Column(name = "ESTATUS", nullable = false)
    private Long estatus;

    @Column(name = "HORA_SALIDA", nullable = false)
    private Time horaSalida;

    @Column(name = "HORA_LLEGADA", nullable = false)
    private Time horaLlegada;

    @Column(name = "CODIGO_VUELO", nullable = false)
    private String codigoVuelo;

    @Column(name = "COSTO", nullable = false)
    private Float costo;
    
    @OneToMany(mappedBy = "vuelo", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ReservaEntity> reservas;
}

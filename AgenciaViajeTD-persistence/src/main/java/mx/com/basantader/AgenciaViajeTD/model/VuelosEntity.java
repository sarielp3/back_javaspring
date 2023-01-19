package mx.com.basantader.AgenciaViajeTD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "VUELOS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VuelosEntity {
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
    private String estatus;

    @Column(name = "HORA_SALIDA", nullable = false)
    private Time horaSalida;

    @Column(name = "HORA_LLEGADA", nullable = false)
    private Time horaLlegada;

    @Column(name = "CODIGO_VUELO", nullable = false)
    private String codigoVuelo;

    @Column(name = "COSTO", nullable = false)
    private Float costo;
    
    @OneToMany(mappedBy = "vuelo", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReservasEntity> reservas;
}

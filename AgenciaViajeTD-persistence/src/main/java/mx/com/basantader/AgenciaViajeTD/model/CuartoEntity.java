package mx.com.basantader.AgenciaViajeTD.model;

import lombok.*;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CUARTOS")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CuartoEntity {

    @Id
    @SequenceGenerator(name = "sequ", sequenceName = "CUARTOSALTA_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequ")
    private Long idCuarto;

    @Column(name = "NOMBRE_CUARTO", nullable = false)
    private String nombreCuarto;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "NUMERO_PERSONAS", nullable = false)
    private Long numeroPersonas;

    @Column(name = "CODIGO_CUARTO", nullable = false)
    private String codigoCuartos;

    @Column(name = "COSTO_NOCHE", nullable = false)
    private Float costoNoche;

    @Column(name = "TIPO_CUARTO", nullable = false)
    private String tipoCuarto;
    @Column(name = "STATUS", nullable = true)
    private Byte status;

    @ManyToOne
    @JoinColumn(name = "ID_HOTEL")
    private HotelEntity hotel;

    @OneToMany(mappedBy = "cuarto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReservaEntity> reservasCuartos;

}

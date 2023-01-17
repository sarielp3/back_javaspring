package mx.com.basantader.AgenciaViajeTD.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CUARTOS")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuartosEntity {

    @Id
    @SequenceGenerator(name = "sequ", sequenceName = "application_seq", initialValue = 1, allocationSize = 1)
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

    //@ManyToOne
    //@JoinColumn(name = "ID_HOTEL")
    //private HotelEntity hotel;

}

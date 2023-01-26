package mx.com.basantader.AgenciaViajeTD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AltaVueloDto {
    private Long idVuelo;

    private Long origen;

    private Long destino;

    private Long aerolinea;

    private Integer estatus;

    private Time horaSalida;

    private Time horaLlegada;

    private String codigoVuelo;

    private Float costo;
}

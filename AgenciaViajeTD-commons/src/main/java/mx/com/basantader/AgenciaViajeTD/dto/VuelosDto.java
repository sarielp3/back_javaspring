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
public class VuelosDto {

    private Long idVuelo;

    private CiudadDto origen;

    private CiudadDto destino;

    private AerolineaDto aerolinea;

    private String estatus;

    private Time horaSalida;

    private Time horaLlegada;

    private String codigoVuelo;

    private Float costo;

}

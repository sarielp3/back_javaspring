package mx.com.basantader.AgenciaViajeTD.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AltaVueloDto implements Serializable{
	
	
    private Long idVuelo;
    
    @NotNull(message = "La ciudad de origen no pueder ser nula")
    private Long origen;

    @NotNull(message = "La ciudad de destino no pueder ser nula")
    private Long destino;

    @NotNull(message = "La aerolinea no pueder ser nula")
    private Long aerolinea;

    @NotNull(message = "La hora de salida no pueder ser nula")
    private Time horaSalida;

    @NotNull(message = "La hora de llegada no pueder ser nula")
    private Time horaLlegada;

    @NotNull(message = "El codigo del vuelo no pueder ser nulo")
    @NotBlank(message = "El codigo del vuelo no puede contener solo espacios en banco")
    private String codigoVuelo;

    @NotNull(message = "El costo no pueder ser nula")
    private Float costo;
}

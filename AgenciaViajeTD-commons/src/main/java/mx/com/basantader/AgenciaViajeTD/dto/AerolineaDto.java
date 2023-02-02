package mx.com.basantader.AgenciaViajeTD.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AerolineaDto implements Serializable{

    private Long idAerolinea;

    private String nombreAerolinea;

}

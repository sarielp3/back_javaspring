package mx.com.basantader.AgenciaViajeTD.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuartoDto {

  private Long idCuarto;
  private String nombreCuarto;
  private String descripcion;
  private Long numeroPersonas;
  private String codigoCuartos;
  private Float costoNoche;
  private String tipoCuarto;

  private Long idHotel;


}

package mx.com.basantader.AgenciaViajeTD.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
  
  @NotNull(message = "El nombre del cuarto no puede ser nulo")
  private String nombreCuarto;
  
  @NotNull(message = "La descripcion del cuarton no puede ser nula")
  private String descripcion;
  
  @NotNull(message = "El numero de personas no puede ser nulo")
  @Min(1)
  private Long numeroPersonas;
  
  @NotNull(message = "El codigo del cuarto no puede ser nulo")
  private String codigoCuartos;
  
  @NotNull(message = "El costo de cuarto no puede ser nulo")
  private Float costoNoche;
  
  @NotNull(message = "El tipo de cuarto no puede ser nulo")
  private String tipoCuarto;

  @NotNull(message = "El estatus no puede ser nulo")
  @Min(0)
  @Max(1)
  private Byte status;

  private Long idHotel;


}

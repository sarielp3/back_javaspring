package mx.com.basantader.AgenciaViajeTD.dto;


import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuartoDto implements Serializable{

  private Long idCuarto;
  
  @Size(min = 1, max = 40, message = "El nombre solo puede contener entre 1 y 40 caracteres")
  @NotNull(message = "El nombre del cuarto no puede ser nulo")
  private String nombreCuarto;
  
  @Size(min = 1, max = 300, message = "La descripción solo puede contener entre 1 y 300 caracteres")
  @NotNull(message = "La descripción del cuarto no puede ser nula")
  private String descripcion;
  
  @NotNull(message = "El número de personas no puede ser nulo")
  @Min(value = 1, message = "El número mínimo de personas por habitación es de 1")
  @Max(value = 10, message = "El número máximo de personas por habitación es de 10")
  private Long numeroPersonas;
  
  @NotNull(message = "El código del cuarto no puede ser nulo")
  @Size(min = 10  ,max = 10,message = "El código del cuarto debe contener 10 caracteres")
  private String codigoCuartos;
  
  @NotNull(message = "El costo de cuarto no puede ser nulo")
  @Min(value = 1, message = "El costo mínimo del cuarto es 1")
  private Float costoNoche;
  
  @NotNull(message = "El tipo de cuarto no puede ser nulo")
  @Size(min = 1, max = 20,message = "El tipo de cuarto debe contener entre 1 y 20 caracteres")
  private String tipoCuarto;

  @NotNull(message = "El estatus no puede ser nulo")
  @Min(0)
  @Max(1)
  private Byte status;

  private Long idHotel;


}

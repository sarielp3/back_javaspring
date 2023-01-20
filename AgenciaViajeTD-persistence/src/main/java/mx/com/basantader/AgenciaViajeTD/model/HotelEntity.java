package mx.com.basantader.AgenciaViajeTD.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "HOTELES")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity implements Serializable {
	@Id
    @SequenceGenerator(name = "hotel", sequenceName = "hotel_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "hotel")
    private Long id_hotel;
	
	@ManyToOne
	@JoinColumn(name = "ID_CIUDAD")
	private CiudadEntity ciudad;
	
	@Column(name="NOMBRE_HOTEL",nullable=false)
	private String nombre_hotel;
	
	@Column(name="CODIGO_HOTEL",nullable=false)
	private String codigo_hotel;
	
	@Column(name="DIRECCION",nullable=false)
	private String direccion;
	
	@Column(name="ESTATUS",nullable=false)
	private String estatus;
	
	@Column(name="LOGO",nullable=false)
	private Blob logo;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ReservaEntity> reservas;
}
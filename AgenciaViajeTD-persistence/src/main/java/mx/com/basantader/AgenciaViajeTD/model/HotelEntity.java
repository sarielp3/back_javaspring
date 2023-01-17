package mx.com.basantader.AgenciaViajeTD.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HOTELES")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelEntity implements Serializable {
	@Id
    @SequenceGenerator(name = "hotel", sequenceName = "hotel_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "hotel")
    private Long id_Hotel;
	
	@OneToOne
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
	
}

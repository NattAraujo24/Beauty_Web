package mx.edu.utez.beautyPalaceApi.models.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.product.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "oferta")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false)
    private Long descuento;

    /*@OneToOne
    @JoinColumn(name = "nombre_producto", referencedColumnName = "nombre", unique = true)
    private Product product;*/
    @Column(nullable = false)
    private String product;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false)
    private String codigoDescuento;

    @Column(nullable = false)
    //private Date fechaInicio;
    private String fechaInicio;

}

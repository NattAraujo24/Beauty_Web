package mx.edu.utez.beautyPalaceApi.controllers.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.product.Product;
import org.springframework.core.io.ByteArrayResource;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;

    private String nombre;
    private String descripcion;
    private double precio;



    public Product getProduct(){
        return new Product(
                getId(),
                getNombre(),
                getDescripcion(),
                getPrecio()

        );
    }
}

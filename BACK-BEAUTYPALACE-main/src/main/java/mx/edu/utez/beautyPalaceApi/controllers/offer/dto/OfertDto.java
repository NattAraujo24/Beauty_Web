package mx.edu.utez.beautyPalaceApi.controllers.offer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.offer.Oferta;
import mx.edu.utez.beautyPalaceApi.models.product.Product;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OfertDto {

    private Long id;

    private String nombre;

    private Long descuento;

    //private Product product;
    private String product;

    private String descripcion;

    private String codigoDescuento;

    private String fechaInicio;

    public Oferta getOferta(){
        return new Oferta(
                getId(),
                getNombre(),
                getDescuento(),
                getProduct(),
                getDescripcion(),
                getCodigoDescuento(),
                getFechaInicio()
        );
    }
}

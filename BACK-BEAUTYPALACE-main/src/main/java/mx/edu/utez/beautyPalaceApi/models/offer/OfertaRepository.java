package mx.edu.utez.beautyPalaceApi.models.offer;

import mx.edu.utez.beautyPalaceApi.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    boolean existsByNombre(String nombre);

    @Modifying
    @Query(
            value = "UPDATE oferta SET nombre = :nombre, descuento = :descuento, product = :product, descripcion = :descripcion, fecha_inicio = :fechaInicio WHERE id = :id",
            nativeQuery = true
    )
    int updateOferta(
            @Param("nombre") String nombre,
            @Param("descuento") Long descuento,
            @Param("product") String product,
            @Param("descripcion") String descripcion,
            @Param("fechaInicio") String fechaInicio,
            @Param("id") Long id
    );

    @Modifying
    @Query(
            value = "DELETE FROM oferta WHERE id = :id",
            nativeQuery = true
    )
    void deleteById(@Param("id") Long id);
}

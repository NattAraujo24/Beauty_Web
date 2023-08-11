package mx.edu.utez.beautyPalaceApi.models.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Blob;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query(
            value = "UPDATE producto SET nombre=:nombre, precio=:precio, descripcion=:descripcion WHERE id=:id",
            nativeQuery = true
    )
    int updateProduct(
            @Param("nombre") String nombre,
            @Param("precio") double precio,
            @Param("descripcion") String descripcion,
            @Param("id") Long id
    );

    @Modifying
    @Query(
            value = "DELETE FROM producto WHERE id=:id",
            nativeQuery = true
    )
    void deleteById(@Param("id") Long id);
}

package mx.edu.utez.beautyPalaceApi.services.product;

import mx.edu.utez.beautyPalaceApi.models.product.Product;
import mx.edu.utez.beautyPalaceApi.models.product.ProductRepository;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Product>> getAllProducts() {
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public Response<Product> getOneProduct(Long id) {
        if (this.repository.findById(id).isPresent()) {
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "ok"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontro el Producto"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Product> insertOne(Product product) {
        return new Response<>(
                this.repository.saveAndFlush(product),
                false,
                200,
                "Producto agregado correctamente"
        );
    }


    /*@Transactional(rollbackFor = SQLException.class)
    public Response<Product> insertOne(Product product) {
        if (!this.repository.existsById(product.getId())) {
            return new Response<>(
                    this.repository.saveAndFlush(product),
                    false,
                    200,
                    "ok"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se pudo agregar el producto"
        );
    }*/

    @Transactional(rollbackFor = SQLException.class)
    public Response<Product> updateProduct(Long id, Product updatedProduct) {
        if (repository.existsById(id)) {
            Product existingProduct = repository.findById(id).get();
            existingProduct.setNombre(updatedProduct.getNombre());
            existingProduct.setPrecio(updatedProduct.getPrecio());
            existingProduct.setDescripcion(updatedProduct.getDescripcion());
            return new Response<>(
                    repository.save(existingProduct),
                    false,
                    200,
                    "Producto actualizado correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontró el Producto"
        );
    }


    /*@Transactional(rollbackFor = SQLException.class)
    public Response<Product> updateProducto(Product product) {
        if (this.repository.existsById(product.getId())) {
            if (this.repository.updateProduct(
                    product.getNombre(),
                    product.getPrecio(),
                    product.getDescripcion(),
                    product.getId()
            ) == 1) {
                return new Response<>(
                        product,
                        false,
                        200,
                        "El producto se actualizo correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salio mal al actualizar el producto"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "El producto no se encontro"
            );
        }
    }*/

    @Transactional(rollbackFor = SQLException.class)
    public Response<Product> deleteProductoById(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "Producto eliminado correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontró el producto"
        );
    }

}

package mx.edu.utez.beautyPalaceApi.controllers.product;

import mx.edu.utez.beautyPalaceApi.controllers.product.dto.ProductDto;
import mx.edu.utez.beautyPalaceApi.models.product.Product;
import mx.edu.utez.beautyPalaceApi.services.product.ProductService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/product")
@CrossOrigin({"*"})
public class ProductController {

    @Autowired
    private ProductService service;


    // Metodo para traer todos los registros,
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/product/
    @GetMapping("/")
    public ResponseEntity<Response<List<Product>>> getAllProduct(){
        return new ResponseEntity<>(
                this.service.getAllProducts(),
                HttpStatus.OK
        );
    }

    // Metodo para traer un porducto segun su ID,
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/product/1 <----- Este numero es un ejemplo
    @GetMapping("/{id}")
    public ResponseEntity<Response<Product>> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOneProduct(id),
                HttpStatus.OK
        );
    }

    //Metodo para insertar un prodcuto.
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/product/
    @PostMapping("/")
    public ResponseEntity<Response<Product>> insertProduct(@Valid @RequestBody ProductDto productDto){
        return new ResponseEntity<>(
                this.service.insertOne(productDto.getProduct()),
                HttpStatus.CREATED
        );
    }

    //Metodo para actualizar una cita agendada.
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/product/update/

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<Product>> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto productDto) {
        Response<Product> response = service.updateProduct(id, productDto.getProduct());

        if (response.isError()) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@PutMapping("/update/{id}")
    public ResponseEntity<Response<Product>> updateProducto(@Valid @RequestBody ProductDto productDto){
        return new ResponseEntity<>(
                this.service.updateProduct(productDto.getProduct()),
                HttpStatus.OK
        );
    }*/

    //Metodo para eliminar una cita ya agendada.
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/product/5  <---- ESTE NUMERO ES UN EJEMPLO
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Product>> deleteProducto(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                this.service.deleteProductoById(id),
                HttpStatus.OK
        );
    }

}



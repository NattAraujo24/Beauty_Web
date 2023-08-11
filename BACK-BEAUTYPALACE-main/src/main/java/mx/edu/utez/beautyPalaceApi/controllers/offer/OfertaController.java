package mx.edu.utez.beautyPalaceApi.controllers.offer;

import mx.edu.utez.beautyPalaceApi.controllers.offer.dto.OfertDto;
import mx.edu.utez.beautyPalaceApi.models.offer.Oferta;
import mx.edu.utez.beautyPalaceApi.services.offer.OfertaService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/oferta")
@CrossOrigin({"*"})
public class OfertaController {
    @Autowired
    private OfertaService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Oferta>>> getOfertas(){
        return new ResponseEntity<>(
                this.service.getAllOfertas(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Oferta>> insertOferta(@Valid @RequestBody OfertDto ofertDto){
        return new ResponseEntity<>(
                this.service.insertOne(ofertDto.getOferta()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response<Oferta>> updateOferta(@PathVariable("id") Long id, @RequestBody Oferta updatedOferta) {
        return new ResponseEntity<>(
                service.updateOferta(id, updatedOferta),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Oferta>> deleteOferta(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                service.deleteOfertaById(id),
                HttpStatus.OK
        );
    }

}

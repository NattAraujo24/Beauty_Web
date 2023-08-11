package mx.edu.utez.beautyPalaceApi.controllers.tip;

import mx.edu.utez.beautyPalaceApi.controllers.question.dto.PreguntasDto;
import mx.edu.utez.beautyPalaceApi.controllers.tip.dto.ConsejosDto;
import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;
import mx.edu.utez.beautyPalaceApi.models.tip.Consejos;
import mx.edu.utez.beautyPalaceApi.services.tip.ConsejosService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/consejos")
@CrossOrigin({"*"})
public class ConsejosController {

    @Autowired
    private ConsejosService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Consejos>>> getAllConsejos(){
        return new ResponseEntity<>(
                this.service.getAllConsejos(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Consejos>> insertConsejo(@Valid @RequestBody ConsejosDto consejosDto){
        return new ResponseEntity<>(
                this.service.insertOne(consejosDto.getConsejos()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Response<Consejos>> updateConsejo(@Valid @RequestBody ConsejosDto consejosDto) {
        return new ResponseEntity<>(
                service.updateConsejos(consejosDto.getConsejos()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteConsejo(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                service.deleteConsejoById(id),
                HttpStatus.OK
        );
    }
}

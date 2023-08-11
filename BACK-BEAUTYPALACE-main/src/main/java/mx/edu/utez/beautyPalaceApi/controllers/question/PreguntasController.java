package mx.edu.utez.beautyPalaceApi.controllers.question;


import mx.edu.utez.beautyPalaceApi.controllers.question.dto.PreguntasDto;
import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;
import mx.edu.utez.beautyPalaceApi.services.question.PreguntasService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/preguntas")
@CrossOrigin({"*"})
public class PreguntasController {

    @Autowired
    private PreguntasService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Preguntas>>> getAllPreguntas(){
        return new ResponseEntity<>(
                this.service.getAllPreguntas(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Preguntas>> insertPregunta(@Valid @RequestBody PreguntasDto preguntasDto){
        return new ResponseEntity<>(
                this.service.inserOne(preguntasDto.getPreguntas()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/")
    public ResponseEntity<Response<Preguntas>> updatePregunta(@Valid @RequestBody PreguntasDto preguntasDto){
        return new ResponseEntity<>(
                this.service.updatePregunta(preguntasDto.getPreguntas()),
                HttpStatus.OK
        );
    }


    @DeleteMapping("/{id}")
   public ResponseEntity<Response<Preguntas>> deletePregunta(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.deletePreguntaById(id),
                HttpStatus.OK
        );
    }
}

package mx.edu.utez.beautyPalaceApi.controllers.service;

import mx.edu.utez.beautyPalaceApi.controllers.question.dto.PreguntasDto;
import mx.edu.utez.beautyPalaceApi.controllers.service.dto.ServicioDto;
import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;
import mx.edu.utez.beautyPalaceApi.models.service.Servicio;
import mx.edu.utez.beautyPalaceApi.services.service.ServicioService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/servicio")
@CrossOrigin({"*"})
public class ServicioController {

    @Autowired
    private ServicioService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Servicio>>> getAllPuntuacion(){
        return new ResponseEntity<>(
                this.service.getAllPuntuacion(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Servicio>> insertPregunta(@Valid @RequestBody ServicioDto servicioDto){
        return new ResponseEntity<>(
                this.service.inserOne(servicioDto.getServicio()),
                HttpStatus.CREATED
        );
    }

}

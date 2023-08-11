package mx.edu.utez.beautyPalaceApi.controllers.appointment;

import mx.edu.utez.beautyPalaceApi.controllers.appointment.dto.CitaDto;
import mx.edu.utez.beautyPalaceApi.models.appointment.Cita;
import mx.edu.utez.beautyPalaceApi.services.appointment.CitaService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/cita")
@CrossOrigin({"*"})
public class CitaController {
    @Autowired
    private CitaService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Cita>>> getCitas () {
        return new ResponseEntity<>(
                this.service.getCita(),
                HttpStatus.OK
        );
    }

    @GetMapping("/citas/")
    public ResponseEntity<Response<List<Object>>> getAllCitas(){
        return new ResponseEntity<>(
                this.service.getAllCita(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Cita>> insertCita(@Valid @RequestBody CitaDto cita) {
        return new ResponseEntity<>(
                this.service.insertOne(cita.getCitas()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/")
    public ResponseEntity<Response<Cita>> updateCita(@Valid @RequestBody CitaDto citaDto){
        return new ResponseEntity<>(
                this.service.updateCitaById(citaDto.getCitas()),
                HttpStatus.OK
        );
    }
}

package mx.edu.utez.beautyPalaceApi.controllers.agenda;


import mx.edu.utez.beautyPalaceApi.controllers.agenda.dto.AgendaDto;
import mx.edu.utez.beautyPalaceApi.models.agenda.Agenda;
import mx.edu.utez.beautyPalaceApi.services.agenda.AgendaService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-beautypalace/agenda")
@CrossOrigin({"*"})
public class AgendaController {

    @Autowired
    private AgendaService service;



    // Metodo para mandar a traer todos los registros
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/agenda/
    @GetMapping("/")
    public ResponseEntity<Response<List<Agenda>>> getAllAgendas(){
        return new ResponseEntity<>(
                this.service.getAgenda(),
                HttpStatus.OK
        );
    }

    // Metodo para mandar a traer un solo registro dependiendo de su id
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/agenda/3 <---- este numero es un ejemplo
    @GetMapping("/{id}")
    public ResponseEntity<Response<Agenda>> getById(@PathVariable ("id") Long id) {
        return new ResponseEntity<>(
                this.service.getOneAgendaById(id),
                HttpStatus.OK
        );
    }

    // Metodo para manmdar a traer la cita agendad segun el id del usuario
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/agenda/user/3 <------ este numero es un ejemplo

    @GetMapping("/user/{id}/")
    public ResponseEntity<Response<List<Object>>> getAgendaByUserId(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getAgendaByUserId(id),
                HttpStatus.OK
        );
    }

    // Metodo para mostrar la cita segun su id
    // URL DE ESE METODO: http://localhost:8080/api-beautypalace/a       genda/viewcita/3 <---- este numero es un ejemplo
    @GetMapping("/viewcita/{id}")
    public ResponseEntity<Response<List<Agenda>>> getAgendaById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getAgendaById(id),
                HttpStatus.OK
        );
    }

    // Metodo para agendar una cita (DE MOMENTO TIENES QUE PONER EL ID PARA QUE FUNCIONE)
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/agenda/ (PARA PROBARLO EN METODO POST DESDE POSTMAN)
    @PostMapping("/")
    public ResponseEntity<Response<Agenda>> inserAgenda(@Valid @RequestBody AgendaDto agendaDto){
        return new ResponseEntity<>(
                this.service.insertOne(agendaDto.getAgenda()),
                HttpStatus.CREATED
        );
    }


    //Metodo para actualizar una cita agendada.
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/agenda/update/

    @PutMapping("/update/")
    public ResponseEntity<Response<Agenda>> updateAgenda(@Valid @RequestBody AgendaDto agendaDto){
        return new ResponseEntity<>(
                this.service.updateAgenda(agendaDto.getAgenda()),
                HttpStatus.OK
        );
    }

    //Metodo para eliminar una cita ya agendada.
    // URL DE ESTE METODO: http://localhost:8080/api-beautypalace/agenda/5  <---- ESTE NUMERO ES UN EJEMPLO
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteAegnda(@PathVariable ("id") Long id){
        return new ResponseEntity<>(
                this.service.deleteAgendaById(id),
                HttpStatus.OK
        );
    }
}

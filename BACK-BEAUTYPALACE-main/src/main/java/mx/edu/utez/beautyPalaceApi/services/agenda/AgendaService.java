package mx.edu.utez.beautyPalaceApi.services.agenda;

import mx.edu.utez.beautyPalaceApi.models.agenda.Agenda;
import mx.edu.utez.beautyPalaceApi.models.agenda.AgendaRepository;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AgendaService {
    @Autowired
    private AgendaRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Agenda>> getAgenda() {
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<Agenda> getOneAgendaById(Long id) {
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "OK"
            );
    }

    @Transactional(readOnly = true)
    public Response<List<Object>> getAgendaByUserId(Long id){
        return new Response<>(
                this.repository.findAgendaByUserId(id),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<Agenda>> getAgendaById(Long id){
        return new Response<>(
                this.repository.findAgendaById(id),
                false,
                200,
                "Ok"
        );
    }


    public void eliminarCitasPasadas() {
        LocalDate fechaActual = LocalDate.now();
        this.repository.deleteCitasAnteriores(fechaActual);

    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Agenda> insertOne(Agenda agenda) {
        // Verificar si ya existe una cita con el mismo nombre de cita y día
        List<Agenda> existingAgendas = repository.findByNameClientAndName(agenda.getNameClient(), agenda.getName());

        if (existingAgendas.isEmpty()) {
            // No hay citas previas, entonces podemos insertar la nueva cita
            return new Response<>(
                    repository.saveAndFlush(agenda),
                    false,
                    200,
                    "Cita agendada con éxito"
            );
        }

        // Ya existe una cita con los mismos atributos, devolver un error
        return new Response<>(
                null,
                true,
                400,
                "Cita agendada previamente"
        );
    }


    @Transactional(rollbackFor = SQLException.class)
    public Response<Agenda> updateAgenda(Agenda agenda) {
        if (this.repository.existsById(agenda.getId())) {
            if (this.repository.agendaUpdate(
                    agenda.getName(),
                    agenda.getNameClient(),
                    agenda.getTypeOfService(),
                    agenda.getDay(),
                    agenda.getStartTime(),
                    agenda.getTimeEnd(),
                    //agenda.getBranch(),
                    agenda.getId()
            ) == 1) {
                return new Response<>(
                        agenda,
                        false,
                        200,
                        "La cita agendada se actualizo correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salio mal al actualizar la cita agendada"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "La cita no se encontro"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<String> deleteAgendaById(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "La cita se elimino correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "La cita no se encontro"
        );
    }


}

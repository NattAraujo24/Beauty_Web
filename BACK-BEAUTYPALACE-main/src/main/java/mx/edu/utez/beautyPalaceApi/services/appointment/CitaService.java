package mx.edu.utez.beautyPalaceApi.services.appointment;

import mx.edu.utez.beautyPalaceApi.models.appointment.Cita;
import mx.edu.utez.beautyPalaceApi.models.appointment.CitaRepository;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CitaService {
    @Autowired
    private CitaRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Cita>> getCita() {
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<Object>> getAllCita(){
        return new Response<>(
                this.repository.findAllCitas(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Cita> insertOne(Cita cita) {
        return new Response<>(
                this.repository.saveAndFlush(cita),
                false,
                200,
                "La cita se registró correctamente"
        );
    }


    /*@Transactional(rollbackFor = SQLException.class)
    public Response<Cita> insertOne(Cita cita) {
        if (!this.repository.existsById(cita.getId())) {
            return new Response<>(
                    this.repository.saveAndFlush(cita),
                    false,
                    200,
                    "La cita se registro correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Ya se solicito esta cita"
        );
    }*/

    @Transactional(rollbackFor = SQLException.class)
    public Response<Cita> updateCitaById(Cita cita){
        if (this.repository.existsById(cita.getId())){
            if (this.repository.updateCita(
                    cita.getDay(),
                    cita.getHour(),
                    cita.getTypeOfService(),
                    cita.getNameUser(),
                    cita.getId()
            ) == 1){
                return new Response<>(
                        cita,
                        false,
                        200,
                        "Cita actualizada correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "No se pudo actualizar la cita"
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

    //Eliminar cita
    @Transactional(rollbackFor = SQLException.class)
    public Response<Void> deleteCitaById(Long citaId) {
        if (this.repository.existsById(citaId)) {
            this.repository.deleteById(citaId);
            return new Response<>(
                    null,
                    false,
                    200,
                    "Cita eliminada correctamente"
            );
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "La cita no se encontró"
            );
        }
    }

}

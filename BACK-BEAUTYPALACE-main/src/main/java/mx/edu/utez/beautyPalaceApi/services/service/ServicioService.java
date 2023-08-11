package mx.edu.utez.beautyPalaceApi.services.service;

import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;
import mx.edu.utez.beautyPalaceApi.models.service.Servicio;
import mx.edu.utez.beautyPalaceApi.models.service.ServicioRepository;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ServicioService {
    @Autowired
    private ServicioRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Servicio>> getAllPuntuacion(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Servicio> inserOne(Servicio servicio){
        if (!this.repository.existsById(servicio.getId())){
            return new Response<>(
                    this.repository.saveAndFlush(servicio),
                    false,
                    200,
                    "OK"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se pudo agreggar la puntuacion del servicio"
        );
    }
}

package mx.edu.utez.beautyPalaceApi.services.question;

import mx.edu.utez.beautyPalaceApi.models.product.Product;
import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;
import mx.edu.utez.beautyPalaceApi.models.question.PreguntasRepository;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class PreguntasService {

    @Autowired
    private PreguntasRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Preguntas>> getAllPreguntas(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    /*@Transactional(rollbackFor = SQLException.class)
    public Response<Preguntas> inserOne(Preguntas preguntas){
        if (!this.repository.existsById(preguntas.getId())){
            return new Response<>(
                    this.repository.saveAndFlush(preguntas),
                    false,
                    200,
                    "Pregunta agregada correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se pudo agregar la pregunta y respuesta"
        );
    }*/

    @Transactional(rollbackFor = SQLException.class)
    public Response<Preguntas> inserOne(Preguntas preguntas) {
        Preguntas savedPregunta = this.repository.saveAndFlush(preguntas);

        if (savedPregunta != null) {
            return new Response<>(
                    savedPregunta,
                    false,
                    200,
                    "Pregunta agregada correctamente"
            );
        }

        return new Response<>(
                null,
                true,
                400,
                "No se pudo agregar la pregunta y respuesta"
        );
    }


    @Transactional(rollbackFor = SQLException.class)
    public Response<Preguntas> updatePregunta(Preguntas preguntas) {
        if (this.repository.existsById(preguntas.getId())) {
            if (this.repository.updatePregunta(
                    preguntas.getPregunta(),
                    preguntas.getRespuesta(),
                    preguntas.getId()
            ) == 1) {
                return new Response<>(
                        preguntas,
                        false,
                        200,
                        "La pregunta se actualizo correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salio mal al actualizar la pregunta"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "La pregunta no se encontro"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Preguntas> deletePreguntaById(Long id) {
        if (this.repository.existsById(id)) {
            this.repository.deletePreguntasById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "Preunta eliminada correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontro la pregunta"
        );
    }
}

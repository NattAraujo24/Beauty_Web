package mx.edu.utez.beautyPalaceApi.controllers.question.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PreguntasDto {

    private Long id;
    private String pregunta;
    private String respuesta;

    public Preguntas getPreguntas(){
        return new Preguntas(
                getId(),
                getPregunta(),
                getRespuesta()
        );
    }
}

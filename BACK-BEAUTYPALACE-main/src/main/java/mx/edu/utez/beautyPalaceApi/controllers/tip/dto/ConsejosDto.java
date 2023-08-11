package mx.edu.utez.beautyPalaceApi.controllers.tip.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.tip.Consejos;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ConsejosDto {

    private Long id;
    private String titulo;
    private String consejo;

    public Consejos getConsejos(){
        return new Consejos(
                getId(),
                getTitulo(),
                getConsejo()
        );
    }
}

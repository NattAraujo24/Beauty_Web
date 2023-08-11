package mx.edu.utez.beautyPalaceApi.controllers.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.service.Servicio;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ServicioDto {

    private Long id;
    private int estrellas;
    private Long id_cita;
    private String name_cita;

    public Servicio getServicio(){
        return new Servicio(
          getId(),
          getEstrellas(),
          getId_cita(),
          getName_cita()
        );
    }
}

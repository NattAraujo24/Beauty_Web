package mx.edu.utez.beautyPalaceApi.controllers.appointment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.appointment.Cita;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CitaDto {

    private Long id;
    private String day;
    private String hour;
    private String typeOfService;
    private String nameUser;
    //private String branch;

    public Cita getCitas(){
        return new Cita(
                getId(),
                getDay(),
                getHour(),
                getTypeOfService(),
                getNameUser()
                //getBranch()
        );
    }
}

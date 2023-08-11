package mx.edu.utez.beautyPalaceApi.controllers.agenda.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.agenda.Agenda;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgendaDto {
    private Long id;
    private String name;
    private String nameClient;
    private String lastNameClient;
    private String typeOfService;
    private String day;
    private String startTime;
    private String timeEnd;
    //private String branch;

    public Agenda getAgenda(){
        return new Agenda(
                getId(),
                getName(),
                getLastNameClient(),
                getNameClient(),
                getTypeOfService(),
                getDay(),
                getStartTime(),
                getTimeEnd()
                //getBranch()
        );
    }
}

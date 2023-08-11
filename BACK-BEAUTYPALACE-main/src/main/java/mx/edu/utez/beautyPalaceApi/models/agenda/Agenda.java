package mx.edu.utez.beautyPalaceApi.models.agenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "agenda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 100)
    private String lastNameClient;

    @Column(nullable = false, length = 100)
    private String nameClient;

    @Column(nullable = false, length = 100)
    private String typeOfService;

    @Column(nullable = false, length = 100)
    private String day;

    @Column(nullable = false,length = 100)
    private String startTime;

    @Column(nullable = false,length = 100)
    private String timeEnd;

    /*@Column(nullable = false, length = 200)
    private String branch;*/

}

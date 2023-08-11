package mx.edu.utez.beautyPalaceApi.models.appointment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cita")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String day;

    @Column(nullable = false, length = 100)
    private String hour;

    @Column(nullable = false, length = 100)
    private String typeOfService;

    @Column(nullable = false, length = 20)
    private String nameUser;

    /*@Column(nullable = false, length = 150)
    private String branch;*/
}

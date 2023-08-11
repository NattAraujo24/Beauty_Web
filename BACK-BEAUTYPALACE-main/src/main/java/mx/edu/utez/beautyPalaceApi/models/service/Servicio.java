package mx.edu.utez.beautyPalaceApi.models.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "servicio")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int estrellas;

    @Column(nullable = false)
    private Long id_cita;

    @Column(nullable = false, length = 200)
    private String name_cita;

}

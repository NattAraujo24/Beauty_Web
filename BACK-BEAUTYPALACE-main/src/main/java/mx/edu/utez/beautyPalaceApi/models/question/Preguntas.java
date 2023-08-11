package mx.edu.utez.beautyPalaceApi.models.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Preguntas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String pregunta;

    @Column(nullable = false, length = 50)
    private String respuesta;

}

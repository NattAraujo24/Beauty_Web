package mx.edu.utez.beautyPalaceApi.models.tip;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;

@Entity
@Table(name = "consejos")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Consejos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 70)
    private String titulo;

    @Column(nullable = false, length = 250)
    private String consejo;
}

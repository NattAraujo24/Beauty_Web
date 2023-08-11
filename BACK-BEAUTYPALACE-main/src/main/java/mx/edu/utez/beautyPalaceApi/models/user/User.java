package mx.edu.utez.beautyPalaceApi.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = true, length = 50)
    private String lastName;

    @Column(nullable = true, unique = true, length = 50)
    private String email;

    @Column(nullable = true, length = 50)
    private String password;

    @Column(nullable = true, length = 10)
    private String tokenPassword;

    @Column(nullable = false, length = 6)
    private String typeOfUser;


}

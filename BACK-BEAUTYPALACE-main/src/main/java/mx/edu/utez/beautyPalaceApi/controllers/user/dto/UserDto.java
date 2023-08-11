package mx.edu.utez.beautyPalaceApi.controllers.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.beautyPalaceApi.models.user.User;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {


    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private String tokenPassword;

    private String typeOfUser;


    public User getUser() {
        return new User(
                this.getId(),
                this.getName(),
                this.getLastName(),
                this.getEmail(),
                this.getPassword(),
                this.getTokenPassword(),
                this.getTypeOfUser()
        );
    }

    public User getUserClient() {
        return new User(
                this.getId(),
                this.getName(),
                this.getLastName(),
                this.getEmail(),
                this.getPassword(),
                this.getTokenPassword(),
                "CLIENT"
        );
    }

    public User getUserTokenPassword() {
        return new User(
               null,
                null,
                null,
                this.getEmail(),
                this.getPassword(),
                this.getTokenPassword(),
                null
        );
    }



}

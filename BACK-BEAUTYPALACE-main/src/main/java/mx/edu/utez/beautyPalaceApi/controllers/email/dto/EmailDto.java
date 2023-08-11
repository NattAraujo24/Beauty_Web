package mx.edu.utez.beautyPalaceApi.controllers.email.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmailDto {
    private String email;
    private String comments;
}

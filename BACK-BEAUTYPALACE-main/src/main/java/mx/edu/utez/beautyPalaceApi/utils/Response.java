package mx.edu.utez.beautyPalaceApi.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {

    private T data;
    private boolean error;
    private int status;
    private String message;


}

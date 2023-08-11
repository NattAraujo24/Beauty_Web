package mx.edu.utez.beautyPalaceApi.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CodigoGenerator {

    public String generarCodigo(){
        UUID uuid = UUID.randomUUID();
        String codigo = uuid.toString().replaceAll("-","").substring(0, 8);
        return codigo;
    }
}

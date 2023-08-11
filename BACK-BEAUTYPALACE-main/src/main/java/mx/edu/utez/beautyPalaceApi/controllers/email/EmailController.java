package mx.edu.utez.beautyPalaceApi.controllers.email;

import mx.edu.utez.beautyPalaceApi.controllers.email.dto.EmailDto;
import mx.edu.utez.beautyPalaceApi.utils.EmailService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-beautypalace/email")
@CrossOrigin(origins = {"*"})
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/")
    public ResponseEntity<Response<Object>> sendMail(
            @Valid @RequestBody EmailDto email
    ) {
        if (this.service.sendMail(email))
            return new ResponseEntity<>(
                    new Response<>(null, false, 200, "OK"),
                    HttpStatus.OK
            );
        return new ResponseEntity<>(
                new Response<>(null, true, 400, "BAD_REQUEST"),
                HttpStatus.BAD_REQUEST
        );
    }
}

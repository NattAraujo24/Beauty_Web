package mx.edu.utez.beautyPalaceApi.controllers.user;

import mx.edu.utez.beautyPalaceApi.controllers.email.dto.EmailDto;
import mx.edu.utez.beautyPalaceApi.controllers.user.dto.UserDto;
import mx.edu.utez.beautyPalaceApi.models.user.User;
import mx.edu.utez.beautyPalaceApi.services.user.UserService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api-beautypalace/user")
@CrossOrigin({"*"})
public class UserController {


    @Autowired
    private UserService service;

    @GetMapping("/clients/")
    public ResponseEntity<Response<List<User>>> getAllClients (){
        return new ResponseEntity<>(
                this.service.getClients(),
                HttpStatus.OK
        );
    }

    @GetMapping("/admins/")
    public ResponseEntity<Response<List<User>>> getAllAdmins (){
        return new ResponseEntity<>(
                this.service.getAdmins(),
                HttpStatus.OK
        );
    }

    @GetMapping ("/credentials/{email}&{password}")
    public ResponseEntity<Response<User>> getUserByEmailAndPassword(@PathVariable ("email") String email, @PathVariable ("password") String password){
        return new ResponseEntity<>(
                this.service.getUserByEmailAndPassword(email, password),
                HttpStatus.OK
        );
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Response<User>> getUserById(@PathVariable ("id") Long id){
        return new ResponseEntity<>(
                this.service.getUserById(id),
                HttpStatus.OK
        );
    }

    @PostMapping ("/")
    public ResponseEntity<Response<User>> insertUser(@Valid @RequestBody  UserDto user){
        return new ResponseEntity<>(
                this.service.insertOne(user.getUserClient()),
                HttpStatus.CREATED
        );
    }


    @PutMapping ("/data/")
    public ResponseEntity<Response<User>> updateOneData(@Valid @RequestBody  UserDto user){
        return new ResponseEntity<>(
                this.service.updateOneData(user.getUserClient()),
                HttpStatus.OK
        );
    }

    @PutMapping ("/token/")
    public ResponseEntity<Response<Object>> updateOneTokenPassword( @Valid @RequestBody   EmailDto data){
        System.out.println(data.getEmail());
        return new ResponseEntity<>(
                this.service.updateOneTokenPassword(data.getEmail()),
                HttpStatus.OK
        );

    }

    @PutMapping ("/tokenPassword/")
    public ResponseEntity<Response<Object>> updateOnePasswordWithToken (@Valid @RequestBody  UserDto user){
        System.out.println(user.getTokenPassword() + " " + user.getPassword() + " " + user.getEmail());
        return new ResponseEntity<>(
                this.service.updateOnePasswordWithToken(user.getPassword(),user.getTokenPassword(),user.getEmail()),
                HttpStatus.OK
        );
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<Response<Object>> deleteOne(@PathVariable ("id") Long id){
        return new ResponseEntity<>(
                this.service.deleteOne(id),
                HttpStatus.OK
        );
    }

}



package mx.edu.utez.beautyPalaceApi.services.user;

import mx.edu.utez.beautyPalaceApi.controllers.email.dto.EmailDto;
import mx.edu.utez.beautyPalaceApi.models.user.User;
import mx.edu.utez.beautyPalaceApi.models.user.UserRepository;
import mx.edu.utez.beautyPalaceApi.utils.EmailService;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EmailService emailService;

    @Transactional(readOnly = true)
    public Response<List<User>> getClients(){
        return new Response<>(
                this.repository.findAllClients(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<List<User>> getAdmins() {
        return new Response<>(
                this.repository.findAllAdmins(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<User> getUserByEmailAndPassword(String email, String password) {
        if (!this.repository.findByEmailAndPassword(email, password).isEmpty()) {
            return new Response<>(
                    this.repository.findByEmailAndPassword(email, password).get(0),
                    false,
                    200,
                    "OK"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "El registro no se encontró"
        );
    }

    @Transactional(readOnly = true)
    public Response<User> getUserById(Long id) {
        if (this.repository.findById(id).isPresent()) {
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "OK"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "El registro no se encontró"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<User> insertOne(User user) {
        if (!this.repository.existsByEmail(user.getEmail())){
            return new Response<>(
                    this.repository.saveAndFlush(user),
                    false,
                    200,
                    "El registro se insertó correctamente"
            );
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "Ya existe un registro con el correo ingresado"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<User> updateOneData(User user) {
        if(this.repository.existsById(user.getId())){
            if (this.repository.userUpdateData(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getId()
            ) == 1) {
                return new Response<>(
                        user,
                        false,
                        200,
                        "El registro se actualizó correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salió mal al actualizar el registro"
                );
            }

        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "El registro no se encontró"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Object> updateOneTokenPassword(String email){
        if(this.repository.existsByEmail(email) && !this.repository.findPasswordByEmail(email).equals("null")){
            String newToken = createToken();
            ArrayList<Object> lista = new ArrayList<>();

            if ( (this.repository.userUpdateTokenPassword(
                    newToken,
                    email
            )) == 1) {
                lista.add("El token se actualizó correctamente");
            } else {
                lista.add("Algo salió mal al actualizar el token");
            }

            if (this.emailService.sendMail(new EmailDto(email,newToken))) {
                lista.add("El correo se envió correctamente");
            } else {
                lista.add("Algo salió mal al enviar el correo");
            }

            return new Response<>(
                    lista.toArray(),
                    false,
                    200,
                    "OK"
            );
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "El registro no se encontró"
            );
        }
    }



    @Transactional(rollbackFor = SQLException.class)
    public Response<Object> updateOnePasswordWithToken(String password,String token, String email){
        if(this.repository.existsByEmail(email) && !this.repository.findPasswordByEmail(email).equals("null")){
            if(this.repository.findTokenPasswordByEmail(email).equals(token)){

                ArrayList<Object> lista = new ArrayList<>();

                if ( (this.repository.userUpdatePasswordByEmail(
                        password,
                        email
                )) == 1) {
                    lista.add("Contraseña actualizada correctamente");

                    if (this.repository.userUpdateTokenPassword(null, email ) == 1) {
                        lista.add("Token actualizado correctamente");
                    } else {
                        lista.add("Algo salió mal al actualizar el token");
                    }

                } else {
                    lista.add("Algo salió mal al actualizar la contraseña");
                }

                return new Response<>(
                        lista.toArray(),
                        false,
                        200,
                        "El registro se actualizó correctamente"
                );

            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "El token no coincide"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "El registro no se encontró"
            );
        }
    }




    @Transactional(rollbackFor = SQLException.class)
    public Response<Object> deleteOne(Long id) {
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return new Response<>(
                    true,
                    false,
                    200,
                    "El registro se eliminó correctamente"
            );
        } else {
            return new Response<>(
                    false,
                    true,
                    400,
                    "El registro no se encontró"
            );
        }
    }


    public String createToken() {
        String token = "";
        String[] caracteres = {"0","1","2","3","4","5","6","7","8","9",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n",
                "o","p","q","r","s","t","u","v","w","x","y","z","A",
                "B","C","D","E","F","G","H","I","J","K","L","M","N",
                "O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        for (int i = 0; i < 10; i++) {
            token += caracteres[(int) (Math.random() * 60)];
        }
        return token;
    }



}

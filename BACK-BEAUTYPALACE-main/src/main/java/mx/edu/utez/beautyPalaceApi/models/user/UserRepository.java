package mx.edu.utez.beautyPalaceApi.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            value = "SELECT * FROM user WHERE BINARY email = :email AND BINARY password = :password",
            nativeQuery = true
    )
    List<User> findByEmailAndPassword(String email, String password);

    @Query(
            value = "SELECT * FROM user WHERE type_of_user = \"client\"",
            nativeQuery = true
    )
    List<User> findAllClients();

    @Query(
            value = "SELECT * FROM user WHERE type_of_user = \"admin\"",
            nativeQuery = true
    )
    List<User> findAllAdmins();

    boolean existsByEmail(String email);


    @Modifying
    @Query(
            value = "UPDATE user SET name = :name, last_name = :lastName, email = :email, password = :password WHERE id = :id",
            nativeQuery = true
    )

    int userUpdateData(String name, String lastName, String email, String password, Long id);



    @Modifying
    @Query(
            value = "UPDATE user SET token_password = :tokenPassword WHERE email = :email",
            nativeQuery = true
    )

    int userUpdateTokenPassword(String tokenPassword, String email);


    @Query(
            value = "SELECT token_password FROM user WHERE email = :email",
            nativeQuery = true
    )

    String findTokenPasswordByEmail(String email);

    @Modifying
    @Query(
            value = "UPDATE user SET password = :password WHERE email = :email",
            nativeQuery = true
    )

    int userUpdatePasswordByEmail(String password, String email);


    @Query(
            value = "SELECT password FROM user WHERE email = :email",
            nativeQuery = true
    )

    String findPasswordByEmail(String email);


}

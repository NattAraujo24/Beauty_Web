package mx.edu.utez.beautyPalaceApi.models.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query(
            value = "SELECT cita.id , cita.day, cita.hour , cita.type_Of_Service , user.name , user.last_name FROM cita  " +
                    "INNER JOIN user ON cita.name_user = user.name WHERE user.type_of_user = 'CLIENT';",
            nativeQuery = true
    )
    List<Object> findAllCitas();

    @Modifying
    @Query(
            value = "UPDATE cita SET day = :day, hour = :hour, type_of_service  = :typeOfService , " +
                    "name_user = :nameUser WHERE id = :id",
            nativeQuery = true
    )
    int updateCita(
            @Param("day") String day,
            @Param("hour") String hour,
            @Param("typeOfService") String typeOfService,
            @Param("nameUser") String nameUser,
            @Param("id") Long id
    );

}

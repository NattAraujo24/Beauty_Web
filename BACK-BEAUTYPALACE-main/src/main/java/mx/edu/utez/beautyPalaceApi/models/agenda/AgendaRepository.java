package mx.edu.utez.beautyPalaceApi.models.agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    List<Agenda> findByNameClientAndName(@Param("nameClient") String nameClient, @Param("nameCita") String nameCita);

    @Modifying
    @Query(
            value = "UPDATE agenda SET name = :name, name_client = :nameClient, type_of_service = :typeOfService, day = :day, start_time = :startTime, time_end = :timeEnd WHERE id = :id",
            nativeQuery = true
    )
    int agendaUpdate(
            @Param("name") String name,
            @Param("nameClient") String nameClient,
            @Param("typeOfService") String typeOfService,
            @Param("day") String day,
            @Param("startTime") String startTime,
            @Param("timeEnd") String timeEnd,
            @Param("id") Long id
    );

    @Query(
            value = "SELECT agenda.* FROM agenda JOIN user ON user.name = agenda.name_client WHERE user.id = :id",
            nativeQuery = true
    )
    List<Object> findAgendaByUserId(@Param("id") Long id);

    @Query(
            value = "SELECT * FROM agenda WHERE agenda.id = :id",
            nativeQuery = true
    )
    List<Agenda> findAgendaById(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Agenda a WHERE a.day < :day")
    void deleteCitasAnteriores(@Param("day") LocalDate day);
}

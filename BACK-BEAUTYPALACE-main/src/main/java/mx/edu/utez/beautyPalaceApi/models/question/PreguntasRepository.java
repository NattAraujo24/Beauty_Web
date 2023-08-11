package mx.edu.utez.beautyPalaceApi.models.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntasRepository extends JpaRepository<Preguntas, Long> {

    @Modifying
    @Query(
            value = "UPDATE preguntas SET pregunta = :pregunta, respuesta = :respuesta WHERE id = :id",
            nativeQuery = true
    )
    int updatePregunta(
            @Param("pregunta") String pregunta,
            @Param("respuesta") String respuesta,
            @Param("id") Long id
    );


    @Modifying
    @Query(
            value = "DELETE FROM preguntas WHERE id = :id",
            nativeQuery = true
    )
    void deletePreguntasById(@Param("id") Long id);

}

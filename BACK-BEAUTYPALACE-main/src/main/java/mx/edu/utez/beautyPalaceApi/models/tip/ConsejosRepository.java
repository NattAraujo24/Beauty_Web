package mx.edu.utez.beautyPalaceApi.models.tip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsejosRepository extends JpaRepository<Consejos, Long> {
    @Modifying
    @Query(
            value = "UPDATE consejos SET titulo = :titulo, consejo = :consejo WHERE id = :id",
            nativeQuery = true
    )
    int updateConsejo(
            @Param("titulo") String titulo,
            @Param("consejo") String consejo,
            @Param("id") Long id);

    @Modifying
    @Query(
            value = "DELETE FROM consejos WHERE id = :id",
            nativeQuery = true
    )
    void deleteConsejosById(@Param("id") Long id);
}

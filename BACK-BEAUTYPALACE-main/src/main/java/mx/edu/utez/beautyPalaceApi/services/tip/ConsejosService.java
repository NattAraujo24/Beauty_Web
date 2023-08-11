package mx.edu.utez.beautyPalaceApi.services.tip;

import mx.edu.utez.beautyPalaceApi.models.question.Preguntas;
import mx.edu.utez.beautyPalaceApi.models.tip.Consejos;
import mx.edu.utez.beautyPalaceApi.models.tip.ConsejosRepository;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class ConsejosService {
    @Autowired
    private ConsejosRepository repository;


    @Transactional(readOnly = true)
    public Response<List<Consejos>> getAllConsejos(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Consejos> insertOne(Consejos consejos){

            return new Response<>(
                    this.repository.saveAndFlush(consejos),
                    false,
                    200,
                    "Consejo agregado correctamente"
            );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Consejos> updateConsejos(Consejos consejos) {
        if (repository.existsById(consejos.getId())) {
            int rowsUpdated = repository.updateConsejo(
                    consejos.getTitulo(),
                    consejos.getConsejo(),
                    consejos.getId()
            );
            if (rowsUpdated == 1) {
                return new Response<>(
                        consejos,
                        false,
                        200,
                        "El consejo se actualizó correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salió mal al actualizar el consejo"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "El consejo no se encontró"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<String> deleteConsejoById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteConsejosById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "El consejo se eliminó correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontró el consejo"
        );
    }
}

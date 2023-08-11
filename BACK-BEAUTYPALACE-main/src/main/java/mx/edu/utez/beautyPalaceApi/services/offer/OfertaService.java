package mx.edu.utez.beautyPalaceApi.services.offer;

import mx.edu.utez.beautyPalaceApi.models.offer.Oferta;
import mx.edu.utez.beautyPalaceApi.models.offer.OfertaRepository;
import mx.edu.utez.beautyPalaceApi.models.product.Product;
import mx.edu.utez.beautyPalaceApi.utils.CodigoGenerator;
import mx.edu.utez.beautyPalaceApi.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class OfertaService {

    @Autowired
    private OfertaRepository repository;

    @Autowired
    private CodigoGenerator generator;


    @Transactional(readOnly = true)
    public Response<List<Oferta>> getAllOfertas() {
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public Response<Oferta> getOneOferta(Long id) {
        if (this.repository.findById(id).isPresent()) {
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "ok"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontro la Oferta"
        );
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Oferta> insertOne(Oferta oferta) {
        return new Response<>(
                this.repository.saveAndFlush(oferta),
                false,
                200,
                "Oferta agregado correctamente"
        );
    }
    /*@Transactional(rollbackFor = SQLException.class)
    public Response<Oferta> insertOne(Oferta oferta) {
        if (!this.repository.existsByNombre(oferta.getNombre())) {
            if (oferta.getCodigoDescuento() == null || oferta.getCodigoDescuento().isEmpty()) {
                oferta.setCodigoDescuento(generator.generarCodigo());
                return new Response<>(
                        this.repository.saveAndFlush(oferta),
                        false,
                        200,
                        "Oferta agregada correctamente"
                );
            }
        }
        return new Response<>(
                null,
                true,
                400,
                "No se pudo agregar la oferta"
        );
    }*/

    @Transactional(rollbackFor = SQLException.class)
    public Response<Oferta> updateOferta(Long id, Oferta updatedOferta) {
        if (repository.existsById(id)) {
            Oferta existingOferta = repository.findById(id).orElse(null);
            if (existingOferta != null) {
                existingOferta.setNombre(updatedOferta.getNombre());
                existingOferta.setDescuento(updatedOferta.getDescuento());
                existingOferta.setProduct(updatedOferta.getProduct());
                existingOferta.setDescripcion(updatedOferta.getDescripcion());
                existingOferta.setFechaInicio(updatedOferta.getFechaInicio());

                repository.save(existingOferta);

                return new Response<>(
                        existingOferta,
                        false,
                        200,
                        "La oferta se actualizó correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salió mal al actualizar la oferta"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "La oferta no se encontró"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Oferta> deleteOfertaById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "Oferta eliminada correctamente"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontró la oferta"
        );
    }


    /*@Transactional(rollbackFor = SQLException.class)
    public Response<Oferta> updateOferta(Oferta oferta) {
        if (this.repository.existsById(oferta.getId())) {
            if (this.repository.updateOferta(
                    oferta.getNombre(),
                    oferta.getDescuento(),
                    oferta.getProduct(),
                    oferta.getDescripcion(),
                    oferta.getFechaInicio(),
                    oferta.getId()
            ) == 1) {
                return new Response<>(
                        oferta,
                        false,
                        200,
                        "La oferta se actualizo correctamente"
                );
            } else {
                return new Response<>(
                        null,
                        true,
                        400,
                        "Algo salio mal al actualizar la oferta"
                );
            }
        } else {
            return new Response<>(
                    null,
                    true,
                    400,
                    "La oferta no se encontro"
            );
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    public Response<Oferta> deleteOfertaById(Long id) {
        if (this.repository.existsById(id)){
            this.repository.deleteById(id);
            return new Response<>(
                    null,
                    false,
                    200,
                    "Ok"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "No se encontro la oferta"
        );
    }*/
}

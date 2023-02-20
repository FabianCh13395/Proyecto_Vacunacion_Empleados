package proyecto.proyecto_vacunacion.service;

import java.util.Optional;

public interface IGenericService <Em>{
    Optional<Em> findById(Long id);

    Em save(Em entity);

    Iterable<Em> findAll();

    void deleteById(Long id);
}

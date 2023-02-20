package proyecto.proyecto_vacunacion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenericServiceImpl <Em, R extends CrudRepository<Em, Long>> implements IGenericService<Em>{
    protected final R repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Em> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Em save(Em entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Em> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

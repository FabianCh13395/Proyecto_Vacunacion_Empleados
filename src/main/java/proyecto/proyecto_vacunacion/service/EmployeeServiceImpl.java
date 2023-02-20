package proyecto.proyecto_vacunacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, EmployeeRepository> implements IEmployeeService{
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> filtrarPorCedula(String cedula) {
        return repository.filtrarPorCedula(cedula);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> obtenerEmpleadosVacunados() {
        return repository.obtenerEmpleadosVacunados();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> obtenerEmpleadosNoVacunados() {
        return repository.obtenerEmpleadosNoVacunados();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> obtenerEmpleadosSputnikV() {
        return repository.obtenerEmpleadosSputnikV();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> obtenerEmpleadosAstraZeneca() {
        return repository.obtenerEmpleadosAstraZeneca();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> obtenerEmpleadosPfizer() {
        return repository.obtenerEmpleadosPfizer();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> obtenerEmpleadosJhonsonAndJhonson() {
        return repository.obtenerEmpleadosJhonsonAndJhonson();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Employee> filtrarPorRangoFechaVacunacion(LocalDate fechaInicio, LocalDate fechaFin) {
        return repository.filtrarPorRangoFechaVacunacion(fechaInicio, fechaFin);
    }
}

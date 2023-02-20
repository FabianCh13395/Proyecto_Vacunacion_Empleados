package proyecto.proyecto_vacunacion.service;

import proyecto.proyecto_vacunacion.domain.entity.Employee;

import java.time.LocalDate;
import java.util.Optional;

public interface IEmployeeService extends IGenericService<Employee>{
    Optional<Employee> filtrarPorCedula(String cedula);

    Iterable<Employee> obtenerEmpleadosVacunados();

    Iterable<Employee> obtenerEmpleadosNoVacunados();

    Iterable<Employee> obtenerEmpleadosSputnikV();

    Iterable<Employee> obtenerEmpleadosAstraZeneca();

    Iterable<Employee> obtenerEmpleadosPfizer();

    Iterable<Employee> obtenerEmpleadosJhonsonAndJhonson();

    Iterable<Employee> filtrarPorRangoFechaVacunacion(LocalDate fechaInicio, LocalDate fechaFin);
}

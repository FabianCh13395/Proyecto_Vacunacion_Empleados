package proyecto.proyecto_vacunacion.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import proyecto.proyecto_vacunacion.domain.entity.Employee;

import java.time.LocalDate;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    //Retornar el nombre del Usuario
    Optional<Employee> findOneByUsuario(String usuario);

    @Query("SELECT e FROM Employee e WHERE e.cedula = ?1")
    Optional<Employee> filtrarPorCedula(String cedula);

    @Query("SELECT e FROM Employee e WHERE e.vaccinationStatus =  proyecto.proyecto_vacunacion.domain.enumerator.VaccinationStatus.VACUNADO")
    Iterable<Employee> obtenerEmpleadosVacunados();

    @Query("SELECT e FROM Employee e WHERE e.vaccinationStatus = proyecto.proyecto_vacunacion.domain.enumerator.VaccinationStatus.NO_VACUNADO")
    Iterable<Employee> obtenerEmpleadosNoVacunados();

    @Query("SELECT e FROM Employee e JOIN e.vacuna v WHERE v.vaccineType = proyecto.proyecto_vacunacion.domain.enumerator.VaccineType.SPUTNIK")
    Iterable<Employee> obtenerEmpleadosSputnikV();

    @Query("SELECT e FROM Employee e JOIN e.vacuna v WHERE v.vaccineType = proyecto.proyecto_vacunacion.domain.enumerator.VaccineType.ASTRA_ZENECA")
    Iterable<Employee> obtenerEmpleadosAstraZeneca();

    @Query("SELECT e FROM Employee e JOIN e.vacuna v WHERE v.vaccineType = proyecto.proyecto_vacunacion.domain.enumerator.VaccineType.PFIZER")
    Iterable<Employee> obtenerEmpleadosPfizer();

    @Query("SELECT e FROM Employee e JOIN e.vacuna v WHERE v.vaccineType = proyecto.proyecto_vacunacion.domain.enumerator.VaccineType.JHONSON_AND_JHONSON")
    Iterable<Employee> obtenerEmpleadosJhonsonAndJhonson();

    @Query("SELECT e FROM Employee e JOIN e.vacuna v WHERE v.fechaVacunacion BETWEEN ?1 AND ?2")
    Iterable<Employee> filtrarPorRangoFechaVacunacion(LocalDate fechaInicio, LocalDate fechaFin);


}

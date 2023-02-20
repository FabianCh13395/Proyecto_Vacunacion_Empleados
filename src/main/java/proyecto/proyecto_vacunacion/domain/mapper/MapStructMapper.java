package proyecto.proyecto_vacunacion.domain.mapper;


import org.mapstruct.Mapper;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAdminDTO;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAllDTO;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeDTO;
import proyecto.proyecto_vacunacion.domain.dto.vaccine.VaccineDTO;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.domain.entity.Vaccine;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    EmployeeAdminDTO empleadoAdminDto(Employee empleado);

    Employee empleadoAdmin(EmployeeAdminDTO empleadoAdminDTO);

    EmployeeDTO empleadoDto(Employee empleado);

    Employee empleado(EmployeeDTO empleadoDTO);

    EmployeeAllDTO empleadoGetAllDTO(Employee empleado);

    VaccineDTO vacunaDTO(Vaccine vacuna);

    Vaccine vacuna(VaccineDTO vacunaDTO);
}

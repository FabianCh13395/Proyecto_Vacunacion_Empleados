package proyecto.proyecto_vacunacion.domain.mapper;

import org.springframework.stereotype.Component;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAdminDTO;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAllDTO;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeDTO;
import proyecto.proyecto_vacunacion.domain.dto.vaccine.VaccineDTO;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.domain.entity.Vaccine;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapStructMapperImpl implements MapStructMapper{
    @Override
    public EmployeeAdminDTO empleadoAdminDto(Employee empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmployeeAdminDTO empleadoAdminDTO = new EmployeeAdminDTO();

        empleadoAdminDTO.setId( empleado.getId() );
        empleadoAdminDTO.setCedula( empleado.getCedula() );
        empleadoAdminDTO.setNombres( empleado.getNombres() );
        empleadoAdminDTO.setApellidos( empleado.getApellidos() );
        empleadoAdminDTO.setEmail( empleado.getEmail() );
        empleadoAdminDTO.setRol(empleado.getRol());
        empleadoAdminDTO.setContrasenia(empleado.getContrasenia());
        empleadoAdminDTO.setUsuario(empleado.getUsuario());


        return empleadoAdminDTO;
    }

    @Override
    public Employee empleadoAdmin(EmployeeAdminDTO empleadoAdminDTO) {
        if ( empleadoAdminDTO == null ) {
            return null;
        }

        Employee empleado = new Employee();

        empleado.setId( empleadoAdminDTO.getId() );
        empleado.setCedula( empleadoAdminDTO.getCedula() );
        empleado.setNombres( empleadoAdminDTO.getNombres() );
        empleado.setApellidos( empleadoAdminDTO.getApellidos() );
        empleado.setEmail( empleadoAdminDTO.getEmail() );
        empleado.setRol(empleadoAdminDTO.getRol());
        empleado.setUsuario(empleadoAdminDTO.getUsuario());
        empleado.setContrasenia(empleadoAdminDTO.getContrasenia());


        return empleado;
    }

    @Override
    public EmployeeDTO empleadoDto(Employee empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmployeeDTO empleadoDTO = new EmployeeDTO();

        empleadoDTO.setId( empleado.getId() );
        empleadoDTO.setCedula( empleado.getCedula() );
        empleadoDTO.setNombres( empleado.getNombres() );
        empleadoDTO.setApellidos( empleado.getApellidos() );
        empleadoDTO.setEmail( empleado.getEmail() );
        empleadoDTO.setFechaNacimiento( empleado.getFechaNacimiento() );
        empleadoDTO.setDomicilio( empleado.getDomicilio() );
        empleadoDTO.setTelefonoMovil( empleado.getTelefonoMovil() );
        empleadoDTO.setVaccinationStatus( empleado.getVaccinationStatus() );
        empleadoDTO.setVaccine( vacunaDTO( empleado.getVacuna() ) );


        empleadoDTO.setRol(empleado.getRol());
        empleadoDTO.setContrasenia(empleado.getContrasenia());
        empleadoDTO.setUsuario(empleado.getUsuario());

        return empleadoDTO;
    }

    @Override
    public Employee empleado(EmployeeDTO empleadoDTO) {
        if ( empleadoDTO == null ) {
            return null;
        }

        Employee empleado = new Employee();

        empleado.setId( empleadoDTO.getId() );
        empleado.setCedula( empleadoDTO.getCedula() );
        empleado.setNombres( empleadoDTO.getNombres() );
        empleado.setApellidos( empleadoDTO.getApellidos() );
        empleado.setEmail( empleadoDTO.getEmail() );
        empleado.setFechaNacimiento( empleadoDTO.getFechaNacimiento() );
        empleado.setDomicilio( empleadoDTO.getDomicilio() );
        empleado.setTelefonoMovil( empleadoDTO.getTelefonoMovil() );
        empleado.setVaccinationStatus( empleadoDTO.getVaccinationStatus() );
        empleado.setRol(empleadoDTO.getRol());
        empleado.setContrasenia(empleadoDTO.getContrasenia());
        empleado.setUsuario(empleadoDTO.getUsuario());
        empleado.setVacuna( vacuna( empleadoDTO.getVaccine() ) );

        return empleado;
    }

    @Override
    public EmployeeAllDTO empleadoGetAllDTO(Employee empleado) {
        if ( empleado == null ) {
            return null;
        }

        EmployeeAllDTO empleadoGetAllDTO = new EmployeeAllDTO();

        empleadoGetAllDTO.setId( empleado.getId() );
        empleadoGetAllDTO.setCedula( empleado.getCedula() );
        empleadoGetAllDTO.setNombres( empleado.getNombres() );
        empleadoGetAllDTO.setApellidos( empleado.getApellidos() );
        empleadoGetAllDTO.setEmail( empleado.getEmail() );
        empleadoGetAllDTO.setFechaNacimiento( empleado.getFechaNacimiento() );
        empleadoGetAllDTO.setDomicilio( empleado.getDomicilio() );
        empleadoGetAllDTO.setTelefonoMovil( empleado.getTelefonoMovil() );
        empleadoGetAllDTO.setVaccinationStatus( empleado.getVaccinationStatus() );
        empleadoGetAllDTO.setRol(empleado.getRol());
        empleadoGetAllDTO.setContrasenia(empleado.getContrasenia());
        empleadoGetAllDTO.setUsuario(empleado.getUsuario());
        empleadoGetAllDTO.setVaccine( vacunaDTO( empleado.getVacuna() ) );

        return empleadoGetAllDTO;
    }

    @Override
    public VaccineDTO vacunaDTO(Vaccine vacuna) {
        if ( vacuna == null ) {
            return null;
        }

        VaccineDTO vacunaDTO = new VaccineDTO();

        vacunaDTO.setId( vacuna.getId() );
        vacunaDTO.setVaccineType( vacuna.getVaccineType() );
        vacunaDTO.setFechaVacunacion( vacuna.getFechaVacunacion() );
        vacunaDTO.setNumeroDosis( vacuna.getNumeroDosis() );

        return vacunaDTO;
    }

    @Override
    public Vaccine vacuna(VaccineDTO vacunaDTO) {
        if ( vacunaDTO == null ) {
            return null;
        }

        Vaccine vacuna = new Vaccine();

        vacuna.setId( vacunaDTO.getId() );
        vacuna.setVaccineType( vacunaDTO.getVaccineType() );
        vacuna.setFechaVacunacion( vacunaDTO.getFechaVacunacion() );
        vacuna.setNumeroDosis( vacunaDTO.getNumeroDosis() );

        return vacuna;
    }
}

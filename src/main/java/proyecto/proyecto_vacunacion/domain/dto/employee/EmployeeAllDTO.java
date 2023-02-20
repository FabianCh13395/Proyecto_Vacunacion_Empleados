package proyecto.proyecto_vacunacion.domain.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import proyecto.proyecto_vacunacion.domain.dto.vaccine.VaccineDTO;
import proyecto.proyecto_vacunacion.domain.enumerator.VaccinationStatus;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeAllDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("cedula")
    private String cedula;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apellidos")
    private String apellidos;

    @JsonProperty("email")
    private String email;

    @JsonProperty("fechaNacimiento")
    private LocalDate fechaNacimiento;

    @JsonProperty("domicilio")
    private String domicilio;

    @JsonProperty("telefonoMovil")
    private String telefonoMovil;

    @JsonProperty("vaccinationStatus")
    private VaccinationStatus vaccinationStatus;

    @JsonProperty("vaccine")
    VaccineDTO vaccine;

    @JsonProperty("rol")
    private int rol;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("contrasenia")
    private String contrasenia;
}

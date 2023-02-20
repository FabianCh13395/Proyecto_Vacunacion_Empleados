package proyecto.proyecto_vacunacion.domain.dto.vaccine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeDTO;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.domain.enumerator.VaccineType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class VaccineDTO {
    @JsonProperty("id")
    private Long id;

    @NotNull(message = "El tipo de vacuna aplicado es obligatorio")
    @JsonProperty("vaccineType")
    private VaccineType vaccineType;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "La fecha de vacunación es obligatoria")
    @PastOrPresent(message = "La fecha de vacunación debe ser anterior o igual a la fecha actual")
    @JsonProperty("fechaVacunacion")
    private LocalDate fechaVacunacion;


    @NotNull(message = "El número de dosis aplicados es obligatorio")
    @Positive(message = "El número ingresado debe ser mayor a 0")
    @JsonProperty("numeroDosis")
    private Integer numeroDosis;



}

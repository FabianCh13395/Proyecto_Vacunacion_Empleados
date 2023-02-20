package proyecto.proyecto_vacunacion.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAllDTO;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeDTO;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.domain.mapper.MapStructMapper;
import proyecto.proyecto_vacunacion.service.IEmployeeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Operaciones del Empleado", tags = "Rol EMPLEADO")
public class EmployeeController {

    private final IEmployeeService empleadoService;
    private final MapStructMapper mapStructure;

    @Autowired
    public EmployeeController(IEmployeeService empleadoService, MapStructMapper mapStructure) {
        this.empleadoService = empleadoService;
        this.mapStructure = mapStructure;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Long id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Employee> optionalEmployee = empleadoService.findById(id);

        if (optionalEmployee.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontró al empleado con el ID: %d", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Employee employee=optionalEmployee.get();

        if ( employee.getRol() == 2) {
            Optional<EmployeeAllDTO> optionalEmployeeAllDTO = optionalEmployee
                    .stream()
                    .map(mapStructure::empleadoGetAllDTO)
                    .findFirst();

            mensaje.put("estado", Boolean.TRUE);
            mensaje.put("datos", optionalEmployeeAllDTO.get());
            return ResponseEntity.ok(mensaje);
        } else {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", "No tienes permiso para ver esta información");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(mensaje);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id,
                                                @Valid @RequestBody EmployeeDTO employeeDTO,
                                                BindingResult resultado
    ) {
        Map<String, Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        EmployeeDTO empleadoActualizado;
        Optional<Employee> optionalEmployee = empleadoService.findById(id);


        if (resultado.hasErrors()) {
            resultado.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(validaciones);
        }

        if (optionalEmployee.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontró al empleado con el ID: %d", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Employee employee=optionalEmployee.get();

        if(employee.getRol()==2){
            //Datos que se llenan automaticamente
            employeeDTO.setContrasenia(employee.getContrasenia());
            employeeDTO.setUsuario(employee.getUsuario());

            empleadoActualizado = mapStructure.empleadoDto(optionalEmployee.get());
            empleadoActualizado.setFechaNacimiento(employeeDTO.getFechaNacimiento());
            empleadoActualizado.setDomicilio(employeeDTO.getDomicilio());
            empleadoActualizado.setTelefonoMovil(employeeDTO.getTelefonoMovil());
            empleadoActualizado.setVaccinationStatus(employeeDTO.getVaccinationStatus());
            empleadoActualizado.setVaccine(employeeDTO.getVaccine());


            mensaje.put("estado", Boolean.TRUE);
            mensaje.put("datos", empleadoService.save(mapStructure.empleado(empleadoActualizado)));
            return ResponseEntity.ok(mensaje);
        }
        mensaje.put("estado", Boolean.FALSE);
        mensaje.put("mensaje", String.format("No esta autorizado para modificar este usuario"));
        return ResponseEntity.badRequest().body(mensaje);


    }
}

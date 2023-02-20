package proyecto.proyecto_vacunacion.controller;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAdminDTO;
import proyecto.proyecto_vacunacion.domain.dto.employee.EmployeeAllDTO;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.domain.mapper.MapStructMapper;
import proyecto.proyecto_vacunacion.service.IEmployeeService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Acciones relacionadas con el Administrador", tags = "Rol ADMINISTRADOR")
public class EmployeeControllerAdmin {

    private final IEmployeeService empleadoService;
    private final MapStructMapper mapStructure;

    @Autowired
    public EmployeeControllerAdmin(IEmployeeService empleadoService, MapStructMapper mapStructure) {
        this.empleadoService = empleadoService;
        this.mapStructure = mapStructure;
    }
        //Obtener Todos los empleados
    @GetMapping
    public ResponseEntity<?> obtenerEmpleados() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Employee> empleados = (List<Employee>) empleadoService.findAll();

        if (empleados.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", "No se encontraron empleados");
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<EmployeeAdminDTO> employeeAdminDTOS= empleados
                .stream()
                .map(mapStructure::empleadoAdminDto)
                .collect(Collectors.toList());

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", employeeAdminDTOS);

        return ResponseEntity.ok(mensaje);
    }
    //Buscar empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Long id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Employee> optionalEmployee = empleadoService.findById(id);

        if (optionalEmployee.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontró al empleado con el ID: %d", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Optional<EmployeeAdminDTO> adminDTOOptional = optionalEmployee
                .stream()
                .map(mapStructure::empleadoAdminDto)
                .findFirst();

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", adminDTOOptional.get());

        return ResponseEntity.ok(mensaje);
    }

    //Crear Empleado
    @PostMapping
    public ResponseEntity<?> crearEmpleado(@Valid @RequestBody EmployeeAdminDTO empleado, BindingResult resultado) {
        Map<String, Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        String cedula = empleado.getCedula();
        Employee empleadoRegistrar;
        Optional<Employee> oEmpleadoCedula = empleadoService.filtrarPorCedula(cedula);

        if (resultado.hasErrors()) {
            resultado.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));

            return ResponseEntity.badRequest().body(validaciones);
        }

        if (oEmpleadoCedula.isPresent()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El empleado con cédula %s, ya se encuentra registrado", cedula));
            return ResponseEntity.badRequest().body(mensaje);
        }
        String usuario = empleado.getNombres().substring(0, 1) + empleado.getApellidos().substring(0, 3) + new Random().nextInt(100);
        String contrasena = UUID.randomUUID().toString().substring(0, 8);


        empleadoRegistrar = mapStructure.empleadoAdmin(empleado);
        empleadoRegistrar.setUsuario(usuario);
        empleadoRegistrar.setContrasenia(contrasena);
        empleadoRegistrar.setRol(empleado.getRol());

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", empleadoService.save(empleadoRegistrar));

        return ResponseEntity.ok(mensaje);
    }

    //Actualizar Empleado por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id, @Valid @RequestBody EmployeeAdminDTO empleado, BindingResult resultado) {
        Map<String, Object> mensaje = new HashMap<>();
        Map<String, Object> validaciones = new HashMap<>();
        EmployeeAdminDTO empleadoActualizado;
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

        if (empleado.getUsuario() == null || empleado.getUsuario().isEmpty()) {
            empleado.setUsuario(optionalEmployee.get().getUsuario());
        }
        if (empleado.getContrasenia() == null || empleado.getContrasenia().isEmpty()) {
            empleado.setContrasenia(optionalEmployee.get().getContrasenia());
        }

        empleadoActualizado = mapStructure.empleadoAdminDto(optionalEmployee.get());
        empleadoActualizado.setCedula(empleado.getCedula());
        empleadoActualizado.setNombres(empleado.getNombres());
        empleadoActualizado.setApellidos(empleado.getApellidos());
        empleadoActualizado.setEmail(empleado.getEmail());
        empleadoActualizado.setRol(empleado.getRol());


        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", empleadoService.save(mapStructure.empleadoAdmin(empleadoActualizado)));

        return ResponseEntity.ok(mensaje);
    }
    //Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable Long id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Employee> optionalEmployee = empleadoService.findById(id);

        if (optionalEmployee.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron empleados con el ID: %d", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        empleadoService.deleteById(id);

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", null);

        return ResponseEntity.ok(mensaje);
    }
    //Empleados por estados de vacunacion
    @GetMapping("/estado_vacunacion/{estadoVacunacion}")
    public ResponseEntity<?> obtenerEmpleadosPorEstadoVacunacion(@PathVariable String estadoVacunacion) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Employee> empleados;
        String sEstadoVacunacion;

        if (estadoVacunacion.equals("V")) {
            sEstadoVacunacion = "Vacunados";
            empleados = (List<Employee>) empleadoService.obtenerEmpleadosVacunados();
        } else if (estadoVacunacion.equals("NV")) {
            sEstadoVacunacion = "No Vacunados";
            empleados = (List<Employee>) empleadoService.obtenerEmpleadosNoVacunados();
        } else {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", "No existe el estado de vacunación ingresado");
            return ResponseEntity.badRequest().body(mensaje);
        }

        if (empleados.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron empleados %s", sEstadoVacunacion));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<EmployeeAllDTO> empleadosDTO = empleados
                .stream()
                .map(mapStructure::empleadoGetAllDTO)
                .collect(Collectors.toList());

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", empleadosDTO);

        return ResponseEntity.ok(mensaje);
    }

    //Empleados por tipo de Vacuna
    @GetMapping("/tipo_vacuna/{tipoVacuna}")
    public ResponseEntity<?> obtenerEmpleadosPorTipoVacuna(@PathVariable String tipoVacuna) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Employee> empleados;
        String sTipoVacuna;

        switch (tipoVacuna) {
            case "S":
                sTipoVacuna = "Sputnik";
                empleados = (List<Employee>) empleadoService.obtenerEmpleadosSputnikV();
                break;
            case "AZ":
                sTipoVacuna = "AstraZeneca";
                empleados = (List<Employee>) empleadoService.obtenerEmpleadosAstraZeneca();
                break;
            case "P":
                sTipoVacuna = "Pfizer";
                empleados = (List<Employee>) empleadoService.obtenerEmpleadosPfizer();
                break;
            case "J&J":
                sTipoVacuna = "Jhonson & Jhonson";
                empleados = (List<Employee>) empleadoService.obtenerEmpleadosJhonsonAndJhonson();
                break;
            default:
                mensaje.put("estado", Boolean.FALSE);
                mensaje.put("mensaje", "No existe el tipo de vacuna ingresado");
                return ResponseEntity.badRequest().body(mensaje);
        }

        if (empleados.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron empleados con la vacuna %s", sTipoVacuna));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<EmployeeAllDTO> empleadosDTO = empleados
                .stream()
                .map(mapStructure::empleadoGetAllDTO)
                .collect(Collectors.toList());

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", empleadosDTO);

        return ResponseEntity.ok(mensaje);
    }
    //Empleados por fecha de vacuna
    @GetMapping("/rango_vacunacion/{fechaInicio}/{fechaFin}")
    public ResponseEntity<?> obtenerEmpleadosPorRangoFechaVacunacion(@PathVariable String fechaInicio, @PathVariable String fechaFin) {
        Map<String, Object> mensaje = new HashMap<>();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fIni = LocalDate.parse(fechaInicio, format);
        LocalDate fFin = LocalDate.parse(fechaFin, format);

        List<Employee> empleados = (List<Employee>) empleadoService.filtrarPorRangoFechaVacunacion(fIni, fFin);

        if (empleados.isEmpty()) {
            mensaje.put("estado", Boolean.FALSE);
            mensaje.put("mensaje", "No se encontraron empleados vacunados en el rango de fechas ingresadas");
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<EmployeeAllDTO> empleadosDTO = empleados
                .stream()
                .map(mapStructure::empleadoGetAllDTO)
                .collect(Collectors.toList());

        mensaje.put("estado", Boolean.TRUE);
        mensaje.put("datos", empleadosDTO);

        return ResponseEntity.ok(mensaje);
    }
}

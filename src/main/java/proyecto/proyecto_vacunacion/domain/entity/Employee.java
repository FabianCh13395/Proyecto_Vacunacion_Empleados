package proyecto.proyecto_vacunacion.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import proyecto.proyecto_vacunacion.domain.enumerator.VaccinationStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Basic
    @Column(name = "cedula", nullable = false, unique = true)
    private String cedula;

    @NonNull
    @Basic
    @Column(name = "nombres", nullable = false)
    private String nombres;

    @NonNull
    @Basic
    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @NonNull
    @Basic
    @Column(name = "correo_electronico", nullable = false)
    private String email;

    @NonNull
    @Basic
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NonNull
    @Basic
    @Column(name = "domicilio")
    private String domicilio;

    @NonNull
    @Basic
    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @NonNull
    @Basic
    @Column(name = "estado_vacunacion")
    private VaccinationStatus vaccinationStatus;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacuna_id", referencedColumnName = "id_vacuna")
    private Vaccine vaccine;*/

    /*@OneToOne(cascade = CascadeType.ALL, mappedBy = "employee",optional = true)
    private Vaccine vaccine;*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vacuna_id", referencedColumnName = "id",nullable = true)
    private Vaccine vacuna;

    @Basic
    @Column(name = "usuario")
    private String usuario;

    @Basic
    @Column(name = "contrasenia")
    private String contrasenia;

    @Basic
    @Column(name = "rol")
    private int rol;

    @Column(name = "fecha_registro")
    @Basic
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_modificacion")
    @Basic
    private LocalDateTime fechaModificacion;

    @PrePersist
    public void beforePersist() {
        this.fechaRegistro = LocalDateTime.now();
    }

    @PreUpdate
    public void beforeUpdate() {
        this.fechaModificacion = LocalDateTime.now();
    }
}

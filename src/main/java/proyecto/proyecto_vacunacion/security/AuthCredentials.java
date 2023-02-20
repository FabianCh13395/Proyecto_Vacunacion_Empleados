package proyecto.proyecto_vacunacion.security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String usuario;
    private String password;
}

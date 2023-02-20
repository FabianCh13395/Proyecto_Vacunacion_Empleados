package proyecto.proyecto_vacunacion.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import proyecto.proyecto_vacunacion.domain.entity.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (employee.getRol() == 1) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else if (employee.getRol() == 2) {
            authorities.add(new SimpleGrantedAuthority("EMPLEADO"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getContrasenia();
    }

    @Override
    public String getUsername() {
        return employee.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getNombre(){
        return employee.getNombres();
    }
}

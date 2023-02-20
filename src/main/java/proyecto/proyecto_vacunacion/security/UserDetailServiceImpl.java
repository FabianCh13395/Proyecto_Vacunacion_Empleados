package proyecto.proyecto_vacunacion.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import proyecto.proyecto_vacunacion.domain.entity.Employee;
import proyecto.proyecto_vacunacion.repository.EmployeeRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
       Employee employee = employeeRepository
                .findOneByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario "+usuario+" no existe."));
       return new UserDetailsImpl(employee);
    }
}

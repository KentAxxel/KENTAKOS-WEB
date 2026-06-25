package kentakitos.backend.security;

import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.service.IUsuarioRolService;
import kentakitos.backend.service.IUsuariosService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUsuariosService usuariosService;
    private final IUsuarioRolService usuarioRolService;

    public CustomUserDetailsService(IUsuariosService usuariosService,
                                    IUsuarioRolService usuarioRolService) {
        this.usuariosService = usuariosService;
        this.usuarioRolService = usuarioRolService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuariosService.buscarPorUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<String> roles = usuarioRolService.buscarRolesPorUsuario(usuario.getIdusuario());
        List<GrantedAuthority> authorities = roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()))
                .collect(Collectors.toList());

        // deleted=1 significa activo en tu lógica
        boolean enabled = usuario.getDeleted() == 1;

        return new User(
                usuario.getUsername(),
                usuario.getContrasena(),
                enabled,
                true, true, true,
                authorities
        );
    }
}
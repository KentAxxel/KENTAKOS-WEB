package kentakitos.backend.security;

import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.service.IUsuarioRolService;
import kentakitos.backend.service.IUsuariosService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final IUsuariosService usuariosService;
    private final IUsuarioRolService usuarioRolService;

    public CustomOAuth2UserService(IUsuariosService usuariosService,
                                   IUsuarioRolService usuarioRolService) {
        this.usuariosService = usuariosService;
        this.usuarioRolService = usuarioRolService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        Optional<Usuarios> usuarioOpt = usuariosService.buscarPorCorreo(email);
        Usuarios usuario;

        if (usuarioOpt.isEmpty()) {
            // Registrar nuevo usuario con Google y rol INVITADO
            usuario = new Usuarios();
            usuario.setNombre(name);
            usuario.setCorreo(email);
            usuario.setUsername(email);
            usuario.setContrasena("");          // sin contraseña, login solo Google
            usuario.setAuthProvider("GOOGLE");
            usuario.setDeleted(1);              // activo
            usuario = usuariosService.guardar(usuario);

            // Asignar rol INVITADO
            usuarioRolService.asignarRolInvitado(usuario.getIdusuario());
        } else {
            usuario = usuarioOpt.get();
        }

        // Cargar roles para las autoridades
        List<String> roles = usuarioRolService.buscarRolesPorUsuario(usuario.getIdusuario());
        List<GrantedAuthority> authorities = roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()))
                .collect(Collectors.toList());

        return new DefaultOAuth2User(authorities, attributes, "email");
    }
}
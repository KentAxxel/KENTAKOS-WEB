package kentakitos.backend.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Roles;
import kentakitos.backend.entity.UsuarioRol;
import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.service.IUsuarioRolService;
import kentakitos.backend.service.IUsuariosService;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private IUsuariosService usuariosService;
    @Autowired
    private IUsuarioRolService usuarioRolService;
    // Repositorio de roles para obtener el rol INVITADO

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Extraer atributos del perfil de Google
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Buscar usuario por email en tu BD
        Usuarios usuario = usuariosService.buscarPorCorreo(email).orElse(null);
        if (usuario == null) {
            // Registrar nuevo usuario con rol INVITADO
            usuario = new Usuarios();
            usuario.setCorreo(email);
            usuario.setNombre(name);
            usuario.setUsername(email);  // o genera uno
            usuario.setContrasena("");   // no tiene contraseña, login solo por Google
            usuario = usuariosService.guardar(usuario);
            
            // Asignar rol INVITADO
            Roles rolInvitado = /* obtener de BD */;
            UsuarioRol ur = new UsuarioRol();
            ur.setIdusuario(usuario.getIdusuario());
            ur.setIdrol(rolInvitado.getIdrol());
            usuarioRolService.guardar(ur);
        }

        // Construir un OAuth2User que incluya las authorities (roles)
        Collection<GrantedAuthority> authorities = /* cargar roles desde BD: "ROLE_INVITADO", etc. */;
        return new DefaultOAuth2User(authorities, oAuth2User.getAttributes(), "email");
    }
}
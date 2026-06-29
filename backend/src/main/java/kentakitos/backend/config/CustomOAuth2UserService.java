package kentakitos.backend.config;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuarioRolService usuarioRolService;

    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = new DefaultOAuth2UserService().loadUser(userRequest);
        String email = oauth2User.getAttribute("email");
        String nombre = oauth2User.getAttribute("name");

        Usuarios usuario = usuariosRepository.findByCorreo(email)
                .orElse(null);

        if (usuario == null) {
            // Crear nuevo usuario
            usuario = new Usuarios();
            usuario.setCorreo(email);
            usuario.setNombre(nombre != null ? nombre : email);
            usuario.setUsername(email); // usar email como username
            usuario.setContrasena("");   // sin contraseña
            usuario.setAuthProvider("GOOGLE");
            usuario.setTokenVersion(0);
            usuario = usuariosRepository.save(usuario);

            // Asignar rol INVITADO
            Roles rolInvitado = rolesRepository.findByNombrerol("INVITADO")
                    .orElseThrow(() -> new RuntimeException("Rol INVITADO no encontrado"));
            UsuarioRol ur = new UsuarioRol();
            ur.setIdusuario(usuario);
            ur.setIdrol(rolInvitado);
            usuarioRolService.guardar(ur);
        } else {
            // Si el usuario existía pero se registró localmente, podrías actualizar el provider
            if (usuario.getAuthProvider() == null || usuario.getAuthProvider().equals("LOCAL")) {
                usuario.setAuthProvider("GOOGLE"); // opcional, vincular cuenta
                usuariosRepository.save(usuario);
            }
        }

        // Construir el OAuth2User de Spring Security con las authorities
        Set<GrantedAuthority> authorities = usuario.getUsuarioRoles().stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getIdrol().getNombrerol()))
                .collect(Collectors.toSet());

        return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), "email");
    }
}

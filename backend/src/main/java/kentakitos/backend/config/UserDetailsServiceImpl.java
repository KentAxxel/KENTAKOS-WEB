package kentakitos.backend.config;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Buscar por username o correo
        Usuarios usuario = usuariosRepository.findByUsernameOrCorreo(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + usernameOrEmail));

        Set<GrantedAuthority> authorities = usuario.getUsuarioRoles().stream()
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getIdrol().getNombrerol()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),   // puede ser el email también
                usuario.getContrasena(),
                usuario.getDeleted() == 1, // enabled = deleted == 1 (activo)
                true, true, true,
                authorities
        );
    }
}
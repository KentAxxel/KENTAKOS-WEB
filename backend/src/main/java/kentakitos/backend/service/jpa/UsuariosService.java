package kentakitos.backend.service.jpa;

import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.repository.UsuariosRepository;
import kentakitos.backend.service.IUsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService implements IUsuariosService {

    private final UsuariosRepository repoUsuarios;
    private final PasswordEncoder passwordEncoder;

    public UsuariosService(UsuariosRepository repoUsuarios, PasswordEncoder passwordEncoder) {
        this.repoUsuarios = repoUsuarios;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuarios> buscarTodos() {
        return repoUsuarios.findAll();
    }

    @Override
    public Usuarios guardar(Usuarios usuario) {
        // Solo encriptamos si la contraseña no está vacía y es un usuario local
        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()
                && (usuario.getAuthProvider() == null || usuario.getAuthProvider().equals("LOCAL"))) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        return repoUsuarios.save(usuario);
    }

    @Override
    public Usuarios modificar(Usuarios usuario) {
        // Al modificar, si se cambia la contraseña, la encriptamos igual
        if (usuario.getContrasena() != null && !usuario.getContrasena().isEmpty()
                && (usuario.getAuthProvider() == null || usuario.getAuthProvider().equals("LOCAL"))) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        return repoUsuarios.save(usuario);
    }

    @Override
    public Optional<Usuarios> buscarPorId(Integer id) {
        return repoUsuarios.findById(id);
    }

    @Override
    public void eliminar(Integer id) {
        repoUsuarios.deleteById(id);
    }

    // Métodos adicionales necesarios para Spring Security
    @Override
    public Optional<Usuarios> buscarPorUsername(String username) {
        return repoUsuarios.findByUsername(username);
    }

    @Override
    public Optional<Usuarios> buscarPorCorreo(String correo) {
        return repoUsuarios.findByCorreo(correo);
    }
}
package kentakitos.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kentakitos.backend.entity.Usuarios;
import kentakitos.backend.service.IUsuariosService;

@Service
public class UsuariosServiceImpl implements IUsuariosService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuariosRepository repo;

    @Override
    public Usuarios guardar(Usuarios usuario) {
        // Hashear la contraseña solo si es nueva o se ha modificado
        if (usuario.getIdusuario() == null || 
            !usuario.getContrasena().equals(obtenerContrasenaActual(usuario.getIdusuario()))) {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        }
        return repo.save(usuario);
    }
}
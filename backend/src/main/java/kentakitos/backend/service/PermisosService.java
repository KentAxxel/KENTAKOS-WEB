package kentakitos.backend.service;

import kentakitos.backend.dto.MatrizPermisosDTO;
import kentakitos.backend.entity.Permisos;
import kentakitos.backend.entity.RolPermiso;
import kentakitos.backend.entity.Roles;
import kentakitos.backend.repository.PermisosRepository;
import kentakitos.backend.repository.RolPermisoRepository;
import kentakitos.backend.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PermisosService {

    private final PermisosRepository permisosRepository;
    private final RolesRepository rolesRepository;
    private final RolPermisoRepository rolPermisoRepository;

    public PermisosService(PermisosRepository permisosRepository, RolesRepository rolesRepository, RolPermisoRepository rolPermisoRepository) {
        this.permisosRepository = permisosRepository;
        this.rolesRepository = rolesRepository;
        this.rolPermisoRepository = rolPermisoRepository;
    }

    public MatrizPermisosDTO getMatrizPermisos() {
        MatrizPermisosDTO matrizDTO = new MatrizPermisosDTO();
        
        // 1. Obtener todos los roles activos
        List<Roles> roles = rolesRepository.findAll();
        List<String> roleNames = roles.stream().map(Roles::getNombrerol).collect(Collectors.toList());
        matrizDTO.setRoles(roleNames);
        
        // 2. Obtener todos los permisos (módulos)
        List<Permisos> permisos = permisosRepository.findAll();
        List<String> modulos = permisos.stream().map(Permisos::getNombrepermiso).collect(Collectors.toList());
        matrizDTO.setModulos(modulos);
        
        // 3. Obtener todas las relaciones (RolPermiso)
        List<RolPermiso> relations = rolPermisoRepository.findAll();
        
        // 4. Construir la matriz Map<Modulo, Map<Rol, Boolean>>
        Map<String, Map<String, Boolean>> matriz = new HashMap<>();
        
        for (String modulo : modulos) {
            Map<String, Boolean> roleChecks = new HashMap<>();
            for (String rol : roleNames) {
                // Verificar si existe la relación
                boolean hasPermission = relations.stream()
                    .anyMatch(rp -> rp.getPermiso().getNombrepermiso().equals(modulo) && 
                                   rp.getRol().getNombrerol().equals(rol));
                
                roleChecks.put(rol, hasPermission);
            }
            matriz.put(modulo, roleChecks);
        }
        
        matrizDTO.setMatriz(matriz);
        return matrizDTO;
    }
}

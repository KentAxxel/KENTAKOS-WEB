package kentakitos.backend.service;

import kentakitos.backend.dto.PermisoResponseDTO;
import kentakitos.backend.dto.RolResponseDTO;
import kentakitos.backend.entity.RolPermiso;
import kentakitos.backend.entity.Roles;
import kentakitos.backend.repository.RolPermisoRepository;
import kentakitos.backend.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;
    private final RolPermisoRepository rolPermisoRepository;

    public RolesService(RolesRepository rolesRepository, RolPermisoRepository rolPermisoRepository) {
        this.rolesRepository = rolesRepository;
        this.rolPermisoRepository = rolPermisoRepository;
    }

    public List<RolResponseDTO> getAllRoles() {
        List<Roles> roles = rolesRepository.findAll();
        
        return roles.stream().map(rol -> {
            RolResponseDTO dto = new RolResponseDTO();
            dto.setIdRol(rol.getIdrol());
            dto.setNombre(rol.getNombrerol());
            // Derivar una descripción simple basada en el nombre si no existe columna de descripción
            dto.setDescripcion("Rol con nivel de acceso " + rol.getNombrerol());
            dto.setEstado(rol.getDeleted());
            
            // Buscar permisos
            List<RolPermiso> rpList = rolPermisoRepository.findByRol_Idrol(rol.getIdrol());
            List<PermisoResponseDTO> permisosDto = rpList.stream().map(rp -> {
                PermisoResponseDTO pdto = new PermisoResponseDTO();
                pdto.setIdPermiso(rp.getPermiso().getIdpermiso());
                pdto.setNombre(rp.getPermiso().getNombrepermiso());
                return pdto;
            }).collect(Collectors.toList());
            
            dto.setPermisos(permisosDto);
            return dto;
        }).collect(Collectors.toList());
    }
}

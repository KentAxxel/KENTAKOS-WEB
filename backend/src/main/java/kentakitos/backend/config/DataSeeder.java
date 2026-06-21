package kentakitos.backend.config;

import kentakitos.backend.entity.Permisos;
import kentakitos.backend.entity.RolPermiso;
import kentakitos.backend.entity.Roles;
import kentakitos.backend.repository.PermisosRepository;
import kentakitos.backend.repository.RolPermisoRepository;
import kentakitos.backend.repository.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RolesRepository rolesRepository;
    private final PermisosRepository permisosRepository;
    private final RolPermisoRepository rolPermisoRepository;
    private final JdbcTemplate jdbcTemplate;

    public DataSeeder(RolesRepository rolesRepository, PermisosRepository permisosRepository, RolPermisoRepository rolPermisoRepository, JdbcTemplate jdbcTemplate) {
        this.rolesRepository = rolesRepository;
        this.permisosRepository = permisosRepository;
        this.rolPermisoRepository = rolPermisoRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solucionar el problema de las columnas faltantes (idusuario_rol y idrolpermiso)
        try {
            jdbcTemplate.execute("ALTER TABLE rol_permiso DROP PRIMARY KEY");
        } catch (Exception e) {}
        try {
            jdbcTemplate.execute("ALTER TABLE rol_permiso ADD COLUMN idrolpermiso INT PRIMARY KEY AUTO_INCREMENT");
        } catch (Exception e) {
            try { jdbcTemplate.execute("ALTER TABLE rol_permiso MODIFY idrolpermiso INT AUTO_INCREMENT"); } catch (Exception ex) {}
        }

        try {
            jdbcTemplate.execute("ALTER TABLE usuario_rol DROP PRIMARY KEY");
        } catch (Exception e) {}
        try {
            jdbcTemplate.execute("ALTER TABLE usuario_rol ADD COLUMN idusuario_rol INT PRIMARY KEY AUTO_INCREMENT");
        } catch (Exception e) {
            try { jdbcTemplate.execute("ALTER TABLE usuario_rol MODIFY idusuario_rol INT AUTO_INCREMENT"); } catch (Exception ex) {}
        }

        // Solo sembrar si no existen roles
        if (rolesRepository.count() == 0) {
            System.out.println("No se encontraron roles, procediendo a sembrar datos...");

            // 1. Crear Permisos
            Permisos pCaja = crearPermiso("VER_CAJA");
            Permisos pMesas = crearPermiso("VER_MESAS");
            Permisos pInventario = crearPermiso("VER_INVENTARIO");
            Permisos pUsuarios = crearPermiso("GESTIONAR_USUARIOS");
            Permisos pReportes = crearPermiso("VER_REPORTES");

            // 2. Crear Roles
            Roles rolAdmin = new Roles();
            rolAdmin.setNombrerol("ADMIN");
            rolAdmin = rolesRepository.save(rolAdmin);

            Roles rolUsuario = new Roles();
            rolUsuario.setNombrerol("USUARIO");
            rolUsuario = rolesRepository.save(rolUsuario);

            // 3. Asignar Permisos a ADMIN (Todos)
            List<Permisos> permisosAdmin = List.of(pCaja, pMesas, pInventario, pUsuarios, pReportes);
            for (Permisos p : permisosAdmin) {
                RolPermiso rp = new RolPermiso();
                rp.setRol(rolAdmin);
                rp.setPermiso(p);
                rolPermisoRepository.save(rp);
            }

            // 4. Asignar Permisos a USUARIO (Solo Caja, Mesas, Inventario)
            List<Permisos> permisosUsuario = List.of(pCaja, pMesas, pInventario);
            for (Permisos p : permisosUsuario) {
                RolPermiso rp = new RolPermiso();
                rp.setRol(rolUsuario);
                rp.setPermiso(p);
                rolPermisoRepository.save(rp);
            }

            System.out.println("Datos sembrados exitosamente.");
        } else {
            System.out.println("Los roles ya existen en la base de datos, saltando sembrado de datos.");
        }
    }

    private Permisos crearPermiso(String nombre) {
        Permisos p = new Permisos();
        p.setNombrepermiso(nombre);
        p.setDeleted(1); // 1 = activo (basado en la lógica del entity)
        return permisosRepository.save(p);
    }
}

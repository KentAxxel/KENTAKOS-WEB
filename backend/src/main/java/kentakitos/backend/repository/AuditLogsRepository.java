package kentakitos.backend.repository;

import kentakitos.backend.entity.AuditLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditLogsRepository extends JpaRepository<AuditLogs, Integer> {
    Optional<AuditLogs> findTopByOrderByIdDesc();
}

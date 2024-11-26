package tomi.coderhouse.example.repository;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.repository.CrudRepository;
import tomi.coderhouse.example.session.Session;

@Schema(description = "Repositorio para gestionar las sesiones")
public interface SessionRepository extends CrudRepository<Session, Long> {
}

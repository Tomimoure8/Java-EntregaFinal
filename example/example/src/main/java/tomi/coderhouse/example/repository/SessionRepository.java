package tomi.coderhouse.example.repository;

import org.springframework.data.repository.CrudRepository;
import tomi.coderhouse.example.session.Session;

public interface SessionRepository extends CrudRepository<Session, Long> {
}

package tomi.coderhouse.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tomi.coderhouse.example.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}

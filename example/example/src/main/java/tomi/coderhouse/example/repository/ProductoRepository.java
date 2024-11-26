package tomi.coderhouse.example.repository;


import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import tomi.coderhouse.example.entity.Product;

@Schema(description = "Repositorio para gestionar los productos")
public interface ProductoRepository extends JpaRepository<Product,Long>{ }

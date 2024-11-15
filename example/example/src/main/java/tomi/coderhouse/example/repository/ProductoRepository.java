package tomi.coderhouse.example.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tomi.coderhouse.example.entity.Product;

public interface ProductoRepository extends JpaRepository<Product,Long>{ }

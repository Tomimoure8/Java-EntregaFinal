package tomi.coderhouse.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import tomi.coderhouse.example.session.Session;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int cantidad;


    @ManyToMany(mappedBy = "products")
    private List<Session> sessions;
}

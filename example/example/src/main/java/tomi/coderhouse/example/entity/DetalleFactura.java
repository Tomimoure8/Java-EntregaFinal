package tomi.coderhouse.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import tomi.coderhouse.example.model.Factura;

@Entity
@Data
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product producto;

    private int cantidad;
    private double precioUnitario;
}

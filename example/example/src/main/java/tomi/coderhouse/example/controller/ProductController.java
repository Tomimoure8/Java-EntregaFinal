package tomi.coderhouse.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomi.coderhouse.example.exception.InsufficientStockException;
import tomi.coderhouse.example.model.Factura;
import tomi.coderhouse.example.entity.Product;
import tomi.coderhouse.example.model.FacturaRequest;
import tomi.coderhouse.example.model.FacturaService;
import tomi.coderhouse.example.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productoService;

    @Autowired
    private FacturaService facturaService;

    @PostMapping ("/facturas")
    public ResponseEntity<Factura> crearFactura (@RequestBody FacturaRequest facturaRequest) {
        Factura factura = new Factura();
        factura = facturaService.crearNuevaFactura(factura, facturaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(factura);
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<String> handleInsufficientStockException(InsufficientStockException
            ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productoService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
}


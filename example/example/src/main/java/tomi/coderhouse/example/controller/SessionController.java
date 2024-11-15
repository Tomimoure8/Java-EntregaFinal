package tomi.coderhouse.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tomi.coderhouse.example.entity.Product;
import tomi.coderhouse.example.model.Cliente;
import tomi.coderhouse.example.model.Factura;
import tomi.coderhouse.example.session.Session;

import tomi.coderhouse.example.session.SessionService;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;


    @PostMapping
    public ResponseEntity<Session>
    createSession(@RequestBody SecurityProperties.User user) {
        Session session = sessionService.createSession(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(session);
    }

    @PostMapping("/{sessionId}/products")
    public ResponseEntity<Session> addProductToSession(@PathVariable Long sessionId, @RequestBody Product product) {
        Session session = sessionService.addProductToSession(sessionId, product);
        return ResponseEntity.ok(session);
    }

    @PostMapping("/{sessionId}/checkout")
    public ResponseEntity<Factura> checkoutSession(@PathVariable Long sessionId, @RequestBody Cliente cliente) {
        Session session = sessionService.getSessionById(sessionId);
        Factura factura = sessionService.generateFactura(session, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(factura);
    }
}

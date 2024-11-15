package tomi.coderhouse.example.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import tomi.coderhouse.example.entity.DetalleFactura;
import tomi.coderhouse.example.entity.Product;
import tomi.coderhouse.example.exception.NotFoundException;
import tomi.coderhouse.example.model.Cliente;
import tomi.coderhouse.example.model.Factura;
import tomi.coderhouse.example.repository.SessionRepository;
import org.springframework.web.bind.support.SessionStatus;
import tomi.coderhouse.example.repository.FacturaRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    private FacturaRepository facturaRepository;

    public Session createSession(SecurityProperties.User user) {
        Session session = new Session();
        session.setUser(user);
        session.setCreatedAt(new Date());
        session.setStatus(Session.STATUS_OPEN);
        return sessionRepository.save(session);
    }

    public Session getSessionById(Long sessionId) {
        return sessionRepository.findById(sessionId).orElse(null);
    }
    // Otros métodos: agregar productos a una sesión, cerrar una sesión, etc.
    public Session addProductToSession(Long sessionId, Product product) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Session not found"));
        session.getProducts().add(product);
        return sessionRepository.save(session);
    }

    public Double calculateTotal(Session session) {
        return session.getProducts().stream()
                .mapToDouble(product -> product.getPrice() * product.getCantidad())
                .sum();
    }

    public Factura generateFactura(Session session, Cliente cliente) {
        // Crear una nueva factura
        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(new Date());

        // Calcular el total
        Double total = calculateTotal(session);
        factura.setTotal(total);

        // Crear los detalles de la factura
        List<DetalleFactura> detallesFactura = new ArrayList<>();
        for (Product producto : session.getProducts()) {
            DetalleFactura detalle = new DetalleFactura();
            detalle.setProducto(producto);
            detalle.setCantidad(producto.getCantidad());
            detalle.setPrecioUnitario(producto.getPrice());
            detalle.setFactura(factura);
            detallesFactura.add(detalle);
        }
        factura.setDetallesFactura(detallesFactura);


        facturaRepository.save(factura);
        closeSession(session.getId());

        return factura;
    }

    public void closeSession(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NotFoundException("Session not found"));
        session.setStatus(Session.STATUS_CLOSED);
        sessionRepository.save(session);
    }
}

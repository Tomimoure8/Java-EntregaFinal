package tomi.coderhouse.example.session;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.support.SessionStatus;
import tomi.coderhouse.example.entity.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")

    private SecurityProperties.User user; // Opcional,
    // si deseas asociar la sesión a un usuario específico

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public static final String STATUS_OPEN = "OPEN";
    public static final String STATUS_CLOSED = "CLOSED";
    private String status;
    // ... getters y setters para status ...
}

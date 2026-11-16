package training.agent;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class NotificationController {

    private final Map<UUID, Notification> repository = new ConcurrentHashMap<>();

    public record Notification(UUID id, String owner, String message) {}

    public NotificationController() {
        UUID id1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
        UUID id2 = UUID.fromString("22222222-2222-2222-2222-222222222222");
        repository.put(id1, new Notification(id1, "alice", "Geheime Benachrichtigung für Alice"));
        repository.put(id2, new Notification(id2, "bob", "Vertrauliche Nachricht für Bob"));
    }

    /**
     * Unsicherer Vorschlag des Agenten aus Übung G:
     * Prüft nur die Rolle APP_USER, aber ignoriert den tatsächlichen Eigentümer (IDOR)!
     */
    @GetMapping("/notifications/{id}")
    @PreAuthorize("hasAuthority('APP_USER')")
    public Notification get(@PathVariable UUID id) {
        Notification notification = repository.get(id);
        if (notification == null) {
            throw new IllegalArgumentException("Not found: " + id);
        }
        return notification;
    }
}

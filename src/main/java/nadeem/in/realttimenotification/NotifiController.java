package nadeem.in.realttimenotification;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notif")
@CrossOrigin(origins = "http://localhost:63342")
public class NotifiController {

    private final Sinks.Many<notification> notificationsSink = Sinks.many().multicast().onBackpressureBuffer();
    private final List<notification> notificationHistory = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @GetMapping("/send")
    public String publishnotification(@RequestParam String message) {
        notification notification = new notification(idGenerator.getAndIncrement(), message, LocalDateTime.now());
        notificationHistory.add(notification);
        notificationsSink.tryEmitNext(notification);
        return "notification sent: " + message;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<notification> streamnotifications() {
        return notificationsSink.asFlux();
    }

    @GetMapping("/history")
    public List<notification> getnotificationHistory() {
        return notificationHistory;
    }

    @DeleteMapping("/clear")
    public String clearnotificationHistory() {
        notificationHistory.clear();
        return "notification history cleared!";
    }

    @GetMapping("/search")
    public List<notification> searchnotifications(@RequestParam String keyword) {
        return notificationHistory.stream()
                .filter(notification -> notification.getMessage().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/summary")
    public String getnotificationSummary() {
        int totalnotifications = notificationHistory.size();
        return "Total notifications sent: " + totalnotifications;
    }

    @PutMapping("/update/{id}")
    public String updatenotification(@PathVariable int id, @RequestParam String newMessage) {
        for (notification notification : notificationHistory) {
            if (notification.getId() == id) {
                notification.setMessage(newMessage);
                notification.setTimestamp(LocalDateTime.now());
                return "notification updated: " + newMessage;
            }
        }
        return "notification with ID " + id + " not found!";
    }

  
}

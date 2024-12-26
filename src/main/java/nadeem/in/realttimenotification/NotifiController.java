package nadeem.in.realttimenotification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/notif")
@CrossOrigin("http://localhost:63342")
public class NotifiController {
    private final Sinks.Many<notification> notificationsSink = Sinks.many().multicast().onBackpressureBuffer();

    @GetMapping("/send")
    public String publishNotification(@RequestParam String message) {

        notification notification = new notification(message);
        notificationsSink.tryEmitNext(notification);
        return "Notification send:- " + message;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<notification> streamNotification() {

        return notificationsSink.asFlux();
    }
}

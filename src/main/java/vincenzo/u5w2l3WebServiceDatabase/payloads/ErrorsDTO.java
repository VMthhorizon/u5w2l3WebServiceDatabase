package vincenzo.u5w2l3WebServiceDatabase.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsDTO(String message, LocalDateTime timestamp, List<String> errorsList) {
}

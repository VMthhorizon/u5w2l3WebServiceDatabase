package vincenzo.u5w2l3WebServiceDatabase.payloads;

import jakarta.validation.constraints.*;
import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;

import java.util.UUID;

public record BlogPostsPayload(
        @NotBlank(message = "La categoria é obbligatoria")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "La categoria non può contenere numeri o " +
                "caratteri" +
                " speciali")
        String categoria,
        @NotBlank(message = "Il titolo é obbligatorio")
        @Size(min = 2, max = 25, message = "Il titolo deve essere compreso tra 2 e 25 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il Titolo non può contenere numeri o " +
                "caratteri" +
                " speciali")
        String titolo,
        @NotBlank(message = "Il contenuto é obbligatorio")
        @Size(min = 2, max = 100, message = "Il contenuto deve essere compreso tra 2 e 100 caratteri")
        String contenuto,
        @NotNull(message = "Il campo Tempo di lettura é obbligatorio")
        @PositiveOrZero(message = "il Tempo di lettura non può essere un numero negativo o 0")
        int tempoDiLettura,
        @NotBlank(message = "L'ID utente è obbligatorio")
        @org.hibernate.validator.constraints.UUID(message = "Formato UUID non valido")
        UUID autoreId) {
}

package vincenzo.u5w2l3WebServiceDatabase.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AutoreRequestPayload(
        @NotBlank(message = "Il campo del NOME non può essere vuoto")
        @Size(min = 2, max = 50, message = "Il nome deve essere compreso tra 2 e 50 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il nome non può contenere numeri o caratteri" +
                " speciali")
        String nome,
        @NotBlank(message = "Il campo del COGNOME non può essere vuoto")
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra 2 e 50 caratteri")
        @Pattern(regexp = "^[a-zA-Z\\sàèìòùòóÁÉÍÓÚçÇñÑ'-]+$", message = "Il nome non può contenere numeri o caratteri" +
                " speciali")
        String cognome,
        @NotBlank(message = "Il campo EMAIL non può essere vuoto")
        @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Formato email non valido")
        String email,
        @NotBlank(message = "La Data di nascita è obbligatoria")
        String dataDiNascita) {
}

package vincenzo.u5w2l3WebServiceDatabase.payloads;

import java.util.UUID;

public record AutoreResponsePayload(UUID id, String nome, String cognome, String email, String dataDiNascita,
                                    String avatar) {
}

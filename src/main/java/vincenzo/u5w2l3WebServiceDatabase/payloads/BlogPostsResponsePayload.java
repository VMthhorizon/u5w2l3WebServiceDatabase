package vincenzo.u5w2l3WebServiceDatabase.payloads;

import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;

import java.util.UUID;

public record BlogPostsResponsePayload(String categoria,
                                       String titolo,
                                       String contenuto,
                                       int tempoDiLettura,
                                       UUID autoreID) {
}

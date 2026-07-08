package vincenzo.u5w2l3WebServiceDatabase.payloads;

import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;

import java.util.UUID;

public class BlogPostsResponsePayload {
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private UUID autoreID;

    public BlogPostsResponsePayload(String categoria, String titolo, String contenuto, int tempoDiLettura,
                                    UUID autoreID) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autoreID = autoreID;
    }

    public String getContenuto() {
        return contenuto;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public UUID getAutoreID() {
        return autoreID;
    }

    public int getTempoDiLettura() {
        return tempoDiLettura;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setTempoDiLettura(int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }

    @Override
    public String toString() {
        return "BlogPostsResponsePayload{" +
                ", categoria='" + categoria + '\'' +
                ", titolo='" + titolo + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", tempoDiLettura=" + tempoDiLettura +
                '}';
    }
}

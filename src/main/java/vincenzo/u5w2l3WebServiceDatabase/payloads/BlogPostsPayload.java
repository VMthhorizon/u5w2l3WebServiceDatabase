package vincenzo.u5w2l3WebServiceDatabase.payloads;

import vincenzo.u5w2l3WebServiceDatabase.entities.Autore;

import java.util.UUID;

public class BlogPostsPayload {

    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private UUID autoreId;

    public BlogPostsPayload(String categoria, String titolo, String contenuto, int tempoDiLettura, UUID autoreId) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autoreId = autoreId;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getContenuto() {
        return contenuto;
    }

    public int getTempoDiLettura() {
        return tempoDiLettura;
    }

    public UUID getAutoreId() {
        return autoreId;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public void setTempoDiLettura(int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    @Override
    public String toString() {
        return "BlogPostsPayload{" +
                "categoria='" + categoria + '\'' +
                ", titolo='" + titolo + '\'' +
                ", contenuto='" + contenuto + '\'' +
                ", tempoDiLettura=" + tempoDiLettura +
                '}';
    }
}

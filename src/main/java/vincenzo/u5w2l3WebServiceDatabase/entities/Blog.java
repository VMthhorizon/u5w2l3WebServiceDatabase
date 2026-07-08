package vincenzo.u5w2l3WebServiceDatabase.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Blogs")
public class Blog {

    @Id
    @GeneratedValue
    private UUID id;
    private String categoria;
    private String titolo;
    private String contenuto;
    private int tempoDiLettura;
    private String cover;
    @ManyToOne
    @JoinColumn(name = "id_autore")
    private Autore autore;

    protected Blog() {
    }


    public Blog(String categoria, String titolo, String contenuto, int tempoDiLettura, Autore autore) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.cover = "https://picsum.photos/200/300";
        this.autore = autore;
    }

    public void setTempoDiLettura(int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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

    public UUID getId() {
        return id;
    }


    public Autore getAutore() {
        return autore;
    }

    public String getCover() {
        return cover;
    }
}

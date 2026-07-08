package vincenzo.u5w2l3WebServiceDatabase.payloads;

import java.util.UUID;

public class AutoreResponsePayload {

    private UUID id;
    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;
    private String avatar;

    public AutoreResponsePayload(UUID id, String nome, String cognome, String email, String dataDiNascita,
                                 String avatar) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
        this.avatar = avatar;
    }

    public UUID getId() {
        return id;
    }

    public String getCognome() {
        return cognome;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    @Override
    public String toString() {
        return "AutoreResponsePayload{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataDiNascita='" + dataDiNascita + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}

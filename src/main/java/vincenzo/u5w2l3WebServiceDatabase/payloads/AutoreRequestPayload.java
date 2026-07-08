package vincenzo.u5w2l3WebServiceDatabase.payloads;

public class AutoreRequestPayload {

    private String nome;
    private String cognome;
    private String email;
    private String dataDiNascita;

    public AutoreRequestPayload(String nome, String cognome, String email, String dataDiNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataDiNascita = dataDiNascita;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    @Override
    public String toString() {
        return "AutoreRequestPayload{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", dataDiNascita=" + dataDiNascita +
                '}';
    }
}

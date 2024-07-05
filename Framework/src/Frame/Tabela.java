package Frame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Tabela {
    private String nome;
    private List<Campo> campos;
    private List<String> chavesEstrangeiras;

    public Tabela(String nome) {
        this.nome = nome;
        this.campos = new ArrayList<>();
        this.chavesEstrangeiras = new ArrayList<>();
    }

    public void adicionarCampo(Campo campo) {
        campos.add(campo);
    }

    public void adicionarChaveEstrangeira(String nomeCampo, String tabelaReferenciada, String campoReferenciado) {
        chavesEstrangeiras.add(String.format("FOREIGN KEY (%s) REFERENCES %s(%s)", nomeCampo, tabelaReferenciada, campoReferenciado));
    }

    public String getNome() {
        return nome;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public List<String> getChavesEstrangeiras() {
        return chavesEstrangeiras;
    }

}

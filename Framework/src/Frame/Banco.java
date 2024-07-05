package Frame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Banco {
    private String nome;
    private List<Tabela> tabelas;

    public Banco(String nome) {
        this.nome = nome;
        this.tabelas = new ArrayList<>();
    }

    public void adicionarTabela(Tabela tabela) {
        tabelas.add(tabela);
    }

    public String getNome() {
        return nome;
    }

    public List<Tabela> getTabelas() {
        return tabelas;
    }
}

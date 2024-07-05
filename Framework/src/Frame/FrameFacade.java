package Frame;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author eduardo
 */
public class FrameFacade {

    private Conexao conexao;
    private Banco banco;

    public FrameFacade(String host, int port, String user, String pass, String databaseName) {
        this.conexao = new Conexao(host, port, user, pass, databaseName);
        this.banco = new Banco(databaseName);
    }

    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public void adicionarTabela(String nomeTabela) {
        banco.adicionarTabela(new Tabela(nomeTabela));
    }

    public void adicionarCampo(String nomeTabela, String nomeCampo, TipoCampo tipoCampo) {
        for (Tabela tabela : banco.getTabelas()) {
            if (tabela.getNome().equals(nomeTabela)) {
                tabela.adicionarCampo(new Campo(nomeCampo, tipoCampo));
                return;
            }
        }
        throw new IllegalArgumentException("Tabela não encontrada: " + nomeTabela);
    }

    public void adicionarChaveEstrangeira(String nomeTabela, String nomeCampo, String tabelaReferenciada, String campoReferenciado) {
        for (Tabela tabela : banco.getTabelas()) {
            if (tabela.getNome().equals(nomeTabela)) {
                tabela.adicionarChaveEstrangeira(nomeCampo, tabelaReferenciada, campoReferenciado);
                return;
            }
        }
        throw new IllegalArgumentException("Tabela não encontrada: " + nomeTabela);
    }

    public String gerarScript() {
        Frame frame = new Frame(conexao); // Instancia o Frame com a conexão adequada
        return frame.gerarScript(banco);
    }

    public void executarScript() throws SQLException {
        try (Connection conn = conexao.conectar()) {
            Frame frame = new Frame(conexao); // Instancia o Frame com a conexão adequada
            frame.executarScript(conn, gerarScript());
        }
    }
}

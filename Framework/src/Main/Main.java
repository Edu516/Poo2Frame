package Main;

import Frame.FrameFacade;
import Frame.TipoCampo;
import java.sql.SQLException;

/**
 *
 * @author eduardo
 */
public class Main {
    public static void main(String[] args) {
        try {
            FrameFacade facade = new FrameFacade("localhost", 5432, "postgres", "postgres", "Facul");
            criaTabelaSimples(facade);
            criaTabelaEstrangeira(facade);
            criaTabelaAssociativa(facade);
            
            System.out.println("------------------- Informações Conexão------------");
            System.out.println(facade.getConexao().toString());
            System.out.println("------------------- Informações Conexão------------");

            String script = facade.gerarScript();
            System.out.println("Script Gerado:\n" + script);

            facade.executarScript();
            System.out.println("Script Executado com Sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void criaTabelaSimples(FrameFacade facade) {
        facade.adicionarTabela("Pessoa");
        facade.adicionarCampo("Pessoa", "id", TipoCampo.SERIAL);
        facade.adicionarCampo("Pessoa", "nome", TipoCampo.VARCHAR);
        facade.adicionarCampo("Pessoa", "idade", TipoCampo.INTEGER);
    }

    private static void criaTabelaEstrangeira(FrameFacade facade) {
        facade.adicionarTabela("Carro");
        facade.adicionarCampo("Carro", "id", TipoCampo.SERIAL);
        facade.adicionarCampo("Carro", "modelo", TipoCampo.VARCHAR);
        facade.adicionarCampo("Carro", "ano", TipoCampo.INTEGER);

        facade.adicionarTabela("Roda");
        facade.adicionarCampo("Roda", "id", TipoCampo.SERIAL);
        facade.adicionarCampo("Roda", "tipo", TipoCampo.VARCHAR);
        facade.adicionarCampo("Roda", "carro_id", TipoCampo.INTEGER);

        facade.adicionarChaveEstrangeira("Roda", "carro_id", "Carro", "id");
    }

    private static void criaTabelaAssociativa(FrameFacade facade) {
        facade.adicionarTabela("Aluno");
        facade.adicionarCampo("Aluno", "id", TipoCampo.SERIAL);
        facade.adicionarCampo("Aluno", "nome", TipoCampo.VARCHAR);

        facade.adicionarTabela("Curso");
        facade.adicionarCampo("Curso", "id", TipoCampo.SERIAL);
        facade.adicionarCampo("Curso", "nome", TipoCampo.VARCHAR);

        facade.adicionarTabela("AlunoCurso");
        facade.adicionarCampo("AlunoCurso", "aluno_id", TipoCampo.INTEGER);
        facade.adicionarCampo("AlunoCurso", "curso_id", TipoCampo.INTEGER);

        facade.adicionarChaveEstrangeira("AlunoCurso", "aluno_id", "Aluno", "id");
        facade.adicionarChaveEstrangeira("AlunoCurso", "curso_id", "Curso", "id");
    }
}
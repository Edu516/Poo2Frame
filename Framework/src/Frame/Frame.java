package Frame;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

/**
 *
 * @author eduardo
 */
public class Frame {
    private Conexao con;

    public Frame(Conexao con) {
        this.con = con;
    }
    
    public String gerarScript(Banco banco) {
        StringBuilder script = new StringBuilder();

        // Adiciona o script de criação do banco como comentário
        script.append("-- Script para criar o banco de dados ").append(banco.getNome()).append("\n");
        script.append(criarBanco(banco.getNome())).append("\n\n");

        // Adiciona os scripts de criação das tabelas
        for (Tabela tabela : banco.getTabelas()) {
            script.append("CREATE TABLE IF NOT EXISTS ").append(tabela.getNome()).append(" (\n");
            for (Campo campo : tabela.getCampos()) {
                script.append("    ").append(campo.getNome()).append(" ").append(campo.getSqlType()).append(",\n");
            }
            for (String chaveEstrangeira : tabela.getChavesEstrangeiras()) {
                script.append("    ").append(chaveEstrangeira).append(",\n");
            }
            if (tabela.getCampos().stream().anyMatch(campo -> campo.getTipo() == TipoCampo.SERIAL)) {
                script.append("    PRIMARY KEY (id),\n");
            }
            script.deleteCharAt(script.lastIndexOf(","));
            script.append(");\n\n");
        }

        return script.toString();
    }

    public void executarScript(Connection conexao, String script) throws SQLException {
        try (var statement = conexao.createStatement()) {
            statement.execute(script);
        }
    }

    public String criarBanco(String nomeBanco) {
        StringBuilder query = new StringBuilder();
        query.append("/* Script para criar o banco de dados ").append(nomeBanco).append(" */\n");

        try (Connection conn = con.getConexaoSemDB()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();
            boolean databaseExists = false;

            while (resultSet.next()) {
                String dbName = resultSet.getString(1);
                if (dbName.equalsIgnoreCase(nomeBanco)) {
                    databaseExists = true;
                    break;
                }
            }

            resultSet.close();

            if (!databaseExists) {
                String sql = "CREATE DATABASE "+ con.getDatabase();
                executarScript(conn, sql);
                query.append("CREATE DATABASE ").append(nomeBanco).append(";\n");
            } else {
                query.append("/* Banco de dados ").append(nomeBanco).append(" já existe. Não é necessário criar. */\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query.toString();
    }

}

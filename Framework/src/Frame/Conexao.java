package Frame;

/**
 *
 * @author eduardo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private String host;
    private int port;
    private String user;
    private String pass;
    private String database;

    public Conexao(String host, int port, String user, String pass, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.database = database.toLowerCase();
    }

    public Connection conectar() throws SQLException {
        String url = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
        return DriverManager.getConnection(url, user, pass);
    }
    
    public  Connection getConexaoSemDB() throws SQLException {
        String url = String.format("jdbc:postgresql://%s:%d/", host, port);
        return DriverManager.getConnection(url, user, pass);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database.toLowerCase();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conexao{");
        sb.append("host=").append(host);
        sb.append(", port=").append(port);
        sb.append(", user=").append(user);
        sb.append(", pass=").append(pass);
        sb.append(", database=").append(database);
        sb.append('}');
        return sb.toString();
    }
    
}
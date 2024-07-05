package Frame;

/**
 *
 * @author eduardo
 */
public class Campo {
    private String nome;
    private TipoCampo tipo;

    public Campo(String nome, TipoCampo tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public TipoCampo getTipo() {
        return tipo;
    }

    public String getSqlType() {
        return tipo.getSqlType();
    }
}

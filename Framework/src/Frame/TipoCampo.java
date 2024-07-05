package Frame;

/**
 *
 * @author eduardo
 */

public enum TipoCampo {
    INTEGER("INT"),
    BIGINT("BIGINT"),
    SERIAL("SERIAL"),
    BIGSERIAL("BIGSERIAL"),
    VARCHAR("VARCHAR"),
    TEXT("TEXT"),
    BOOLEAN("BOOLEAN"),
    DATE("DATE"),
    TIMESTAMP("TIMESTAMP");

    private final String sqlType;

    TipoCampo(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getSqlType() {
        return sqlType;
    }
}

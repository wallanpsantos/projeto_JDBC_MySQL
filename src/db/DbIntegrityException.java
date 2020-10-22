package db;

public class DbIntegrityException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    /**
     * Integridade referencial para quando gerar uma execção de chave primaria com chave estrangeira
     * <p>
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DbIntegrityException(String message) {
        super(message);
    }
}

package application;

import db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramRollBack {
    public static void main(String[] args) throws SQLException, IOException {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DbConnection.getConnection();
            connection.setAutoCommit(false);

            statement = connection.createStatement();

            int primeiraColuna = statement.executeUpdate("UPDATE seller SET BaseSalary = 9000 WHERE Id = 7");

//            Integer erro = 1;
//            if (erro < 2) {
//                throw new SQLException("Erro de commit");
//            }

            int segundaColuna = statement.executeUpdate("UPDATE seller SET BaseSalary = 9000 WHERE Id = 2");

            connection.commit();

            System.out.println("Coluna 1: " + primeiraColuna);
            System.out.println("Coluna 2: " + segundaColuna);


            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new SQLException(sqlException.getMessage() + "  Erro no Rollback");
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            DbConnection.getCloseStatement(statement);
            DbConnection.getCloseConnection(connection);
        }
    }
}

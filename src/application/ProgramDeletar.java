package application;

import db.DbConnection;
import db.DbException;
import db.DbIntegrityException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramDeletar {
    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if (connection == null) {
            try {

                connection = DbConnection.getConnection();
                preparedStatement = connection.prepareStatement("DELETE FROM department WHERE id = ?",
                        Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, 2);

                Integer rowsAffected = preparedStatement.executeUpdate();

                System.out.println("DONE! Rows Affected: " + rowsAffected);

            } catch (SQLException e) {
                throw new DbIntegrityException(e.getMessage());
            } finally {
                DbConnection.getCloseStatement(preparedStatement);
                DbConnection.getCloseConnection(connection);
            }
        }
    }
}

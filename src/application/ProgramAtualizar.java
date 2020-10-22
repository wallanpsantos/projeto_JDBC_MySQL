package application;

import db.DbConnection;
import db.DbException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramAtualizar {
    public static void main(String[] args) throws IOException, SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if (connection == null) {
            try {

                connection = DbConnection.getConnection();
                preparedStatement = connection.prepareStatement("UPDATE seller " +
                                "SET  BaseSalary = BaseSalary + ? " +
                                "WHERE (DepartmentId = ?) ",
                        Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setDouble(1, 200.00);
                preparedStatement.setInt(2, 2);

                Integer rowsAffected = preparedStatement.executeUpdate();

                System.out.println("DONE! Rows Affected: " + rowsAffected);

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            } finally {
                DbConnection.getCloseStatement(preparedStatement);
                DbConnection.getCloseConnection(connection);
            }
        }
    }
}

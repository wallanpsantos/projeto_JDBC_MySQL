package application;

import db.DbConnection;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInserir {
    public static void main(String[] args) throws SQLException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        /**
         * ? no PreparedStatement a gente chama de placeholder que recebem o valor depois
         */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DbConnection.getConnection();

            preparedStatement = connection.prepareStatement("INSERT INTO SELLER (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, "Kamus Gemeos");
            preparedStatement.setString(2, "Kamus@gmail.com");
            /**
             * Para inserir data no banco de dados com campo Date usa o new java.sql.Date
             * E tem que criar um Pattern com o  SimpleDateFormat passando a data de acordo com o que a tabela espera
             * Usa o .getTime() na frente do primeiro parenteses para retorna a data para o banco de dados
             */
            preparedStatement.setDate(3, new java.sql.Date(simpleDateFormat.parse("25/11/2000").getTime()));
            preparedStatement.setDouble(4, 2500.00);
            preparedStatement.setInt(5, 2);

            /**
             * Para executar o comando SQL preparados acima
             */
            Integer rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                resultSet = preparedStatement.getGeneratedKeys();

                while (resultSet.next()) {
                    // no .getInt() passa a posição da coluna que contem o ID
                    Integer id = resultSet.getInt(1);
                    System.out.println("DONE! ID: " + id);
                }
                System.out.println("Done! Rows affected: " + rowsAffected);
            } else {
                System.out.println("No Rown Affected");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
            DbConnection.getCloseConnection(connection);
        }


    }
}

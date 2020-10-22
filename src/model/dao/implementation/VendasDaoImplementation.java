package model.dao.implementation;

import db.DbConnection;
import db.DbException;
import model.dao.VendasModelDaoInterface;
import model.entities.DepartamentoModelEntity;
import model.entities.VendasModelEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendasDaoImplementation implements VendasModelDaoInterface {

    private Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public VendasDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(VendasModelEntity vendasModelEntity) {

        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                            "VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, vendasModelEntity.getNome());
            preparedStatement.setString(2, vendasModelEntity.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(vendasModelEntity.getDataNascimento().getTime()));
            preparedStatement.setDouble(4, vendasModelEntity.getBaseSalarial());
            preparedStatement.setInt(5, vendasModelEntity.getDepartamentoModelEntity().getId());

            Integer rowsAffect = preparedStatement.executeUpdate();

            if (rowsAffect > 0) {
                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    vendasModelEntity.setId(id);
                }
                DbConnection.getCloseResultSet(resultSet);
            } else {
                throw new DbException("Nenhuma linha afetada!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
        }
    }

    @Override
    public void atualizar(VendasModelEntity vendasModelEntity) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE seller "
                    + "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
                    + "WHERE Id = ?");

            preparedStatement.setString(1, vendasModelEntity.getNome());
            preparedStatement.setString(2, vendasModelEntity.getEmail());
            preparedStatement.setDate(3, new java.sql.Date(vendasModelEntity.getDataNascimento().getTime()));
            preparedStatement.setDouble(4, vendasModelEntity.getBaseSalarial());
            preparedStatement.setInt(5, vendasModelEntity.getDepartamentoModelEntity().getId());
            preparedStatement.setInt(6, vendasModelEntity.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
        }
    }

    @Override
    public void deletarPorId(Integer id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM seller WHERE Id = ?");

            preparedStatement.setInt(1, id);

            Integer rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("O ID informado não existe na tabela ");
            } else {
                System.out.println("Foi deletado o id: " + id);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
        }
    }

    @Override
    public VendasModelEntity consultarPorId(Integer id) {

        try {
            preparedStatement = connection.prepareStatement("SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE seller.Id = ? ");

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return getVendasModelEntity(resultSet);
            }

            return null;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
            DbConnection.getCloseResultSet(resultSet);
        }

        return null;
    }

    @Override
    public List<VendasModelEntity> consultarTodos() {
        try {
            preparedStatement = connection.prepareStatement("" +
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "ORDER BY Name");

            resultSet = preparedStatement.executeQuery();

            List<VendasModelEntity> listVendasModelEntities = new ArrayList<>();
            Map<Integer, DepartamentoModelEntity> mapDepartamento = new HashMap<>();

            while (resultSet.next()) {
                DepartamentoModelEntity departamento = mapDepartamento.get(resultSet.getInt("DepartmentId"));

                if (departamento == null) {
                    departamento = getDepartamentoModelEntity(resultSet);
                    mapDepartamento.put(resultSet.getInt("DepartmentId"), departamento);
                }

                VendasModelEntity vendas = getVendasModelEntity(resultSet);
                listVendasModelEntities.add(vendas);

            }
            return listVendasModelEntities;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
            DbConnection.getCloseResultSet(resultSet);
        }

        return null;
    }

    @Override
    public List<VendasModelEntity> consultarDepartamentoPorId(DepartamentoModelEntity departamentoModelEntity) {
        try {
            preparedStatement = connection.prepareStatement("" +
                    "SELECT seller.*,department.Name as DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON seller.DepartmentId = department.Id " +
                    "WHERE DepartmentId = ? " +
                    "ORDER BY Name");

            preparedStatement.setInt(1, departamentoModelEntity.getId());

            resultSet = preparedStatement.executeQuery();

            List<VendasModelEntity> listVendasModelEntities = new ArrayList<>();
            Map<Integer, DepartamentoModelEntity> mapDepartamento = new HashMap<>();

            while (resultSet.next()) {
                DepartamentoModelEntity departamento = mapDepartamento.get(resultSet.getInt("DepartmentId"));

                if (departamento == null) {
                    departamento = getDepartamentoModelEntity(resultSet);
                    mapDepartamento.put(resultSet.getInt("DepartmentId"), departamento);
                }

                VendasModelEntity vendas = getVendasModelEntity(resultSet);
                listVendasModelEntities.add(vendas);

            }
            return listVendasModelEntities;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            DbConnection.getCloseStatement(preparedStatement);
            DbConnection.getCloseResultSet(resultSet);
        }

        return null;
    }

    private VendasModelEntity getVendasModelEntity(ResultSet resultSet) throws SQLException {

        VendasModelEntity vendasModelEntity = new VendasModelEntity();

        vendasModelEntity.setId(resultSet.getInt("Id"));
        vendasModelEntity.setNome(resultSet.getString("Name"));
        vendasModelEntity.setEmail(resultSet.getString("Email"));
        vendasModelEntity.setDataNascimento(resultSet.getDate("BirthDate"));
        vendasModelEntity.setBaseSalarial(resultSet.getDouble("BaseSalary"));
        vendasModelEntity.setDepartamentoModelEntity(getDepartamentoModelEntity(resultSet));

        return vendasModelEntity;
    }

    private DepartamentoModelEntity getDepartamentoModelEntity(ResultSet resultSet) throws SQLException {

        DepartamentoModelEntity departamentoModelEntity = new DepartamentoModelEntity();

        departamentoModelEntity.setId(resultSet.getInt("DepartmentId"));
        departamentoModelEntity.setNome(resultSet.getString("DepName"));

        return departamentoModelEntity;
    }
}

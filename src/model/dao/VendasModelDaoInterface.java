package model.dao;

import model.entities.DepartamentoModelEntity;
import model.entities.VendasModelEntity;

import java.sql.SQLException;
import java.util.List;

public interface VendasModelDaoInterface {

    void inserir(VendasModelEntity vendasModelEntity);

    void atualizar(VendasModelEntity vendasModelEntity);

    void deletarPorId(Integer id);

    VendasModelEntity consultarPorId(Integer id) throws SQLException;

    List<VendasModelEntity> consultarTodos();

    List<VendasModelEntity> consultarDepartamentoPorId(DepartamentoModelEntity departamentoModelEntity);
}

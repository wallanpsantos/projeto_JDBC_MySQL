package model.dao;

import model.entities.DepartamentoModelEntity;

import java.util.List;

public interface DepartamentoModelDaoInterface {

    void inserir(DepartamentoModelEntity departamentoModelEntity);

    void atualizar(DepartamentoModelEntity departamentoModelEntity);

    void deletarPorId(Integer id);

    DepartamentoModelEntity consultarPorId(Integer id);

    List<DepartamentoModelEntity> consultarTodos();
}

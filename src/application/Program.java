package application;

import model.dao.factory.DaoFactory;
import model.dao.implementation.VendasDaoImplementation;
import model.entities.DepartamentoModelEntity;
import model.entities.VendasModelEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) throws SQLException, IOException {

        VendasDaoImplementation vendasDaoImplementation = DaoFactory.criarVendasModelDaoInterface();

        System.out.println("==== TESTE 1 PROCURTAR POR ID VENDA ===");
        VendasModelEntity vendasModelEntity = vendasDaoImplementation.consultarPorId(2);
        System.out.println("Venda por ID: " + vendasModelEntity);


        System.out.println("\n==== TESTE 2 PROCURAR POR ID DEPARTAMENTO ===");
        DepartamentoModelEntity departamentoModelEntity = new DepartamentoModelEntity(2, null);
        List<VendasModelEntity> listaVendas = vendasDaoImplementation.consultarDepartamentoPorId(departamentoModelEntity);
        for (VendasModelEntity obj : listaVendas) {
            System.out.println(obj);
        }

        System.out.println("\n=== TESTE 3 CONSULTAR TODOS OS DEPARTAMENTOS ===");
        List<VendasModelEntity> listaVendas2 = vendasDaoImplementation.consultarTodos();
        for (VendasModelEntity obj2 : listaVendas2) {
            System.out.println(obj2);

        }


        System.out.println("\n=== TESTE 4 INSERT NOVO VENDEDOR ===");
        departamentoModelEntity = new DepartamentoModelEntity(4, "Anime");
        VendasModelEntity vendasModelEntity1 = new VendasModelEntity(null,
                "Luffy",
                "luffy@gmail.com",
                new Date(),
                30000.00,
                departamentoModelEntity);
        vendasDaoImplementation.inserir(vendasModelEntity1);
        System.out.println("Novo ID: " + vendasModelEntity1.getId() + " adicionado para " + vendasModelEntity1.getNome());
        List<VendasModelEntity> listaVendas3 = vendasDaoImplementation.consultarDepartamentoPorId(departamentoModelEntity);
        for (VendasModelEntity vendasModelEntity2 : listaVendas3) {
            System.out.println(vendasModelEntity2);
        }


        System.out.println("\n=== TESTE 5 ATUALIZANDO VENDEDOR ===");
        VendasModelEntity vendasModelEntity2 = vendasDaoImplementation.consultarPorId(2);
        vendasModelEntity2.setNome("Nami");
        vendasModelEntity2.setEmail("nami@gmail.com");
        vendasModelEntity2.setBaseSalarial(100000.00);
        vendasDaoImplementation.atualizar(vendasModelEntity2);
        System.out.println("Atualizado!!!");


        System.out.println("\n=== TESTE 6 DELETANDO VENDEDOR ===");
        Integer id = 2;
        vendasDaoImplementation.deletarPorId(id);
    }
}

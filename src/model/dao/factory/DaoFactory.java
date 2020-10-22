package model.dao.factory;

import db.DbConnection;
import model.dao.implementation.VendasDaoImplementation;

import java.io.IOException;
import java.sql.SQLException;

public class DaoFactory {

    /**
     * Metodo utilizado para não expor a implementação de VendasDao.
     * Deixando somente a interface para se chamada
     *
     * @return VendasDaoImplementation()
     */
    public static VendasDaoImplementation criarVendasModelDaoInterface() throws IOException, SQLException {
        return new VendasDaoImplementation(DbConnection.getConnection());
    }
}

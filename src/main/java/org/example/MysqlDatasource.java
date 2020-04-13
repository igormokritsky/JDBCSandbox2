package org.example;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MysqlDatasource {
    private static final Logger LOG = Logger.getLogger(MysqlDatasource.class);

    public static DataSource getDataSource() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            return (DataSource) envContext.lookup("jdbc/mysql");
        } catch (NamingException e) {
            LOG.error("Can't find data source", e);
            throw new RuntimeException("Can't find data source ", e);
        }
    }
}

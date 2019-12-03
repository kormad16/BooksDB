/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author KORNBERGERMarc
 */
public class DB_StatPool {
    
    DB_ConnectionPool conPool = DB_ConnectionPool.getInstance();
    private Map<Connection, Statement> conMap = new HashMap<>();

    private static DB_StatPool instance;
    public static synchronized DB_StatPool getInstance() {
        return instance != null ? instance : (instance = new DB_StatPool());
    }
    private DB_StatPool() {
    }

    public Statement getStatement() throws SQLException, Exception {
        Connection connection = conPool.getConnection();
        if (conMap.containsKey(connection)) return connection.createStatement();
        return conMap.get(connection);
    }

    public void releaseStatement(Statement statement) throws SQLException {
        conPool.releaseConnection(statement.getConnection());
    }
    
}

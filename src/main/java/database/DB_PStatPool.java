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
public class DB_PStatPool {
    
    DB_ConnectionPool conPool = DB_ConnectionPool.getInstance();
    
    private static DB_PStatPool instance;
    public static DB_PStatPool getInstance() {
        return instance == null ? (instance = new DB_PStatPool()) : instance;
    }
    
    private DB_PStatPool() {
        
    }
    
    private Map<Connection, Map<DB_StmtType, PreparedStatement>> conMap = new HashMap<>();
    
    public PreparedStatement getPStat(DB_StmtType stmtType) throws Exception {
        Connection connection = conPool.getConnection();
        Map<DB_StmtType, PreparedStatement> pStatMap = conMap.get(connection);
        if (pStatMap.get(stmtType) == null) {
            pStatMap = new HashMap<>();
            conMap.put(connection, pStatMap);
        }
        PreparedStatement pStat = pStatMap.get(stmtType);
        if (pStat == null) {
            pStat = connection.prepareStatement(stmtType.getSqlString());
            pStatMap.put(stmtType, pStat);
        }
        return pStat;
    }
    
    public void releasePStat(PreparedStatement pStat) throws SQLException {
        conPool.releaseConnection(pStat.getConnection());
    }
    
}

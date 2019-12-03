/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.LinkedList;
/**
 *
 * @author KORNBERGERMarc
 */
public class DB_ConnectionPool implements DB_Config {
    
    private static DB_ConnectionPool instance;
    public static synchronized DB_ConnectionPool getInstance() {
        return instance == null ? (instance = new DB_ConnectionPool()) : instance;
    }
    
    private LinkedList<Connection> conPool = new LinkedList<>();
    private static final int MAX_CON = 150;
    private int conCnt = 0;
    
    private DB_ConnectionPool () {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Loading DB Driver failed: " + ex.toString());
        }
    }
    
    public synchronized Connection getConnection() throws Exception {
        if (conPool.isEmpty()) {
            if (conCnt == MAX_CON) {
                throw new Exception("No connections available - try again later");
            }
            ++conCnt;
            return DriverManager.getConnection(DB_URL + DB_NAME,
                    DB_USER, DB_PASSWD);
        } else {
            return conPool.poll();
        }
    }
    
    public synchronized void releaseConnection(Connection connection) {
        conPool.offer(connection);
    }
    
}

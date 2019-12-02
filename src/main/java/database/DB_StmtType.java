/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author KORNBERGERMarc
 */
public enum DB_StmtType {
    
    
    GET_BOOKS_FROM_AUTHOR("SELECT * FROM books b WHERE b.author LIKE ? ");
    
    private String sqlString;
    
    private DB_StmtType(String sqlString) {
        this.sqlString = sqlString;
    }
    
    public String getSqlString() {
        return sqlString;
    }
    
}

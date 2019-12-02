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
    
    GET_ALL_BOOKS("SELECT * FROM books"
            + "INNER JOIN bookauthor USING(book_id)"
            + "INNER JOIN author a USING(author_id)"),
    GET_BOOKS_BY_TITLE("SELECT * FROM books b"
            + "INNER JOIN bookauthor USING(book_id)"
            + "INNER JOIN author a USING(author_id) WHERE b.title LIKE ? "),
    GET_BOOKS_FROM_AUTHOR("SELECT * FROM books b "
            + "INNER JOIN bookauthor USING(book_id)"
            + "INNER JOIN author a USING(author_id) WHERE a.author LIKE ? ");
    
    private String sqlString;
    
    private DB_StmtType(String sqlString) {
        this.sqlString = sqlString;
    }
    
    public String getSqlString() {
        return sqlString;
    }
    
}

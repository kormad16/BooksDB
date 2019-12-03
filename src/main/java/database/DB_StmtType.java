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

    GET_ALL_BOOKS("SELECT *, a.url AS aurl, p.url AS purl "
            + "FROM book LEFT OUTER JOIN bookauthor USING(book_id) "
            + "          LEFT OUTER JOIN author a USING(author_id) "
            + "		 LEFT OUTER JOIN publisher p USING(publisher_id) "
            + "ORDER BY book_id, rank"),
    GET_BOOKS_BY_TITLE("SELECT *, a.url AS aurl, p.url AS purl "
            + "FROM book b LEFT OUTER JOIN bookauthor USING(book_id) "
            + "            LEFT OUTER JOIN author a USING(author_id) "
            + "		   LEFT OUTER JOIN publisher p USING(publisher_id) "
            + "WHERE b.title LIKE ? "
            + "ORDER BY b.book_id, rank"),
    GET_BOOKS_FROM_AUTHOR("SELECT *, a.url AS aurl, p.url AS purl "
            + "FROM book b LEFT OUTER JOIN bookauthor USING(book_id) "
            + "            LEFT OUTER JOIN author a USING(author_id) "
            + "		   LEFT OUTER JOIN publisher p USING(publisher_id) "
            + "WHERE a.firstname || ' ' || a.lastname LIKE ? "
            + "ORDER BY b.book_id, rank");

    private String sqlString;

    private DB_StmtType(String sqlString) {
        this.sqlString = sqlString;
    }

    public String getSqlString() {
        return sqlString;
    }

}

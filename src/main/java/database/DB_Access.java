/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Book;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author KORNBERGERMarc
 */
public class DB_Access {
    
    DB_PStatPool pStatPool = DB_PStatPool.getInstance();
    
    public List<Book> getAllBooksFromAuthor() throws Exception {
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_BOOKS_FROM_AUTHOR);
        pStat.setString(1, "Sepp");
        ResultSet rs = pStat.executeQuery();
        List<Book> bookList = new ArrayList<>();
        while (rs.next()) {
            //bookList.add(new Book(rs.getInt("book_id"), title, url, 0, 0))
        }
        return null;
    }
    
}

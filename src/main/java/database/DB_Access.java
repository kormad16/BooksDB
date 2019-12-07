/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Author;
import beans.Book;
import beans.Publisher;
import java.util.List;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author KORNBERGERMarc
 */
public class DB_Access {
    
    DB_PStatPool pStatPool = DB_PStatPool.getInstance();
    DB_StatPool statPool = DB_StatPool.getInstance();
    
    public List<Book> getAllBooks() throws Exception {
//        Statement stat = statPool.getStatement();
//        ResultSet rs = stat.executeQuery(DB_StmtType.GET_ALL_BOOKS.getSqlString());
//        statPool.releaseStatement(stat);
//        return mappedBooks(rs);
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_ALL_BOOKS);
        ResultSet rs = pStat.executeQuery();
        pStatPool.releasePStat(pStat);
        return mappedBooks(rs);
    }
    
    public List<Book> getBooksFromAuthor(String search) throws Exception {
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_BOOKS_FROM_AUTHOR);
        pStat.setString(1, search);
        ResultSet rs = pStat.executeQuery();
        pStatPool.releasePStat(pStat);
        return mappedBooks(rs);
    }
    
    public List<Book> getBooksByTitle(String search) throws Exception {
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_BOOKS_BY_TITLE);
        pStat.setString(1, search);
        ResultSet rs = pStat.executeQuery();
        pStatPool.releasePStat(pStat);
        return mappedBooks(rs);
    }
    
    private List<Book> mappedBooks(ResultSet rs) throws SQLException {
        Map<Integer, Book> books = new HashMap<>();
        Map<Integer, Author> authors = new HashMap<>();
        Map<Integer, Publisher> publishers = new HashMap<>();
        while (rs.next()) {
            if (authors.get(rs.getInt("author_id")) == null)
                authors.put(rs.getInt("author_id"), new Author(
                        rs.getInt("author_id"), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getString("aurl")
                ));
            if (publishers.get(rs.getInt("publisher_id")) == null)
                publishers.put(rs.getInt("publisher_id"), new Publisher(
                        rs.getInt("publisher_id"), rs.getString("name"),
                        rs.getString("purl")
                ));
            if (books.get(rs.getInt("book_id")) == null)
                books.put(rs.getInt("book_id"), new Book(
                        rs.getInt("book_id"), rs.getString("isbn"), rs.getString("title"),
                        rs.getString("url"), rs.getDouble("price"), new LinkedList<>(), publishers.get(rs.getInt("publisher_id"))
                ));
            books.get(rs.getInt("book_id")).getAuthors().add(authors.get(rs.getInt("author_id")));
        }
        return books.keySet().stream().map(id -> books.get(id)).collect(Collectors.toList());
    }
    
}

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
    
    public List<Book> getAllBooks() throws Exception {
        PreparedStatement pStat = pStatPool.getPStat(DB_StmtType.GET_ALL_BOOKS);
        ResultSet rs = pStat.executeQuery();
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
            if (authors.get(rs.getInt("a.author_id")) == null)
                authors.put(rs.getInt("a.author_id"), new Author(
                        rs.getInt("a.author_id"), rs.getString("firstname"),
                        rs.getString("lastname"), rs.getString("a.url")
                ));
            if (publishers.get(rs.getInt("b.publisher_id")) == null)
                publishers.put(rs.getInt("b.publisher_id"), new Publisher(
                        rs.getInt("b.publisher_id"), rs.getString("p.name"),
                        rs.getString("p.url")
                ));
            if (books.get(rs.getInt("b.book_id")) == null)
                books.put(rs.getInt("b.book_id"), new Book(
                        rs.getInt("b.book_id"), rs.getString("b.isbn"), rs.getString("b.title"),
                        rs.getString("b.url"), new LinkedList<>(), publishers.get(rs.getInt("b.publisher_id"))
                ));
            books.get(rs.getInt("b.book_id")).getAuthors().add(authors.get(rs.getInt("a.author_id")));
        }
        return books.keySet().stream().map(id -> books.get(id)).collect(Collectors.toList());
    }
    
}

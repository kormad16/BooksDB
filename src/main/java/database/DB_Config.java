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
public interface DB_Config {
    
    final String DB_NAME = "BooksDB";
    final String DB_USER = "postgres";
    final String DB_PASSWD = "postgres";
    final String DB_URL = "jdbc:postgresql://localhost:5432/";
    final String DB_DRIVER = "org.postgresql.Driver";
    
}

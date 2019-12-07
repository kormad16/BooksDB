/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author KORNBERGERMarc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    
    private int id;
    private String isbn;
    private String title;
    private String url;
    private List<Author> authors;
    private Publisher publisher;
    
}

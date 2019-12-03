/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

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
public class Author {
    
    private int id;
    private String firstname;
    private String lastname;
    private String url;
    
}

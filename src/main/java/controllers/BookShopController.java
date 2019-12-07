/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import beans.Author;
import database.DB_Access;
import java.io.IOException;
import beans.Book;
import beans.Publisher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author KORNBERGERMarc
 */
@WebServlet(name = "BookShopController", urlPatterns = {"/BookShopController"})
public class BookShopController extends HttpServlet {
    
    DB_Access access = new DB_Access();
    
    private static final List<String> SORTVALUES = new ArrayList<>(Arrays.asList(new String[] {"title", "author", "price"}));

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        List<Book> books = null;
        
        try {
            books = access.getAllBooks();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
        if(request.getParameter("sort") != null && SORTVALUES.contains(request.getParameter("sort"))) {
            String sortby = request.getParameter("sort");
            request.setAttribute("sort", sortby);
            Collections.sort(books, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    if(sortby.equals("author")) {
                        for(int i = 0; i < (o1.getAuthors().size() < o2.getAuthors().size() ? o1.getAuthors().size() : o2.getAuthors().size()); i++) {
                            if(!o1.getAuthors().get(i).getLastname().equals(o2.getAuthors().get(i).getLastname()))
                                return o1.getAuthors().get(i).getLastname().compareTo(o2.getAuthors().get(i).getLastname());
                        }
                        return 0;
                    } else if(sortby.equals("title")) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    } else {
                        return (int)(o1.getPrice() - o2.getPrice());
                    }
                }
            });
        }
        
        if(request.getParameter("filter") != null) {
            List<Book> filtered = new ArrayList<>();
            for(Book b : books) {
                if(b.matchesFilter(request.getParameter("filter")))
                    filtered.add(b);
            }
            books = filtered;
            request.setAttribute("filter", request.getParameter("filter"));
        }
        
        request.setAttribute("books", books);
        
        request.getRequestDispatcher("jsps/bookShop.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

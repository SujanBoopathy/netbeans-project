/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.*;

/**
 *
 * @author Saubali
 */
public class CkServlet extends HttpServlet {
    
    private final Map books=new HashMap();
    
    public void init(){
        books.put("c-programming","12345");
        books.put("letusc++","123432");
        books.put("javabasics","128793");
        books.put("intro2dbms","120909");
    }
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CkServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CkServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        Cookie cks[]=request.getCookies();
        
        
        out.println("<html>");
        out.println("<body>");
        if(cks!=null || cks.length > 0){
            for(int i=0;i<cks.length;i++){
                out.println(cks[i].getName()+"----"+cks[i].getValue()+"<br>");
            }
        }
        else{
            out.println("No items in the cart");
            out.println("<br><a href=\"index.html\">Add more items in the cart</a>");
        }
        out.println("</body>");
        out.println("</html>");
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
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        
        String lang=request.getParameter("book");
        String isbn=books.get(lang).toString();
        
        Cookie ck=new Cookie(lang,isbn);
        ck.setMaxAge(60*60);
        response.addCookie(ck);
        
        out.println("<html>");
        out.println("<body>");
        out.println("<a href=\"index.html\">ADD MORE ITEMS IN THE CART</a><br>");
        out.println("<a href=\"CkServlet\">VIEW ITEMS IN THE CART</a><br>");
        
        out.println("</body>");
        out.println("</html>");
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

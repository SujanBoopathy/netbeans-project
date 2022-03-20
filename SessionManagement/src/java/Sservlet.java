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
import java.util.*;
import javax.servlet.http.*;


/**
 *
 * @author Saubali
 */
public class Sservlet extends HttpServlet {
    
    private final Map books=new HashMap();
    
    public void init(){
        books.put("letusc","12321");
        books.put("c++basics","199211");
        books.put("java","172622");
        books.put("networks","128921");
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
            out.println("<title>Servlet Sservlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Sservlet at " + request.getContextPath() + "</h1>");
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
        
        out.println("<html>");
        out.println("<body>");
        
        Enumeration valueNames;
        HttpSession session = request.getSession(false);
        if(session!=null)
            valueNames=session.getAttributeNames();
        else
            valueNames=null;
        
        while(valueNames!=null && valueNames.hasMoreElements()){
            String name,value;
            name=valueNames.nextElement().toString();
            value=session.getAttribute(name).toString();
            out.println("<p>"+name+"----"+value+"</p>");
        }
                
        out.println("<p><a href=\"index.html\">ADD MORE ITEMS IN THE CART</a></p>");
        
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
        HttpSession session = request.getSession(true);
        session.setAttribute(lang,books.get(lang));
        
        
        
        
        out.println("<p>you select "+lang+"</p>");
        out.println("<p>The session id is :"+session.getId()+"</p>");
        out.println("<p>The session creation time "+session.getCreationTime()+"</p>");
        out.println("<p>The session ends at "+session.getLastAccessedTime()+"</p>");
        out.println("<p>The session inactive time is "+session.getMaxInactiveInterval()+"</p>");
        
        
        out.println("<p><a href=\"index.html\">ADD MORE ITEMS IN THE CART</a></p>");
        out.println("<p><a href=\"Sservlet\">View items in the cart</a></p>");
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

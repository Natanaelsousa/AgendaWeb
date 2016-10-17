/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda_web.agendaweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author natan
 */
@WebServlet(name = "Formulario", urlPatterns = {"/Formulario"})
public class Formulario extends HttpServlet {

     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	  throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
     
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
    
    String nome = request.getParameter("nome");
    String nascimento = request.getParameter("nascimento");
    String telefone = request.getParameter("telefone");
    String email = request.getParameter("email");
    
    DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    Date dtNasc = null;
    try {
      dtNasc = formatador.parse(nascimento);
    } catch (ParseException ex) {
      //TODO: Fazer tratamento se data for invalida
    }

    // PROCESSAMENTO DOS DADOS
    
    
    // Seta os atributos para compartilhar os valores com o jsp
    // Nao confundir get/setAttribute com getParameter!!!
    request.setAttribute("id", request.getParameter("id"));
    request.setAttribute("nome", nome);
    request.setAttribute("nascimento", nascimento);
    request.setAttribute("telefone", telefone);
    request.setAttribute("email", email);
   
    
    // Encaminhamento para o processamento continuar no jsp.
    RequestDispatcher dispatcher =
	    request.getRequestDispatcher("resposta.jsp");
    dispatcher.forward(request, response);
    
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda_web.agendaweb;

import agenda_web.agenda.dao.ContatoDAO;
import agenda_web.agenda.entidade.Contato;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
     Date dtNasc;
         try {
             dtNasc = new SimpleDateFormat("yyyy-MM-dd").parse(nascimento);
         } catch (ParseException ex) {
             out.println("Erro de conversão de data");
             return;
         }
   
   

    // PROCESSAMENTO DOS DADOS //String nome, Date dtNascimento, String email, String telefone
    //inserindo contato no banco
    Contato contato = new Contato ();
    contato.setNome(nome);
    contato.setEmail(email);
    contato.setTelefone(telefone);
    contato.setDtNascimento(dtNasc);
    
    ContatoDAO sql = new ContatoDAO();
    
    sql.incluir(contato);
    
    out.println("<html>");
    out.println("<body>");
    out.println("Contato" +contato.getNome()+" adicionado com sucesso");
    out.println("</body>");
    out.println("</html>");
    
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
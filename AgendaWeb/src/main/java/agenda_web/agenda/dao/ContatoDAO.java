package agenda_web.agenda.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import agenda_web.agenda.entidade.Contato;
import agenda_web.agendaweb.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fernando.tsuda
 */
public class ContatoDAO extends ConexaoBD {

  public ContatoDAO (){
      
  }
  public Contato obterContato(long idContato) {
    PreparedStatement stmt = null;
    Connection conn = null;
    Contato p = null;

    String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
            + "FROM TB_CONTATO WHERE ID_CONTATO = ?";
    try {
      conn = obterConexao();
      stmt = conn.prepareStatement(sql);
      stmt.setLong(1, idContato);
      ResultSet resultados = stmt.executeQuery();

      while (resultados.next()) {
        Long id = resultados.getLong("ID_CONTATO");
        String nome = resultados.getString("NM_CONTATO");
        Date dataNasc = resultados.getDate("DT_NASCIMENTO");
        String email = resultados.getString("VL_EMAIL");
        String telefone = resultados.getString("VL_TELEFONE");
        p = new Contato(id, nome, dataNasc, email, telefone);
        break;
      }
    } catch (SQLException ex) {
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      // Código colocado aqui para garantir que a conexão com o banco
      // seja sempre fechada, independentemente se executado com sucesso
      // ou erro.
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return p;
  }

  public List<Contato> listar() {
    Statement stmt = null;
    Connection conn = null;

    String sql = "SELECT ID_CONTATO, NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL "
            + "FROM TB_CONTATO";

    List<Contato> lista = new ArrayList<Contato>();
    try {
      conn = obterConexao();
      stmt = conn.createStatement();
      ResultSet resultados = stmt.executeQuery(sql);

      DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");

      while (resultados.next()) {
        Long id = resultados.getLong("ID_CONTATO");
        String nome = resultados.getString("NM_CONTATO");
        Date dataNasc = resultados.getDate("DT_NASCIMENTO");
        String email = resultados.getString("VL_EMAIL");
        String telefone = resultados.getString("VL_TELEFONE");
//                System.out.println(String.valueOf(id) + ", " + nome + ", "
//                        + formatadorData.format(dataNasc) + ", " + email + ", "
//                        + telefone);
        Contato p = new Contato(id, nome, dataNasc,
                email, telefone);
        lista.add(p);
      }

    } catch (SQLException ex) {
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      // Código colocado aqui para garantir que a conexão com o banco
      // seja sempre fechada, independentemente se executado com sucesso
      // ou erro.
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    return lista;
  }

  // http://stackoverflow.com/questions/17459094/getting-id-after-insert-within-a-transaction-oracle
  // http://www.mkyong.com/jdbc/jdbc-transaction-example/
  public void incluirComTransacao(Contato contato) {
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO TB_CONTATO "
            + "(NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
            + "VALUES (?, ?, ?, ?, ?)";
    try {
      conn = obterConexao();

      conn.setAutoCommit(false); // Permite usar transacoes para multiplos comandos no banco de dados
      stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, contato.getNome());
      stmt.setDate(2, new java.sql.Date(contato.getDtNascimento().getTime()));
      stmt.setString(3, contato.getTelefone());
      stmt.setString(4, contato.getEmail());
      stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
      
      stmt.executeUpdate();
      
      // ResultSet para recuperar o ID gerado automaticamente no banco de dados.
      // Neste exemplo, o ID é gerado automaticamente.
      ResultSet generatedKeys = stmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        long idNovo = generatedKeys.getLong(1);
        ///////System.out.println("***** ID NOVO CADASTRADO: " + String.valueOf(idNovo));
        
        // Executar próximos INSERTs USANDO O ID novo.
      }
      conn.commit();
    } catch (SQLException ex) {
      try {
        // Caso ocorra algum erro, tenta desfazer todas as ações realizadas no BD.
        if (conn != null & !conn.isClosed()) {
          conn.rollback();
        }
      } catch (SQLException ex1) {
        Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex1);
      }
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      try {
        // Caso ocorra algum erro, tenta desfazer todas as ações realizadas no BD.
        if (conn != null & !conn.isClosed()) {
          conn.rollback();
        }
      } catch (SQLException ex1) {
        Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex1);
      }
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }

  public void incluir(Contato contato) {
    PreparedStatement stmt = null;
    Connection conn = null;

    String sql = "INSERT INTO TB_CONTATO "
            + "(NM_CONTATO, DT_NASCIMENTO, VL_TELEFONE, VL_EMAIL, DT_CADASTRO) "
            + "VALUES (?, ?, ?, ?, ?)";
    try {
      conn = obterConexao();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, contato.getNome());
      stmt.setDate(2, new java.sql.Date(contato.getDtNascimento().getTime()));
      stmt.setString(3, contato.getTelefone());
      stmt.setString(4, contato.getEmail());
      stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
      stmt.executeUpdate();
      //System.out.println("Registro incluido com sucesso.");

    } catch (SQLException ex) {
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
  }
}

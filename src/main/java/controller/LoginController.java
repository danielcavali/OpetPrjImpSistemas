package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//arquivo responsavel por validar o login.
@WebServlet("/login")
public class LoginController extends HttpServlet {
    //cria objeto dao que sera usado para buscar os dados no bd
    private UsuarioDAO loginDAO;

    public void init() {
        loginDAO = new UsuarioDAO();
    }
    //entrada de dados/local de onde virao
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //verificaçao
    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
//se deu boa vai para
        if (loginDAO.validacao(email, senha)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } else {//se nao deu boa vai para
            throw new Exception("login.jsp");
        }
    }

    private static final long serialVersionUID = 1l;
}
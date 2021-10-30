package controller;

import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//cadastrar usuario no bd
@WebServlet("/registro")
public class RegistroController extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
    //prepara os dados para enviar e envia
    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nomeusuario = request.getParameter("nomeusuario");
        String curso = request.getParameter("curso");
        String periodo = request.getParameter("periodo");
        String dtnasc = request.getParameter("dtnasc");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");

        Usuario usuario = new Usuario();
        usuario.setNomeusuario(nomeusuario);
        usuario.setCurso(curso);
        usuario.setPeriodo(periodo);
        usuario.setDtnasc(dtnasc);
        usuario.setSenha(senha);
        usuario.setEmail(email);

        usuarioDAO.salvarUsuario(usuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
    private static final long serialVersionUID = 1l;
}




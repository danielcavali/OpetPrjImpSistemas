package controller;

import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
// classe que recebe os metodos de enviar salvar e excluir os dados
public class UsuarioDAO {
    //funçao salvar usuario
    public void salvarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(usuario);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    //funçao que vai buscar os dados no bd e trazer para o login controller
    public boolean validacao(String email, String senha) {

        Transaction transaction = null;
        Usuario usuario = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            usuario = (Usuario) session.createQuery("FROM Usuario U WHERE U.email = :email").setParameter("email", email)
                    .uniqueResult();

            if (usuario != null && usuario.getSenha().equals(senha)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

}

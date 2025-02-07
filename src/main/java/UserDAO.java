import javax.persistence.*;
import java.util.List;

public class UserDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserDAO() {
        this.emf = Persistence.createEntityManagerFactory("UserPU");
        this.em = emf.createEntityManager();
    }

    // Method to persist a user
    public void saveUser(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    // Method to get all users
    public List<User> getAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // Method to find a user by ID
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    // Method to delete a user
    public void deleteUser(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.remove(user);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    // Close the EntityManager and EntityManagerFactory
    public void close() {
        em.close();
        emf.close();
    }
}

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // Begin transaction
            tx.begin();

            // Create a new user
            User user1 = new User("John Doe", LocalDate.of(1990, 5, 15));
            em.persist(user1);
            User user2 = new User("Outi S.", LocalDate.of(1998, 3, 15));
            em.persist(user2);

            // Commit transaction
            tx.commit();

            // Query all users
            List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
            for (User u : users) {
                System.out.println(u.getName() + " - " + u.getBirthDate());
            }

            // Remove user
            User userToRemove = em.find(User.class, 1L); // Assuming ID 1 exists
            if (userToRemove != null) {
                tx.begin();
                em.remove(userToRemove);
                tx.commit();
                System.out.println("User removed successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}

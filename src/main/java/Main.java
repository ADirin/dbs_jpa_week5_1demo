import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        try {
            // Create new users
            User user1 = new User("John Doe", LocalDate.of(1990, 5, 15));
            userDAO.saveUser(user1);
            User user2 = new User("Outi S.", LocalDate.of(1998, 3, 15));
            userDAO.saveUser(user2);

            // Query all users
            List<User> users = userDAO.getAllUsers();
            for (User u : users) {
                System.out.println(u.getName() + " - " + u.getBirthDate());
            }

            // Remove user with ID 1
            User userToRemove = userDAO.getUserById(1L);
            if (userToRemove != null) {
                userDAO.deleteUser(userToRemove);
                System.out.println("User removed successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            userDAO.close();
        }
    }
}

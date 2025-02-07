import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user_account") // Consider using a different name if "users" causes issues
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Defaults to column "name"

    @Column(name = "Birth_Date")
    private LocalDate birthDate;

    // Default constructor (required by Hibernate)
    public User() {}

    // Constructor without ID (useful for creating new instances)
    public User(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    // Constructor with ID (optional but useful for debugging)
    public User(Long id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }

    // Setters (important for Hibernate)
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

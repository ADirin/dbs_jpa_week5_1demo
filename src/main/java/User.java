import javax.persistence.*;
import java.time.LocalDate;

//to map the user class to database we use annotation after adding the core hibornate
@Entity
//add the table name annotation
@Table (name="Users")
public class User {
    // this is for primary ket
    @Id
    // telling that this is a identity key for the record so do not generate other
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @Column(name= "Name")
    private String name;

     @Column(name= "Birth_Date")
     private LocalDate birthDate;

     public User(){}

    public User (String name, LocalDate birthDate){
         this.name= name;
         this.birthDate =birthDate;

    }

    public String getName() {
         return name;
    }

    public LocalDate getBirthDate() {
         return birthDate;
    }
}

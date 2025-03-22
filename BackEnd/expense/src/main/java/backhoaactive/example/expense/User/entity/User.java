package backhoaactive.example.expense.User.entity;

import backhoaactive.example.expense.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name="username",unique = true)
    String username;

    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;

    Roles role;
    LocalDate createdAt;
    LocalDate updatedAt;

}

package backhoaactive.example.expense.User.entity;

import backhoaactive.example.expense.department.entity.Department;
import backhoaactive.example.expense.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="usertable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name="username",unique = true)
    String username;

    String password;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    Department department;

    Roles role;
    LocalDate createdAt;
    LocalDate updatedAt;

}

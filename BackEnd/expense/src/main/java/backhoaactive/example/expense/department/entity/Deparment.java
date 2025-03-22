package backhoaactive.example.expense.department.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Deparment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
}

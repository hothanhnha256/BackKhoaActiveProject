package backhoaactive.example.expense.User.dto.request;

import backhoaactive.example.expense.enums.Roles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    String password;

    String departmentId;

    LocalDate createdAt;
    LocalDate updatedAt;
}

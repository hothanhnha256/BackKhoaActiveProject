package backhoaactive.example.expense.User.dto.response;

import backhoaactive.example.expense.enums.Roles;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    String id;

    String username;

    String password;
    String firstName;
    String lastName;
    LocalDate birthDate;

    Roles role;
    LocalDate createdAt;
    LocalDate updatedAt;
}

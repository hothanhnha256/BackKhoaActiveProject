package backhoaactive.example.expense.User.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserUpdatePasswordRequest {
    @Size(min = 8, max = 30, message = "INVALID_PASS")
    String oldPassword;

    @Size(min = 8, max = 30, message = "INVALID_PASS")
    String password;
}

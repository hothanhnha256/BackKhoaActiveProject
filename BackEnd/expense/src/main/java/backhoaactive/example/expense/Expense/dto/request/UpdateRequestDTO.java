package backhoaactive.example.expense.Expense.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRequestDTO {
    String status;
}

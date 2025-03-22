package backhoaactive.example.expense.Expense.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class ExpenseRequestDTO {
    Integer page = 0;
    Integer limit = 20;
    String id = null;
    LocalDate requestCreationDate = null;
    String requestStatus = null;
    String userId = null;
}

package backhoaactive.example.expense.Expense.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class ExpenseRequestDTO {
    Integer page;
    Integer limit;
    String id;
    LocalDate requestCreationDate;
    String requestStatus;
    String userId;
}

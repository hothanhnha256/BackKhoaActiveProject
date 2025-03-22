package backhoaactive.example.expense.Expense.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateRequestDTO {
    String typeExpense;
    Double amount;
}

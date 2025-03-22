package backhoaactive.example.expense.Expense.dto.response;

import backhoaactive.example.expense.Expense.model.Record;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Setter
public class ExpensesResponseDTO {
    List<Record> expenses;
    Integer page;
    Integer total;
    Integer limit;
}

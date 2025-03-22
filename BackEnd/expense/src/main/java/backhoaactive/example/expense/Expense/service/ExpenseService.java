package backhoaactive.example.expense.Expense.service;


import backhoaactive.example.expense.Expense.dto.request.CreateRequestDTO;
import backhoaactive.example.expense.Expense.dto.request.ExpenseRequestDTO;
import backhoaactive.example.expense.Expense.model.Record;
import backhoaactive.example.expense.Expense.repository.ExpenseRequestRepository;
import backhoaactive.example.expense.enums.Process;
import backhoaactive.example.expense.enums.TypeExpense;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRequestRepository repository;

    public void createExpenseRequest(Record record) {
        System.out.print(record.toString());
        this.repository.save(record);
    }

    public void updateExpenseRequest(Record updatedRecord) {

        Optional<Record> oldRecordOptional = repository.findById(updatedRecord.getId());
        if (oldRecordOptional.isPresent()) {
            Record oldRecord = oldRecordOptional.get();
            oldRecord.setProcess(updatedRecord.getProcess());
            repository.save(oldRecord);
        } else {
            //TODO: throw not found here
        }
    }

    public Page<Record> getExpenses(ExpenseRequestDTO dto) {
        Page<Record> page = this.repository.findWithFilters(dto.getRequestStatus() != null ? Process.valueOf(dto.getRequestStatus()) : null, dto.getUserId(), dto.getId(), PageRequest.of(dto.getPage(), dto.getLimit()));
        return page;
    }


}

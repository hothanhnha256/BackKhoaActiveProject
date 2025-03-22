package backhoaactive.example.expense.Expense.repository;

import backhoaactive.example.expense.Expense.model.Record;
import backhoaactive.example.expense.enums.Process;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ExpenseRequestRepository extends JpaRepository<Record, String>, JpaSpecificationExecutor<Record> {
    static Specification<Record> withFilters(Process process, String userId, String id) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (process != null) {
                System.out.println(process.toString()); // Safe since we check for null
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("process"), process));
            }

            if (userId != null && !userId.trim().isEmpty()) {
                System.out.println(userId);
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("employeeId"), userId));
            }

            if (id != null && !id.trim().isEmpty()) {
                System.out.println(id);
                try {
                    UUID uuid = UUID.fromString(id);
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("id"), uuid));
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID: " + id); // Log an invalid UUID instead of crashing
                }
            }

            return predicate;
        };
    }

    default Page<Record> findWithFilters(Process process, String userId, String id, Pageable pageable) {
        return findAll(withFilters(process, userId, id), pageable);
    }
}

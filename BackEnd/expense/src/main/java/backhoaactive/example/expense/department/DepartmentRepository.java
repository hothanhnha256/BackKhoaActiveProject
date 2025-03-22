package backhoaactive.example.expense.department;

import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

    Department findByName(String name);
    Boolean existsByName(String name);

    Department findAllById(String id);

}

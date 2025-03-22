package backhoaactive.example.expense.User.repository;

import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    List<User> findAllByDepartment(Department department);
}

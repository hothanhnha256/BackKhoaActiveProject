package backhoaactive.example.expense.Auth.repository;

import backhoaactive.example.expense.Auth.entity.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidateTokenRepository extends JpaRepository<InvalidateToken, String> {}

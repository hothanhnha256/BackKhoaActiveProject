package backhoaactive.example.expense.admin;

import backhoaactive.example.expense.User.dto.request.UserCreationRequest;
import backhoaactive.example.expense.User.dto.response.UserResponse;
import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.User.mapper.UserMapper;
import backhoaactive.example.expense.User.repository.UserRepository;
import backhoaactive.example.expense.enums.Roles;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminService {

    UserMapper userMapper;
    UserRepository userRepository;

    public UserResponse createAdmin(UserCreationRequest userCreationRequest) {
        User user= userMapper.toUser(userCreationRequest);
        user.setRole(Roles.ADMIN);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        return userMapper.toUserResponse(userRepository.save(user));
    }
}

package backhoaactive.example.expense.User.services;

import backhoaactive.example.expense.User.dto.request.UserCreationRequest;
import backhoaactive.example.expense.User.dto.request.UserUpdatePasswordRequest;
import backhoaactive.example.expense.User.dto.response.UserResponse;
import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.User.mapper.UserMapper;
import backhoaactive.example.expense.User.repository.UserRepository;
import backhoaactive.example.expense.exception.AppException;
import backhoaactive.example.expense.exception.ErrorCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    //note them department id
    @Test
    public void testCreateUser() {
        UserCreationRequest request = new UserCreationRequest();
        request.setUsername("testuser");
        request.setPassword("123");

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("123");

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername("testuser");

        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(userMapper.toUser(request)).thenReturn(user);
        when(passwordEncoder.encode("123")).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse result = userService.createRequest(request);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testCreateRequest_UserExists() {
        UserCreationRequest request = new UserCreationRequest();
        request.setUsername("testuser");
        request.setPassword("123");

        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        AppException exception = assertThrows(AppException.class, () -> userService.createRequest(request));
        assertEquals(ErrorCode.USER_EXISTED, exception.getErrorCode());
    }
    @Test
    public void testGetUser() {
        String userId = "user1";
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername("testuser");

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse result = userService.getUser(userId);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }
    @Test
    public void testGetUser_UserNotFound() {
        String userId = "user1";

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> userService.getUser(userId));
        assertEquals(ErrorCode.USER_INVALID, exception.getErrorCode());
    }
    @Test
    public void testChangePasswordUser() {
        String userId = "user1";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        UserUpdatePasswordRequest passwordRequest = new UserUpdatePasswordRequest();
        passwordRequest.setOldPassword(oldPassword);
        passwordRequest.setPassword(newPassword);

        User user = new User();
        user.setId(userId);
        user.setPassword("encodedOldPassword");

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername("testuser");

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(passwordEncoder.matches(oldPassword, "encodedOldPassword")).thenReturn(true);
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse result = userService.changePasswordUser(userId, passwordRequest);

        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }
    @Test
    public void testChangePasswordUser_IncorrectOldPassword() {
        String userId = "user1";
        String oldPassword = "wrongOldPassword";
        String newPassword = "newPassword";
        UserUpdatePasswordRequest passwordRequest = new UserUpdatePasswordRequest();
        passwordRequest.setOldPassword(oldPassword);
        passwordRequest.setPassword(newPassword);

        User user = new User();
        user.setId(userId);
        user.setPassword("encodedOldPassword");

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        when(passwordEncoder.matches(oldPassword, "encodedOldPassword")).thenReturn(false);

        AppException exception = assertThrows(AppException.class, () -> userService.changePasswordUser(userId, passwordRequest));
        assertEquals(ErrorCode.INCORRECT_PASS, exception.getErrorCode());
    }

    // Test case for deleting user
    @Test
    public void testDeleteUser() {
        String userId = "user1";
        User user = new User();
        user.setId(userId);
        user.setUsername("testuser");

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user));
        doNothing().when(userRepository).delete(user);

        String result = userService.deleteUser(userId);

        assertEquals("Success delete user : testuser", result);
        verify(userRepository, times(1)).delete(user);
    }

    // Test case for deleting user that doesn't exist
    @Test
    public void testDeleteUser_UserNotFound() {
        String userId = "user1";

        when(userRepository.findById(userId)).thenReturn(java.util.Optional.empty());

        AppException exception = assertThrows(AppException.class, () -> userService.deleteUser(userId));
        assertEquals(ErrorCode.USER_INVALID, exception.getErrorCode());
    }
}

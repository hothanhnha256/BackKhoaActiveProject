package backhoaactive.example.expense.admin;


import backhoaactive.example.expense.User.dto.request.UserCreationRequest;
import backhoaactive.example.expense.User.dto.response.UserResponse;
import backhoaactive.example.expense.dtoGlobal.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping()
    public ApiResponse<UserResponse> createAdmin(@RequestBody UserCreationRequest userCreationRequest) {
        log.info("Creating Admin");
        return ApiResponse.<UserResponse>builder()
                .data(adminService.createAdmin(userCreationRequest))
                .success(true)
                .status(HttpStatus.OK)
                .build();
        }

}

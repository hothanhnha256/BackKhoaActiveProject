package backhoaactive.example.expense.department;


import backhoaactive.example.expense.User.dto.request.UserCreationRequest;
import backhoaactive.example.expense.department.dto.request.DepartmentCreationRequest;
import backhoaactive.example.expense.department.dto.response.DepartmentResponse;
import backhoaactive.example.expense.dtoGlobal.response.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasRole('ADMIN')")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ApiResponse<DepartmentResponse> createDepartment(@RequestBody DepartmentCreationRequest departmentCreationRequest) {
        return ApiResponse.<DepartmentResponse>builder()
                .data(departmentService.createDepartment(departmentCreationRequest))
                .status(HttpStatus.OK)
                .success(true)
                .message("OKE MY FEN")
                .build();
    }

    @GetMapping()
    public ApiResponse<Page<DepartmentResponse>> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String name
    ) {
        return ApiResponse.<Page<DepartmentResponse>>builder()
                .data(departmentService.getDepartments(page,size,name))
                .status(HttpStatus.OK)
                .success(true)
                .message("OKE MY FEN")
                .build();
    }

    @DeleteMapping("/{departmentName}")
    public ApiResponse<String> deleteDepartment(@PathVariable String departmentName) {
        return ApiResponse.<String>builder()
                .data(departmentService.deleteDepartment(departmentName))
                .status(HttpStatus.OK)
                .success(true)
                .message("OKE MY FEN")
                .build();
    }

}

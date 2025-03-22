package backhoaactive.example.expense.Expense.controller;

import backhoaactive.example.expense.Expense.dto.request.CreateRequestDTO;
import backhoaactive.example.expense.Expense.dto.request.ExpenseRequestDTO;
import backhoaactive.example.expense.Expense.dto.request.UpdateRequestDTO;
import backhoaactive.example.expense.Expense.dto.response.ExpensesResponseDTO;
import backhoaactive.example.expense.Expense.model.Record;
import backhoaactive.example.expense.Expense.service.ExpenseService;
import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.User.repository.UserRepository;
import backhoaactive.example.expense.User.services.UserService;
import backhoaactive.example.expense.enums.Roles;
import backhoaactive.example.expense.enums.TypeExpense;
import backhoaactive.example.expense.exception.ApiResponse;
import backhoaactive.example.expense.exception.AppException;
import backhoaactive.example.expense.exception.ErrorCode;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService service;
    private final UserService userService;

    @GetMapping
    public ApiResponse<ExpensesResponseDTO> getAllRequestForUser(ExpenseRequestDTO dto) {
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userService.getUserByUserName(name);
        System.out.println("Username + " + user.getUsername() + " " + user.getId());

        List<Record> records = null;
        Page<Record> pageRecord = null;
        Roles userRole = user.getRole();
        // chia role
        if (userRole == Roles.USER) {
            // lấy của chính nó
            dto.setUserId(user.getId()); // add employeeId
            pageRecord = this.service.getExpenses(dto);
            records = pageRecord.getContent();
        } else if (userRole == Roles.FINANCE_MANAGER) {
            // lấy hết
            pageRecord = this.service.getExpenses(dto);
            records = pageRecord.getContent();

        } else if (userRole == Roles.MANAGER) {
            // lấy của deparment của nó
            pageRecord = this.service.getExpenses(dto);
            records = pageRecord.getContent();
        } else {
            throw new AppException(ErrorCode.NOT_ALLOWED);
        }

        ExpensesResponseDTO responseDTO = ExpensesResponseDTO.builder()
                .expenses(records)
                .total(pageRecord.getTotalPages())
                .page(pageRecord.getNumber() + 1)
                .limit(pageRecord.getSize())
                .build();
        ApiResponse<ExpensesResponseDTO> response = ApiResponse.<ExpensesResponseDTO>builder()
                .code(200)
                .message("Get expenses successfully")
                .result(responseDTO)
                .build();
        return response;
    }

    @PostMapping
    public ApiResponse<Record> createExpenseRequest(@RequestBody CreateRequestDTO dto) {

        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userService.getUserByUserName(name);
        System.out.println("user " + user.getRole());

        if (user.getRole() == Roles.USER) {
            Record savedRecord = Record.builder()
                    .typeExpense(TypeExpense.valueOf(dto.getTypeExpense()))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .amount(dto.getAmount())
                    .employeeId(user.getId())
                    .build();
            this.service.createExpenseRequest(savedRecord);
            System.out.println("here");

            ApiResponse<Record> response = ApiResponse.<Record>builder()
                    .code(201)
                    .message("create request successfully")
                    .result(null)
                    .build();
            return response;
        } else {
            ApiResponse<Record> response = ApiResponse.<Record>builder()
                    .code(400)
                    .message("Not allowed")
                    .result(null)
                    .build();
            return response;
        }
    }

    @PatchMapping("/{requestId}")
    public ApiResponse<String> createExpenseRequest(@RequestHeader("Authorization") String authHeader, @RequestBody UpdateRequestDTO dto) {
        String token = authHeader.replace("Bearer ", "");
        System.out.print("token: " + token);
//        this.service.updateExpenseRequest();

        ApiResponse<String> response = ApiResponse.<String>builder()
                .result("ahihi") // Assuming `result` is a valid builder method
                .build();
        return response;
    }
}

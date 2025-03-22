package backhoaactive.example.expense.department;

import backhoaactive.example.expense.User.dto.request.UserCreationRequest;
import backhoaactive.example.expense.User.dto.response.UserResponse;
import backhoaactive.example.expense.User.entity.User;
import backhoaactive.example.expense.User.mapper.UserMapper;
import backhoaactive.example.expense.User.repository.UserRepository;
import backhoaactive.example.expense.department.dto.request.DepartmentCreationRequest;
import backhoaactive.example.expense.department.dto.response.DepartmentResponse;
import backhoaactive.example.expense.department.entity.Department;
import backhoaactive.example.expense.department.mapper.DepartmentMapper;
import backhoaactive.example.expense.enums.Roles;
import backhoaactive.example.expense.exception.AppException;
import backhoaactive.example.expense.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentResponse createDepartment(DepartmentCreationRequest departmentCreationRequest){

        Department department = departmentMapper.toDepartment(departmentCreationRequest);
        if(departmentRepository.existsByName(departmentCreationRequest.getName())){
            throw new AppException(ErrorCode.INVALID_DEPARTMENT_NAME);
        }
        try {
            department= departmentRepository.save(department);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
        return departmentMapper.toDepartmentResponse(department);
    }

    public Page<DepartmentResponse> getDepartments(int page, int size,String name){
        Pageable pageable = PageRequest.of(page, size);
        return departmentRepository.findAll(pageable).map(departmentMapper::toDepartmentResponse);
    }

    public String deleteDepartment(String name){
        if(departmentRepository.existsByName(name)){
            throw new AppException(ErrorCode.INVALID_DEPARTMENT_NAME);
        }
        Department department = departmentRepository.findByName(name);
        departmentRepository.delete(department);
        return "Department deleted successfully";
    }

}

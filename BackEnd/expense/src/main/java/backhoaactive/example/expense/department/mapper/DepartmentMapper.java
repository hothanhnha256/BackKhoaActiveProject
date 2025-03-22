package backhoaactive.example.expense.department.mapper;

import backhoaactive.example.expense.department.dto.request.DepartmentCreationRequest;
import backhoaactive.example.expense.department.dto.response.DepartmentResponse;
import backhoaactive.example.expense.department.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department toDepartment(DepartmentCreationRequest departmentCreationRequest);

    DepartmentResponse toDepartmentResponse(Department department);
}

package backhoaactive.example.expense.User.mapper;

import backhoaactive.example.expense.User.dto.request.ProfileCreationRequest;
import backhoaactive.example.expense.User.dto.request.UserCreationRequest;
import backhoaactive.example.expense.User.dto.request.UserUpdateRequest;
import backhoaactive.example.expense.User.dto.response.UserResponse;
import backhoaactive.example.expense.User.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User toUser(UserCreationRequest user);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    ProfileCreationRequest toProfileCreationRequest(User user);

}

package backhoaactive.example.expense.Auth.controller;

import backhoaactive.example.expense.Auth.dto.request.AuthenticationRequest;
import backhoaactive.example.expense.Auth.dto.request.IntrospectRequest;
import backhoaactive.example.expense.Auth.dto.request.LogoutRequest;
import backhoaactive.example.expense.Auth.dto.request.RefreshRequest;
import backhoaactive.example.expense.Auth.dto.response.AuthenticationResponse;
import backhoaactive.example.expense.Auth.dto.response.IntrospectResponse;
import backhoaactive.example.expense.Auth.service.AuthenticationService;
import backhoaactive.example.expense.dtoGlobal.response.ApiResponse;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        IntrospectResponse result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        AuthenticationResponse authenticationResponse = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(authenticationResponse)
                .build();
    }
}

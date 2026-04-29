package kr.fintarget.api.domain.auth;
import kr.fintarget.api.common.ApiResponse;
import kr.fintarget.api.domain.auth.dto.AuthResponse;
import kr.fintarget.api.domain.auth.dto.KakaoLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping("/kakao/login")
    public ResponseEntity<ApiResponse<AuthResponse>> kakaoLogin(
            @RequestBody KakaoLoginRequest request) {
        AuthResponse response = AuthResponse.builder()
                .isNewUser(true)
                .userId("test-uuid-1234")
                .accessToken("temp-access-token")
                .refreshToken("temp-refresh-token")
                .expiresIn(3600)
                .provider("KAKAO")
                .build();
        return ResponseEntity.status(201).body(ApiResponse.created(response));
    }
}

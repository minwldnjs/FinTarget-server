package kr.fintarget.api.domain.auth;
import kr.fintarget.api.common.ApiResponse;
import kr.fintarget.api.domain.auth.dto.AuthResponse;
import kr.fintarget.api.domain.auth.dto.KakaoLoginRequest;
import kr.fintarget.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    @PostMapping("/kakao/login")
    public ResponseEntity<ApiResponse<AuthResponse>> kakaoLogin(
            @RequestBody KakaoLoginRequest request) {
        String userId = UUID.randomUUID().toString();
        String accessToken = jwtUtil.generateToken(userId, "KAKAO");
        String refreshToken = jwtUtil.generateRefreshToken(userId);
        AuthResponse response = AuthResponse.builder()
                .isNewUser(true)
                .userId(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(3600)
                .provider("KAKAO")
                .build();
        return ResponseEntity.status(201).body(ApiResponse.created(response));
    }
}

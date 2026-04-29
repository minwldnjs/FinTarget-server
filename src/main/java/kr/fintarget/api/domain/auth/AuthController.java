package kr.fintarget.api.domain.auth;
import kr.fintarget.api.common.ApiResponse;
import kr.fintarget.api.domain.auth.dto.AppleLoginRequest;
import kr.fintarget.api.domain.auth.dto.AuthResponse;
import kr.fintarget.api.domain.auth.dto.KakaoLoginRequest;
import kr.fintarget.api.domain.auth.dto.NaverLoginRequest;
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
        AuthResponse response = AuthResponse.builder()
                .isNewUser(true)
                .userId(userId)
                .accessToken(jwtUtil.generateToken(userId, "KAKAO"))
                .refreshToken(jwtUtil.generateRefreshToken(userId))
                .expiresIn(3600)
                .provider("KAKAO")
                .build();
        return ResponseEntity.status(201).body(ApiResponse.created(response));
    }
    @PostMapping("/naver/login")
    public ResponseEntity<ApiResponse<AuthResponse>> naverLogin(
            @RequestBody NaverLoginRequest request) {
        String userId = UUID.randomUUID().toString();
        AuthResponse response = AuthResponse.builder()
                .isNewUser(true)
                .userId(userId)
                .accessToken(jwtUtil.generateToken(userId, "NAVER"))
                .refreshToken(jwtUtil.generateRefreshToken(userId))
                .expiresIn(3600)
                .provider("NAVER")
                .build();
        return ResponseEntity.status(201).body(ApiResponse.created(response));
    }
    @PostMapping("/apple/login")
    public ResponseEntity<ApiResponse<AuthResponse>> appleLogin(
            @RequestBody AppleLoginRequest request) {
        String userId = UUID.randomUUID().toString();
        AuthResponse response = AuthResponse.builder()
                .isNewUser(true)
                .userId(userId)
                .accessToken(jwtUtil.generateToken(userId, "APPLE"))
                .refreshToken(jwtUtil.generateRefreshToken(userId))
                .expiresIn(3600)
                .provider("APPLE")
                .build();
        return ResponseEntity.status(201).body(ApiResponse.created(response));
    }
    @PostMapping("/token/refresh")
    public ResponseEntity<ApiResponse<?>> refreshToken(
            @RequestBody java.util.Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        if (!jwtUtil.validateToken(refreshToken)) {
            return ResponseEntity.status(401).body(ApiResponse.error(401, "Invalid refresh token"));
        }
        String userId = jwtUtil.getUserId(refreshToken);
        String newAccessToken = jwtUtil.generateToken(userId, "REFRESH");
        return ResponseEntity.ok(ApiResponse.ok(java.util.Map.of(
                "accessToken", newAccessToken,
                "expiresIn", 3600
        )));
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout() {
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}

package lucent.backend.controller;

import lombok.RequiredArgsConstructor;
import lucent.backend.Entity.User;
import lucent.backend.Security.AppUserPrincipal;
import lucent.backend.Security.CurrentUser;
import lucent.backend.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CurrentUser currentUser;

    @GetMapping("/login-url")
    public Map<String,String> login(){
        return Map.of("url","/oauth2/authorization/github");
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(){
        AppUserPrincipal principal = currentUser.require();
        User user = principal.getUser();
        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getGithubId(),
                user.getDisplayName(),
                user.getGithubUsername(),
                user.getAvatarUrl()
                ));

    }


}

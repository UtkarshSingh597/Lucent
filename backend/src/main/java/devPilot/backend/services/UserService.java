package devPilot.backend.services;

import devPilot.backend.Entity.User;
import devPilot.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    public final TextEncryptor tokenEncryptor;

    @Transactional(readOnly = true)
    public User requiredById(UUID id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found with id: " + id));
    }
    public String decryptAccessToken(User user){
        return tokenEncryptor.decrypt(user.getAccessToken());
    }
}

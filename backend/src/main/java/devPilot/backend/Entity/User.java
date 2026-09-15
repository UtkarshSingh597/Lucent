package devPilot.backend.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, name = "github_id")
    private Long githubId;

    @Column(nullable = false, unique = true, name = "github_username", length = 100 )
    private String githubUsername;

    @Column(name = "display_name", length = 200, nullable = false)
    private String displayName;

    @Column(name="avatar_url", length = 500)
    private String avatarUrl;

    @Column(name = "access_token", length = 500)
    private String accessToken;

    @Column(name = "token_scope", length = 500)
    private String tokenScopes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreated(){
        if(createdAt == null){
            createdAt= Instant.now();
        }
    }
}

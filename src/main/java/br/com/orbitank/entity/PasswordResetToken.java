package br.com.orbitank.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_password_reset_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetToken {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_reset_token_seq"
    )
    @SequenceGenerator(
            name = "password_reset_token_seq",
            sequenceName = "seq_password_reset_token",
            allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "operational_user_id", nullable = false)
    private OperationalUser operationalUser;

    @Column(name = "code_hash")
    private String codeHash;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "used_at")
    private LocalDateTime usedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
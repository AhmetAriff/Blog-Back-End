package dev.arif.blogbackend.Register.Token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    Optional <VerificationToken> findByToken(String token);
}

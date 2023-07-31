package dev.arif.blogbackend.Register;

import dev.arif.blogbackend.Exception.InvalidTokenException;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import dev.arif.blogbackend.Register.Token.VerificationToken;
import dev.arif.blogbackend.Register.Token.VerificationTokenRepository;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/register")
public class RegistrationController {
    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository verificationTokenRepository;
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("verifyEmail")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token){
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(()->new ResourceNotFoundException(
                        "invalid verification token"
                ));
        if(verificationToken.getUser().isEnabled()){
            throw new RuntimeException("This account has already been verified, please, login.");}
        if (userService.validateVerificationToken(token)){
            return ResponseEntity.ok("mail is verified");
        }
        throw new InvalidTokenException();
    }
    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}

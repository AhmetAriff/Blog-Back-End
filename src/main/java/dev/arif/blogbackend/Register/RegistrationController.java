package dev.arif.blogbackend.Register;

import dev.arif.blogbackend.Register.Token.VerificationTokenRepository;
import dev.arif.blogbackend.User.User;
import dev.arif.blogbackend.User.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest registrationRequest, final HttpServletRequest request){
        User user = userService.registerUser(registrationRequest);
        publisher.publishEvent(new RegistrationCompleteEvent(user,applicationUrl(request)));
        return ResponseEntity.status(HttpStatus.CREATED).build();//TODO frontende tarafında check your mail yazdırılacak
    }
    @GetMapping("verify-mail")
    public ResponseEntity<?> verifyEmail(){
        return null;//TODO
    }

    public String applicationUrl(HttpServletRequest request) {
        return "https://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}

package dev.arif.blogbackend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.GONE)
public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(){
        super("token already expired");
    }
}

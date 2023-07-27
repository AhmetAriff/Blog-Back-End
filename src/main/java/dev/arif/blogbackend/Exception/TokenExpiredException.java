package dev.arif.blogbackend.Exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(){
        super("token already expired");
    }
}

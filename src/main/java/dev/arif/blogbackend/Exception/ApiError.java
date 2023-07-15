package dev.arif.blogbackend.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class ApiError {

    private String path;
    private String message;
    private int statusCode;
    private LocalDateTime localDateTime;
}

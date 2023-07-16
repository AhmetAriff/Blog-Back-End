package dev.arif.blogbackend.Subject;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSubjectRequest {
    @NotBlank(message = "subject name can not be null")
    private String subjectName;
}

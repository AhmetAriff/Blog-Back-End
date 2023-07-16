package dev.arif.blogbackend.Subject;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubjectRequest {
    @NotNull(message = "subject name cont not be null")
    private String subjectName;
}

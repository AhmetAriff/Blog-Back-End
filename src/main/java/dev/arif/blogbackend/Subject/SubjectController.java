package dev.arif.blogbackend.Subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    ResponseEntity<?> addSubject (@RequestBody CreateSubjectRequest createSubjectRequest){
        subjectService.addSubject(createSubjectRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    ResponseEntity<List<SubjectDto>> getAllSubjects(){
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
    @GetMapping("{subjectId}")
    ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long subjectId){
        return ResponseEntity.ok(subjectService.getSubjectById(subjectId));
    }

    @GetMapping("{subjectName}")
    ResponseEntity<SubjectDto> getSubjectByName(@PathVariable String subjectName){
        return ResponseEntity.ok(subjectService.getSubjectBySubjectName(subjectName));
    }
    @DeleteMapping("{subjectId}")
    ResponseEntity<?> deleteSubjectById(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

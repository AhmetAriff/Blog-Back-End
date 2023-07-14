package dev.arif.blogbackend.Subject;

import java.util.List;

public interface SubjectService {

    void addSubject(CreateSubjectRequest createSubjectRequest);

    SubjectDto getSubjectById(Long subjectId);

    SubjectDto getSubjectBySubjectName(String subjectName);

    List<SubjectDto> getAllSubjects();

    void deleteSubject(Long subjectId);
}

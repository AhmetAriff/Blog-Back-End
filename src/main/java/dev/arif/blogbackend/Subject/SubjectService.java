package dev.arif.blogbackend.Subject;

import java.util.List;

public interface SubjectService {

    void addSubject(String subjectName);

    SubjectDto getSubjectById(Long subjectId);

    SubjectDto getSubjectBySubjectName(String subjectName);

    List<SubjectDto> getAllSubjects();
}

package dev.arif.blogbackend.Subject;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    SubjectDto subjectToSubjectDto(javax.security.auth.Subject subject);
    List<SubjectDto> subjectListToSubjectDtoList(List<Subject> subjectList);
    javax.security.auth.Subject createSubjectRequestToSubject(CreateSubjectRequest subjectRequest);
}

package dev.arif.blogbackend.Subject;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.security.auth.Subject;
import java.util.List;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);
    SubjectDto subjectToSubjectDto(Subject subject);
    List<SubjectDto> subjectListToSubjectDtoList(List<Subjcet> subjcetList);
    Subject createSubjectRequestToSubject(CreateSubjectRequest subjectRequest);
}

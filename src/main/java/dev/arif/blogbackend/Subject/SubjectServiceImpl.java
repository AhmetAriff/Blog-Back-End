package dev.arif.blogbackend.Subject;

import dev.arif.blogbackend.Exception.DuplicateResourceException;
import dev.arif.blogbackend.Exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    @Override
    public void addSubject(CreateSubjectRequest createSubjectRequest) {
        String subjectName = createSubjectRequest.getSubjectName();
        if(subjectRepository.existsSubjectBySubjectName(subjectName)){
            throw new DuplicateResourceException(
                    "subject [%s] is already registered".formatted(subjectName)
            );
        }

        subjectRepository.save(
                subjectMapper.createSubjectRequestToSubject(createSubjectRequest)
        );
    }

    @Override
    public SubjectDto getSubjectById(Long subjectId) {
           return subjectMapper.subjectToSubjectDto(
                   subjectRepository.findSubjectBySubjectId(subjectId)
                           .orElseThrow(()-> new ResourceNotFoundException(
                                   "subject with id [%s] not found".formatted(subjectId)
                           ))
           );
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
        return subjectMapper.subjectListToSubjectDtoList(
                subjectRepository.findAll()
        );
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.delete(
            subjectRepository.findSubjectBySubjectId(subjectId)
                    .orElseThrow(()->new ResourceNotFoundException(
                            "subject with id [%s] not found".formatted(subjectId)
                    ))
        );
    }
}

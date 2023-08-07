package dev.arif.blogbackend.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    boolean existsSubjectBySubjectName(String subjectName);

    Optional<Subject> findSubjectBySubjectId(Long subjectId);
}

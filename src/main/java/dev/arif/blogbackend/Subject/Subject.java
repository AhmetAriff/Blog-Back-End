package dev.arif.blogbackend.Subject;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.arif.blogbackend.Blog.Blog;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Subject")
public class Subject {
    @Id
    @SequenceGenerator(
            name = "subject_id_seq",
            sequenceName = "subject_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_id_seq"
    )
    private Long subjectId;

    @Column(name = "subject_name",nullable = false,unique = true )
    private String subjectName;

    @OneToMany(mappedBy = "subject",cascade=CascadeType.ALL)
    private List<Blog> blogs;
}

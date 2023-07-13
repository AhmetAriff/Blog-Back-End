package dev.arif.blogbackend.Subject;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Subject")
public class Subjcet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;

    @Column(name = "subject_name",nullable = false,unique = true )
    private String subjectName;
}

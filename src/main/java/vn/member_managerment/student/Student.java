package vn.member_managerment.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "student_name", nullable = false, length = 50)
    private String student_name;

    @Column(name = "student_code",nullable = false, length = 50)
    private String student_code;

    @JsonManagedReference
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private StudentInfo studentInfo;
}
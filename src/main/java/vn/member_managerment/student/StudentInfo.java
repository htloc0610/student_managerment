package vn.member_managerment.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "student_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id", nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "average_score")
    private Double averageScore;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
}
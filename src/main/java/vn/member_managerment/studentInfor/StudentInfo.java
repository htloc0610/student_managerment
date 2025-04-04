package vn.member_managerment.studentInfor;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import vn.member_managerment.student.Student;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
}
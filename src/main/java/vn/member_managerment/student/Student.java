package vn.member_managerment.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

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

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", nullable = false, updatable = false)
    private java.util.Date createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    private java.util.Date updatedAt;
}
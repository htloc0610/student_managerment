package vn.member_managerment.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Lấy tất cả sinh viên
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Lấy sinh viên theo ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Thêm sinh viên mới
    public Student createStudent(Student student) {
        System.out.println("Creating student: " + student);
        return studentRepository.save(student);
    }

    // Cập nhật thông tin sinh viên
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setStudent_name(studentDetails.getStudent_name());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }
}
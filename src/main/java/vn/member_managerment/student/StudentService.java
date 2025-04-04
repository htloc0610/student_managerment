package vn.member_managerment.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.member_managerment.studentInfor.StudentInfoService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentInfoService studentInfoService;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        System.out.println("Creating student: " + student);
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với ID: " + id));

        if (studentDetails.getStudent_name() != null && !studentDetails.getStudent_name().isEmpty()) {
            student.setStudent_name(studentDetails.getStudent_name());
        }

        if (studentDetails.getStudent_code() != null && !studentDetails.getStudent_code().isEmpty()) {
            student.setStudent_code(studentDetails.getStudent_code());
        }

        student.setStudentInfo(studentInfoService.updateStudentInfo(student, studentDetails.getStudentInfo()));

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        studentRepository.delete(student);
    }

}
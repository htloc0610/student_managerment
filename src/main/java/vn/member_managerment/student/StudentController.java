package vn.member_managerment.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.member_managerment.utils.ResponseHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseHandler.generateResponse("Students retrieved successfully", HttpStatus.OK, students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(value ->
                ResponseHandler.generateResponse("Student found", HttpStatus.OK, value)
        ).orElseGet(() ->
                ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null)
        );
    }

    @PostMapping("")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);
        return ResponseHandler.generateResponse("Student created successfully", HttpStatus.CREATED, savedStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseHandler.generateResponse("Student updated successfully", HttpStatus.OK, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseHandler.generateResponse("Student deleted successfully", HttpStatus.OK, null);
    }
}

package vn.member_managerment.studentInfor;

import org.springframework.stereotype.*;
import vn.member_managerment.student.Student;

@Service
public class StudentInfoService {

    public StudentInfo updateStudentInfo(Student student, StudentInfo newInfo) {
        if (newInfo == null) {
            return student.getStudentInfo(); // Không có thông tin mới, giữ nguyên dữ liệu cũ
        }

        StudentInfo studentInfo = student.getStudentInfo();
        if (studentInfo == null) {
            studentInfo = new StudentInfo();
            studentInfo.setStudent(student);
        }

        if (newInfo.getAddress() != null && !newInfo.getAddress().isEmpty()) {
            studentInfo.setAddress(newInfo.getAddress());
        }

        if (newInfo.getAverageScore() != null) {
            studentInfo.setAverageScore(newInfo.getAverageScore());
        }

        if (newInfo.getDateOfBirth() != null) {
            studentInfo.setDateOfBirth(newInfo.getDateOfBirth());
        }

        return studentInfo;
    }
}
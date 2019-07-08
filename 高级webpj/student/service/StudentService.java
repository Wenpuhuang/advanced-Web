package education.client.student.service;


import education.entity.Student;

public interface StudentService {
    String login(String code);

    String sendGetReq(String url);

    int save(Student Student);

    int update(Student Student);

    int remove(Student Student);

    Student getStudentById(String StudentId);

    boolean isExisted(String studentID);
}

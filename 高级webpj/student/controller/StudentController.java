package education.client.student.controller;

import education.client.student.service.StudentService;
import education.entity.Student;
//import org.json.simple.parser.JSONParser;
import net.minidev.json.JSONObject;

//import org.json.simple.JSONObject;
//import net.sf.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@CrossOrigin
@RequestMapping("user")
public class StudentController {
  @Resource
  private StudentService studentService;

  //登录
  @RequestMapping(value = "loginAuth", produces = "application/json;UTF-8")
  public String loginAuth(@RequestParam("code") String code) {
   // System.out.println(code);
    // "openid": "{\"session_key\":\"0++DfHwrMHn6S5TDbTui3.0Q==\",\"openid\":\"ocfet1VAmStb_mBW-5SqUWDWmtXM\"}"
    String openid = studentService.login(code);
    JSONObject jsonObject = new JSONObject() ;
    jsonObject.put("openid",openid);
    jsonObject.put("isExisted",studentService.isExisted(openid));
    return jsonObject.toJSONString();
  }

  //获得和完善用户信息的接口,用id取得学生全部个人信息    已测试
  @RequestMapping(value = "getStu", produces = "application/json;UTF-8")
  public String getStu(@RequestParam() String studentid) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("student", studentService.getStudentById(studentid));
    return jsonObject.toJSONString();
  }

  // 更新个人信息   已测试
  @RequestMapping(value = "updateStu", method = RequestMethod.POST)
  public String updateStu(@RequestBody Student student) {
    System.out.println(student.getStudentid());
    JSONObject jsonObject = new JSONObject();
//    Student student = new Student();
//    student.setStudentid(studentid);
//    student.setEmail(email);
//    student.setName(name);
//    student.setGender(gender);
//    student.setStudentno(studentNo);
    Student checkedStudent = studentService.getStudentById(student.getStudentid());
    if (null == checkedStudent) {
      studentService.save(student);
    }
    studentService.update(student);
    jsonObject.put("status", true);
    return jsonObject.toJSONString();
  }
}

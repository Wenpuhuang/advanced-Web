package education.client.student.controller;

import education.client.student.service.CourseService;
import jdk.nashorn.internal.scripts.JO;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("course")
public class CourseController {
  @Resource
  private CourseService courseService;

  //4.1 得到学生所选课程
  @RequestMapping(value = "getCourse")
  public String getCourse(@RequestParam String studentID) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list", courseService.getCourse(studentID));
    return jsonObject.toJSONString();
  }

  //4.2 得到全部课程    已测
  @RequestMapping(value = "getAllCourse", produces = "application/json;UTF-8")
  public String getAllCourse() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list", courseService.getAllCourse());
    return jsonObject.toJSONString();
  }

  //4.3 学生添加课程    已测
  @RequestMapping(value = "addcourse")
  public String addCourse(@RequestParam String studentID, @RequestParam int courseid) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("status", courseService.addCourse(studentID, courseid));
    return jsonObject.toJSONString();
  }

  //4.4 得到course所有章节id，name，decription    已测
  @RequestMapping(value = "getChapter")
  public String getAllChapterByCourseId(@RequestParam int courseid) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list", courseService.getAllChapterByCourseId(courseid));
    return jsonObject.toJSONString();
  }

  //4.5 得到对应章节的所有知识点
  @RequestMapping(value = "getkpoint")
  public String getAllKnowledgePointByChapterId(@RequestParam int chapterId) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list", courseService.getAllKnowledgePointByChapterId(chapterId));
    return jsonObject.toJSONString();
  }

  //4.6 得到知识点对应每条小内容    已测
  @RequestMapping(value = "getKpDetail")
  public String getKpDetail(@RequestParam int kpID){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("kpdetail",courseService.getKpDetail(kpID));
    return jsonObject.toJSONString();
  }
  //3.1 通过学生id查询到对应收藏的知识点 ； //已测
  @RequestMapping(value = "getFav")
  public String getFav(@RequestParam String studentID){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list",courseService.getFav(studentID));
    return jsonObject.toJSONString();
  }
  //3.2 添加收藏  已测
  @PostMapping(value = "addFav")
  public String addFav(@RequestParam String studentID, @RequestParam int kpID,@RequestParam String description) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("status", courseService.addFav(studentID, kpID,description));
    return jsonObject.toJSONString();
  }

  //3.3 删除收藏    已测
  @PostMapping(value = "delFav")
  public String delFav(@RequestParam String studentID, @RequestParam int kpID) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("status", courseService.delFav(studentID, kpID));
    return jsonObject.toJSONString();
  }

  //3.4 获取笔记列表    已测 noteid为3的没有显示
  @RequestMapping(value = "getNote")
  public String getNote(@RequestParam String studentID) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list",courseService.getNote(studentID));
    return jsonObject.toJSONString();
  }

  //3.5 添加笔记    已测
  @PostMapping(value = "addNote")
  public String addNote(@RequestParam String studentID, @RequestParam int kpID, @RequestParam String description) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("status", courseService.addNote(studentID, kpID, description));
    return jsonObject.toJSONString();
  }
  //3.6 删除笔记    已测
  @PostMapping(value = "delNote")
  public String delNote(@RequestParam int memoID) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("status", courseService.delNote(memoID));
    return jsonObject.toJSONString();
  }

  //5.1 得到对应课程的测验题  如果没有数据会报错
  @RequestMapping(value = "getPaper")
  public String getPaper(@RequestParam int courseid){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("paperid",courseService.getPaper(courseid));
    return jsonObject.toJSONString();
  }
  //5.2 得到对应课程的测验题目 返回参数：exerciseid ,desc,options,answer
  @RequestMapping(value = "getExercise")
  public String getExercise(@RequestParam int paperid){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("list",courseService.getExercise(paperid));
    return jsonObject.toJSONString();
  }
  //5.3 记录学生做得习题答案    不能插入数据表中已有的studentID-exerciseID-paperID
  @PostMapping(value = "submitPaper")
  public String submitPaper(@RequestParam String studentID,@RequestParam int exerciseID,
                            @RequestParam int paperID,@RequestParam char choose){
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("status",courseService.submitPaper(studentID,exerciseID,paperID,choose));
    return jsonObject.toJSONString();
  }
}

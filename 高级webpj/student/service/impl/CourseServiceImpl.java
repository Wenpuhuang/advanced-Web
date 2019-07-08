package education.client.student.service.impl;

import education.client.student.service.CourseService;
import education.dao.*;
import education.entity.*;
import education.entity.wx.WXCourse;
import education.entity.wx.WXFavorite;
import education.entity.wx.WXNote;
import education.entity.wx.WXStudentCourse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
  @Resource
  private CourseMapper courseMapper;
  @Resource
  private ChooseCourseMapper chooseCourseMapper;
  @Resource
  private ChapterMapper chapterMapper;
  @Resource
  private FavoriteMapper favoriteMapper;
  @Resource
  private MemoMapper memoMapper;
  @Resource
  private WXCustomMapper wxCustomMapper;
  @Resource
  private KPDetailMapper kpDetailMapper;
  @Resource
  private PaperMapper paperMapper;

  @Override
  //4.1 通过studentID获取该学生的所有的课程
  public List<WXStudentCourse> getCourse(String studentID){
    return wxCustomMapper.getStudentCourse(studentID);
  }
  @Override
  //4.2 得到全部课程    已测
  public List<WXCourse> getAllCourse() {
    List<WXCourse> list = wxCustomMapper.getAllCourse();
    System.out.println(list);
    return wxCustomMapper.getAllCourse();
  }

  @Override
  //4.3 学生添加课程
  public boolean addCourse(String studentId, int courseId) {
    ChooseCourse chooseCourse = new ChooseCourse();
    chooseCourse.setStudentid(studentId);
    chooseCourse.setCourseid(courseId);
    if (chooseCourseMapper.insertSelective(chooseCourse) != 0) {
      return true;
    }
    return false;
  }

  @Override
  //4.4 得到course所有章节id，name，decription
  public List<Chapter> getAllChapterByCourseId(int courseId) {
    return wxCustomMapper.getAllChapterByCourseID(courseId);
  }

  @Override
  //4.5 得到对应章节的所有知识点
  public List<KnowledgePoint> getAllKnowledgePointByChapterId(int chapterId) {
    return wxCustomMapper.getAllKnowledgePoint(chapterId);
  }

  @Override
  //4.6
  public String getKpDetail(int kpID){
    KPDetail kpDetail = kpDetailMapper.selectByPrimaryKey(kpID);
    return kpDetail.getDescription();
  }
  @Override
  //3.1 通过学生id查询到对应收藏的知识点
  public  List<WXFavorite> getFav(String studentID){
    return wxCustomMapper.getFavorite(studentID);
  }
  @Override
  //3.2 添加收藏
  public boolean addFav(String studentId, int kpID ,String description) {
    Favorite favorite = new Favorite();
    favorite.setStudentid(studentId);
    favorite.setKpid(kpID);
    favorite.setDescription(description);
    if (favoriteMapper.insertSelective(favorite) != 0) {
      return true;
    }
    return false;
  }

  @Override
  //3.3 删除收藏
  public boolean delFav(String studentId, int kpID) {
    FavoriteKey favoriteKey = new FavoriteKey();
    favoriteKey.setStudentid(studentId);
    favoriteKey.setKpid(kpID);
    if (favoriteMapper.deleteByPrimaryKey(favoriteKey) != 0) {
      return true;
    }
    return false;
  }

  @Override
  //3.4 获取笔记列表
  public List <WXNote> getNote(String studentID) {
    return wxCustomMapper.getNote(studentID);
  }

  @Override
  //3.5 添加笔记
  public boolean addNote(String studentID, int kpID, String description) {
    Memo memo = new Memo();
    memo.setStudentid(studentID);
    memo.setKpid(kpID);
    memo.setDescription(description);
    if (memoMapper.insertSelective(memo) != 0) {
      return true;
    }
    return false;
  }
  @Override
  //3.6 删除笔记
  public boolean delNote(int memoID){
    if (memoMapper.deleteByPrimaryKey(memoID) != 0) {
      return true;
    }
    return false;
  }


  @Override
  //5.1 得到对应课程的测验题
  public int getPaper(int courseid){
    return wxCustomMapper.getPaperID(courseid);
  }

  //5.2 得到对应课程的测验题目 返回参数：exerciseid ,desc,options,answer
  @Override
  public List<ExerciseWithBLOBs> getExercise(int paperid){
    return wxCustomMapper.getPaper(paperid);
  }

  //5.3
  @Override
  public boolean submitPaper(String studentID, int exerciseID, int paperID, char choose){
    if(wxCustomMapper.insertDoPaper(studentID,exerciseID,paperID,choose) != 0){
      return true;
    }
    return false;
  }
}

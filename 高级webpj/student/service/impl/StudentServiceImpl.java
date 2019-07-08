package education.client.student.service.impl;

import education.client.student.service.StudentService;
import education.dao.StudentMapper;
import education.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
  @Resource
  private StudentMapper studentMapper;

  @Override
  public boolean isExisted(String studentID){
    Student student = studentMapper.selectByPrimaryKey(studentID);
    if(student == null){
      return false;
    }
    return true;
  }
  @Override
  public String login(String code) {
//    String appId = "wxa4eea09957bbdc57";
    String appId = "wx20468fa6d9ea9bf3";
//    String secretKey = "5b3986f5ce132d92da5348842d4b62ef";
    String secretKey = "fdcec829f17be0dc980517cc0f4a45eb";
    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret="
      + secretKey + "&js_code=" + code + "&grant_type=authorization_code";

    String results = sendGetReq(url);
    if (results.equals("")) {
      System.out.println("不存在");
    }
//    System.out.print("results:");
//    System.out.println(results);
    String openid = "";
    int len = results.indexOf("openid");
    //\"session_key\":\"0++DfHwrMHn6S5TDbTui3Q==\",\"openid\":\"ocfet1VAmStb_mBW-5SqUWDWmtXM\"
    //code":40029,"errmsg":"invalid code, hints: [ req_id: 1lJCC0yFe-iE_vTa
    openid = results.substring(len + 9, results.length() - 2);
//    System.out.print("openid:");
//    System.out.println(openid);
    return openid;
  }

  public String sendGetReq(String url) {
    String result = "";
    BufferedReader in = null;
    try {
      String urlNameString = url;
      URL realUrl = new URL(urlNameString);
      // 打开和URL之间的连接
      URLConnection connection = realUrl.openConnection();
      // 设置通用的请求属性
      connection.setRequestProperty("accept", "*/*");
      connection.setRequestProperty("connection", "Keep-Alive");
      connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      // 建立实际的连接
      connection.connect();
      // 获取所有响应头字段
      java.util.Map<String, List<String>> map = connection.getHeaderFields();
      // 遍历所有的响应头字段
      for (String key : map.keySet()) {
        System.out.println(key + "--->" + map.get(key));
      }
      // 定义 BufferedReader输入流来读取URL的响应
      in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      System.out.println("发送GET请求出现异常！" + e);
      e.printStackTrace();
    } // 使用finally块来关闭输入流
    finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return result;
  }

  @Override
  public int save(Student student) {
    return studentMapper.insert(student);
  }

  @Override
  public int update(Student student) {
    return studentMapper.updateByPrimaryKeySelective(student);
  }

  @Override
  public int remove(Student student) {
    return studentMapper.deleteByPrimaryKey(student.getStudentid());
  }

  @Override
  public Student getStudentById(String studentId) {
    return studentMapper.selectByPrimaryKey(studentId);
  }
}

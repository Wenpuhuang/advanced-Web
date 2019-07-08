import request from './request.js'

class courseApi {
  constructor() {
    const app = getApp();
    console.log(app);
    this._baseUrl = 'http://192.168.2.153:8080/course';
    this._defaultHeader = {
      'data-tupe': 'application/json'
    }
    this._request = new request
    this._request.setErrorHandler(this.errorHander)
  }

  //错误处理
  errorHander(res) {
    console.error(res)
  }

  //得到全部课程
  getAllCourse(key = null) {
    let data = key != null ? {
      queryValue: key
    } : {}
    return this._request.getRequest(this._baseUrl + '/getAllCourse', data).then(res => res.data)
  }

  //得到已选课程
  getCourse(openid, key = null) {
    let data = key != null ? {
      studentID: openid,
      queryValue: key
    } : {
      openid: openid
    }
    return this._request.getRequest(this._baseUrl + '/getCourse', data).then(res => res.data)
  }

  //添加课程到已选
  addCourse(openid, courseid, key = null) {
    let data = key != null ? {
      studentid: openid,
      courseid: courseid,
      queryValue: key
    } : {
      openid: openid,
      courseid: courseid,
    }
    return this._request.postRequest(this._baseUrl + '/addCourse', data).then(res => res.data)
  }

  //得到课程的章节
  getChapter(courseid, key = null) {
    let data = key != null ? {
      courseid: courseid,
      queryValue: key
    } : {
      courseid: courseid
    }
    return this._request.getRequest(this._baseUrl + '/getChapter', data).then(res => res.data)
  }

  //得到章节的kpoint
  getKp(chapterid, stdid, key = null) {
    let data = key != null ? {
      chapterid: chapterid,
      studentID: stdid,
      queryValue: key
    } : {
      chapterid: chapterid,
      studentID: stdid,
    }
    return this._request.getRequest(this._baseUrl + '/getKpoint', data).then(res => res.data)
  }

  getKpDetail(kpid, key = null) {
    let data = key != null ? {
      kpid: kpid,
      queryValue: key
    } : {
      kpid: kpid
    }
    return this._request.getRequest(this._baseUrl + '/getkpdetail', data).then(res => res.data)
  }

  //得到收藏
  getFav(stdid, key = null) {
    let data = key != null ? {
      studentID: stdid,
      queryValue: key
    } : {
      studentID: stdid,
    }
    return this._request.getRequest(this._baseUrl + '/getFav', data).then(res => res.data)
  }
  //add收藏
  addFav(kpid, stdid, key = null) {
    let data = key != null ? {
      kpid: kpid,
      studentID: stdid,
      queryValue: key
    } : {
      kpid: kpid,
      studentID: stdid,
    }
    return this._request.postRequest(this._baseUrl + '/addFav', data).then(res => res.data)
  }
  //删除收藏
  delFav(kpid, stdid, key = null) {
    let data = key != null ? {
      kpid: kpid,
      studentID: stdid,
      queryValue: key
    } : {
      kpid: kpid,
      studentID: stdid
    }
    return this._request.postRequest(this._baseUrl + '/delFav', data).then(res => res.data)
  }
  //得到笔记
  getNote(stdid, key = null) {
    let data = key != null ? {
      studentID: stdid,
      queryValue: key
    } : {
      studentID: stdid,
    }
    return this._request.getRequest(this._baseUrl + '/getNote', data).then(res => res.data)
  }
  //添加笔记
  addNote(stdid, kpid, key = null) {
    let data = key != null ? {
      studentID: stdid,
      kpid: kpid,
      queryValue: key
    } : {
      studentID: stdid,
      kpid: kpid
    }
    return this._request.postRequest(this._baseUrl + '/addNote', data).then(res => res.data)
  }
  //删除笔记
  delNote(noteid, key = null) {
    let data = key != null ? {
      momoID: noteid,
      queryValue: key
    } : {
      noteID: noteid,
    }
    return this._request.postRequest(this._baseUrl + '/delNote', data).then(res => res.data)
  }

  //更新笔记
  updateNote(noteid, desc, key = null) {
    let data = key != null ? {
      momoID: noteid,
      description: desc,
      queryValue: key
    } : {
      noteID: noteid,
      description: desc
    }
    return this._request.postRequest(this._baseUrl + '/updateNote', data).then(res => res.data)
  }
  //得到试卷
  getPaper(courseid, key = null) {
    let data = key != null ? {
      courseid: courseid,
      queryValue: key
    } : {
      courseid: courseid,
    }
    return this._request.getRequest(this._baseUrl + '/getPaper', data).then(res => res.data)
  }

  //得到试题
  getExercise(paperid) {
    let data = key != null ? {
      paperid: paperid,
      queryValue: key
    } : {
      paperid: paperid,
    }
    return this._request.getRequest(this._baseUrl + '/getExercise', data).then(res => res.data)
  }


}
export default courseApi
import request from './request.js'

class userApi {
  constructor() {
    const app = getApp();
    console.log(app);
    this._baseUrl = 'http://192.168.2.153:8080/user';
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

  //login 发送 res.code 到后台换取 openId, sessionKey, unionId
  login(code, key = null) {
    let data = key != null ? {
      code: code,
      queryValue: key
    } : {
      code: code,
    }
    return this._request.postRequest(this._baseUrl + '/loginAuth', data).then(res => res.data)
  }

  //得到个人信息
  getStd(openid, key = null) {
    let data = key != null ? {
      studentid: openid,
      queryValue: key
    } : {
      studentid: openid
    }
    return this._request.getRequest(this._baseUrl + '/getStu', data).then(res => res.data)
  }

  //更新个人信息
  updateUser(openid, email, name, gender, stdNo, key = null) {
    let data = key != null ? {
      studentid: openid,
      email: email,
      name: name,
      gender: gender,
      studentNo: stdNo,
      queryValue: key
    } : {
      studentid: openid,
      email: email,
      name: name,
      gender: gender,
      studentNo: stdNo,
    }
    return this._request.postRequest(this._baseUrl + '/updateStu', data).then(res => res.data)
  }
}
export default userApi
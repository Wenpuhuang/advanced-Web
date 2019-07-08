//app.js
import userApi from 'apis/userApi.js'
App({
  onLaunch: function() {
    let that = this;
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // let stdid = 'huang'
    // wx.setStorageSync('stdid', stdid)
    wx.setStorageSync('isexisted', false)
    let api = new userApi;


    // 登录
    wx.login({
      success: res => {
        console.log(res.code)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        api.login(res.code).then(datat => {
          if (data.openid) {
            wx.setStorageSync('stdid', data.openid)
            wx.setStorageSync('isexisted', data.isExisted)
            console.log(data)
          }
        })
        api.getStd(stdid).then(data => {
          wx.setStorage({
            key: 'user',
            data: {
              'email': data.student.email,
              'gender': data.student.gender,
              'name': data.student.name,
              'stdno': data.student.studentno
            }
          })
        })
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })

    globalData: {
      userInfo: null
    }
  }
})
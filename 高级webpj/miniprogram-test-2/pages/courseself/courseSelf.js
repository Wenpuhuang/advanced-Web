// pages/courseself/courseSelf.js
import courseApi from '../../apis/courseApi.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stdid: '',
    courses: [{
      'courseName': '计算机',
      'courseID': '计算机',
      'teacher': '计算机'
    }]
  },
  init: function() {
    let that = this;
    let api = new courseApi;
    let stdidtemp = wx.getStorageSync('stdid');
    that.setData({
      stdid: stdidtemp
    })
    api.getCourse(stdidtemp).then(data => {
      that.setData({
        courses: data
      })
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.init()
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})
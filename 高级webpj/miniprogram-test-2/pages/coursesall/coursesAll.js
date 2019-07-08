// pages/coursesAll/coursesAll.js
import coursesApi from "../../apis/courseApi.js"
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stdid: '',
    allcourses: [],
    index: '',
  },
  addcourse: function(options) {
    let api2 = new coursesApi;
    api2.addcourse(this.stdid, this.allcourses[index].courseID).then(data => {
      if (data.status) {
        wx.showToast({
          title: '添加成功',
          duration: 1000
        })
      } else {
        wx.showToast({
          title: '添加失败',
          duration: 1000
        })
      }
    })
  },
  fresh: function() {
    let that = this;
    let stdidtemp = wx.getStorageSync('stdid');
    that.setData({
      stdid: stdidtemp
    })
    let api = new coursesApi;
    api.getAllCourse().then(data => {
      that.setData({
        allcourses: data
      })
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.fresh();
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
    this.fresh();
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
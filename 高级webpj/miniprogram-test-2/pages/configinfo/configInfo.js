import courseApi from '../../apis/courseApi.js'
import userApi from '../../apis/userApi.js'

const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stdid: '',
    name: '',
    stdno: '',
    gender: '',
    note_image: '../../static/images/note.png',
    fav_image: '../../static/images/fav.png',
    conf_image: '../../static/images/per.png'
  },

  getStd: function() {
    let that = this;
    let idtemp = wx.getStorageSync('stdid');
    that.setData({
      stdid: idtemp,
    })
    wx.getStorage({
      key: 'user',
      success: function(res) {
        that.setData({
          name: res.data.name,
          stdno: res.data.stdno,
          gender: res.data.gender
        })
      },
    })
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo
      })
    }
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function() {
    this.getStd;
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.getStd()
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {},

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
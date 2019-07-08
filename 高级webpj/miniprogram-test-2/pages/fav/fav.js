// pages/fav/fav.js
import courseApi from '../../apis/courseApi.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stdid: '',
    fav: [{
      coursename: 'sadas',
      chaptername: 'das',
      kpname: 'sda',
      kpid: '1'
    }],
  },
  delfav: function(e) {
    let index = e.currentTarget.dataset.index;
    console.log(index)
    let api2 = new courseApi;
    api2.delfav(stdid, fav[index].kpID).then(data => {
      if (data.status) {
        wx.showToast({
          title: '取消收藏成功',
          duration: 1000
        })
        fav.splice(index, 1)
      } else {
        wx.showToast({
          title: '取消收藏失败',
          duration: 1000
        })
      }
    })
  },
  fresh: function() {
    let that = this;
    let stdid_temp = wx.getStorageSync(stdid);
    that.setData({
      stdid: stdid_temp
    })
    let api = new courseApi;
    api.getFav(stdid_temp).then(data => {
      that.setData({
        fav: data
      })
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

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
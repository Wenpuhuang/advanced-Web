// pages/kpoint/kpoint.js
import courseApi from '../../apis/courseApi.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    stdid: '',
    chaptername: '',
    chapterid: '',
    kps: [{
        'name': 'http',
        'kpid': '12',
      },
      {
        'name': 'http',
        'kpid': '12',
      }
    ]
  },
  addfav: function(e) {
    console.log(e)
    let index = e.currentTarget.dataset.index;
    console.log(index)
    let api2 = new courseApi;
    console.log(this.kps[index].kpid);
    api2.addfav(this.kps[index].kpid, this.stdid).then(data => {
      if (data.status) {
        wx.showToast({
          title: '收藏成功',
          duration: 1000
        })
      } else {
        wx.showToast({
          title: '收藏失败',
          duration: 1000
        })
      }
    })
  },
  fresh: function() {
    let that = this;
    let api = new courseApi;
    that.setData({
      stdid: wx.getStorageSync('stdid')
    })
    api.getKp(that.chapterid, that.stdid).then(data => {
      that.setData({
        kps: data
      })
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;

    that.setData({
      cname: options.coursename,
      chapterid: options.chapterid,
    })
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
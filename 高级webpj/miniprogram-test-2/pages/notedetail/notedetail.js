// pages/notedetail/notedetail.js
import courseApi from '../../apis/courseApi.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    kpname: '',
    kpid: '',
    noteid: '',
    notetext: ''
  },
  delnote: function() {
    let that = this;
    let api = new courseApi;
    api.delnote(this.noteid).then(data=>{

    })
    wx.showToast({
      title: '删除成功',
      duration: 1000
    })
    wx.navigateBack({

    })
  },
  savenote: function() {
    let that = this;
    let api = new courseApi;
    api.updateNote(this.noteid, this.notetext).then(data => {

    })
    wx.showToast({
      title: '保存成功',
      duration: 1000
    })
  },
  textareaBInput:function(e){
    this.setData({
      notetext: e.detail.value
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    that.setData({
      kpname: options.kpname,
      kpid: options.kpid,
      noteid: options.noteid,
      notetext: options.desc
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
// pages/note/note.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    stdid: '',
    notes: []
  },
  fresh: function() {
    let that = this;
    let stdid_temp = wx.getStorageSync(stdid);
    that.setData({
      stdid: stdid_temp
    })
    let api = new courseApi;
    api.getNote(stdid_temp).then(data => {
      that.setData({
        notes: data
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
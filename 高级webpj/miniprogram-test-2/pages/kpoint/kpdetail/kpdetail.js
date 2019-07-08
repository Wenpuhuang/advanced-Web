// pages/kpoint/kpdetail/kpdetail.js
import courseApi from '../../../apis/courseApi.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    kpid: '',
    kpname: '',
    records: [{
      kpdetail: '123'
    }],
  },
  fres: function(){
    let that = this;
    let api = new courseApi;
    api.getKpDetail(that.kpid).then(data=>{
      that.setData({
        records: data
      })
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    that.setData({
      kpid: options.kpid,
      kpname: options.kpname,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})
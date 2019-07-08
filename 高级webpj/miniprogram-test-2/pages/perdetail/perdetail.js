// pages/perdetail/perdetail.js
import userApi from '../../apis/userApi.js'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    id: "",
    name: "",
    stdno: '',
    email: '',
    gender: ['男', '女'],
    genderindex: ''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    let idtemp = wx.getStorageSync('stdid');
    that.setData({
      id: idtemp
    });
    wx.getStorage({
      key: 'user',
      success: function(res) {
        that.setData({
          name: res.data.name,
          email: res.data.email,
          stdno: res.data.stdno,
          genderindex: res.data.gender ? 1 : 0
        })
      }
    })
  },
  save: function(e) {
    wx.setStorageSync('isexisted', true);
    let api = new userApi;
    let res = false;
    wx.setStorage({
      key: 'user',
      data: {
        'email': this.data.email,
        'gender': this.data.genderindex == 0 ? false : true,
        'name': this.data.name,
        'stdno': this.data.stdno
      }
    })
    api.updateUser(this.id, this.email, this.name, this.gender, this.stdNo).then(data => {
      res = data.status
    })
    if (res) {
      wx.setStorage({
        key: 'user',
        data: {
          'email': this.data.email,
          'gender': this.data.genderindex == 0 ? false : true,
          'name': this.data.name,
          'stdno': this.data.stdno
        }
      })
      wx.showToast({
        title: '保存成功',
        duration: 1000
      })
    } else {
      wx.showToast({
        title: '保存失败',
        duration: 1000
      })
    }
    wx.navigateBack({

    })
  },
  bindGenderChange: function(e) {
    this.setData({
      genderindex: e.detail.value
    })
  },
  emailInput: function(e) {
    this.setData({
      email: e.detail.value
    })
  },
  nameInput: function(e) {
    this.setData({
      name: e.detail.value
    })
  },
  stdnoInput: function(e) {
    this.setData({
      stdno: e.detail.value
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
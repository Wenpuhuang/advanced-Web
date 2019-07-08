Page({
  data: {
    isAuthorized: true
  },
  onLoad: function() {},
  onShow: function(options) {
    this.getUserInfoFun()
  },
  getUserInfoFun: function() {
    var that = this;
    wx.getUserInfo({
      success: function(res) {
        if (wx.getStorageSync('isexisted')) {
          wx.switchTab({
            url: '../configinfo/configInfo',
          })
        } else {
          wx.navigateTo({
            url: '../perdetail/perdetail',
          })
        }
      },
      fail: that.showPrePage
    })
  },
  showPrePage: function() {
    this.setData({
      isAuthorized: false
    })
  }
})
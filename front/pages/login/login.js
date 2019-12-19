// pages/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    username:'',
    password:'',
  },
  handleInputUsernameChange:function(event){
    this.setData({username:event.detail.value});
  },
  handleInputPasswordChange:function(event){
    this.setData({password:event.detail.value});
  }
})
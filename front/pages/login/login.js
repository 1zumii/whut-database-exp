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
  },
  handleLoginByWechat:function(){
    wx.getUserInfo({
      withCredentials: 'false',
      lang: 'zh_CN',
      // timeout:10000,
      success: (result)=>{
        console.log(result)
      },
      fail: (error)=>{
        console.log(error);
      }
    });
  },
  handleLoginByUser:function(){
    
  },
  onGotUserInfo:function(value){
    const {detail} = value;
    console.log("onGotUserInfo",detail);
  }
})
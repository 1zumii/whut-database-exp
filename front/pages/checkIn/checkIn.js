// pages/checkIn/checkIn.js
import {getUserToken} from '../../utils/userTokenManager'

Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let userId = getUserToken();
    if(!userId){
      //未登录
      wx.reLaunch({
        url: "../login/login",  
      });
    }else{
      //已登录
      console.log(userId)
    }
  }
})
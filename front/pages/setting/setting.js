// pages/setting/setting.js
import { AaHostPost } from '../../utils/httpManager'
import { getUserToken,clearUserToken } from '../../utils/userTokenManager'
import Notify from '../../dist/vant-weapp/notify/notify';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //state
    isAdmin:getUserToken().isAdmin,
    //display
    updateUserInfoVisible:false,
  },
  //生命周期函数--监听页面加载
  onLoad: function (options) {

  },
  //点击注销
  handleLogoutClick:function(){
    wx.reLaunch({
      url: "../login/login",
      success: (result)=>{
        clearUserToken();
        Notify({
          type:"success",
          message:"注销成功"
        });
      },
      fail: ()=>{
        Notify({
          type:"warning",
          message:"注销失败"
        });
      },
    });
  },
  //用户信息修改popup
  flipUpdateUserInfoPopupVisible:function(){
    const {updateUserInfoVisible} = this.data;
    this.setData({
      updateUserInfoVisible:!updateUserInfoVisible
    })
  }
})
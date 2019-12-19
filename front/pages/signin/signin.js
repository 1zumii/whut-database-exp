// pages/signin/signin.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //用户信息
    username:'',
    password:'',
    checkPassword:'',
    //学生信息
    name:'',
    stuNum:'',
    gender:null,
  },
  handleGenderChange:function(event){
    this.setData({gender:event.detail});
  },
})
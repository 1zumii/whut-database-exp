// pages/signin/signin.js
import {AaHostPost} from '../../utils/httpManager'

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
    stuNum:'',
    name:'',
    gender:null,
    phone:'',
    classId:null,
    //展示用数据
    genderPopupVisible:false,
    genderMap:['女','男'],
    classPopupVisible:false,
    classDataMap:[],
    classNameMap:[],
  },
  //生命周期函数--监听页面加载
  onLoad: function (options) {
    this.fetchClassIdList();
  },
  //性别选项
  handleGenderChange:function(event){
    this.setData(
      {gender:event.detail.index},
      ()=>this.closeGenderPopup()
    );
  },
  showGenderPopup:function(){
    this.setData({genderPopupVisible:true});
  },
  closeGenderPopup:function(){
    this.setData({genderPopupVisible:false});
  },
  //班级选项
  fetchClassIdList:function(){
    AaHostPost('/login/getAllClasses').then((json)=>{
      if(json.code === 0){
        const {classList} = json.data;
        this.setData({
          classDataMap:classList,
          classNameMap:classList.map(e=>e.className),
        });
      }else{
        throw json;
      }
    }).catch((error)=>{
      Notify({ type: 'danger', message: '可选班级列表获取失败' });
      console.error(error);
    });
  },
  renderClassesList:function(){
    const {genderMap} = this.data;
    return genderMap.map((element)=>{
      console.log(element);
      return element.className;
    });
  },
  flipClassIdPopup:function(){
    const {classPopupVisible} = this.data;
    this.setData({classPopupVisible:!classPopupVisible});
  },
  handleClassIdChange:function(event){
    const {index} = event.detail;
    const {classDataMap} = this.data;
    this.setData({classId:classDataMap[index].id});
  }
})